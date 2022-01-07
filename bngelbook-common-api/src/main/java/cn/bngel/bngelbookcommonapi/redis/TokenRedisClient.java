package cn.bngel.bngelbookcommonapi.redis;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class TokenRedisClient extends SimpleRedisClient{

    private final int defaultExpiredTime = 24*60*60*1000;

    public TokenRedisClient(String password) {
        super(password);
    }

    private interface SyncTokenKeyContext {
        Object invoke(RedisCommands<String, String> sync, String tokenKey);
    }

    public Object syncWithTokenKey(SyncTokenKeyContext syncContext) {
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommands<String, String> sync = connect.sync();
        try {
            String readTokenKey = sync.get("tokenKey");
            if (readTokenKey == null) {
                readTokenKey = createSalt();
                SetArgs args = SetArgs.Builder.ex(defaultExpiredTime);
                sync.set("tokenKey", readTokenKey, args);
            }
            return syncContext.invoke(sync, readTokenKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connect.close();
            redisClient.shutdown();
        }
    }

    private String createSalt() {
        byte[] encoded = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();
        return Base64.encode(encoded);
    }

    public String createToken(Long id, Integer expiredTime) {
        return (String) syncWithTokenKey((sync, tokenKey) -> {
            String originToken = getOriginToken();
            String resToken = id + "-" + originToken;
            String token = encryptToken(resToken, tokenKey);
            SetArgs args;
            if (expiredTime != null)
                args = SetArgs.Builder.ex(expiredTime);
            else
                args = SetArgs.Builder.ex(defaultExpiredTime);
            String tokenSet = sync.set("token:" + id, originToken, args);
            if (tokenSet.equals("OK"))
                return token;
            else
                return null;
        });
    }

    public String createToken(Long id) {
        return createToken(id, null);
    }

    public Boolean verifyToken(String token) {
        if (token == null) return false;
        return (Boolean) syncWithTokenKey((sync, tokenKey) -> {
            String decryptedToken = decryptToken(token, tokenKey);
            String[] split = decryptedToken.split("-");
            if (split.length == 2) {
                String id = split[0];
                String originToken = split[1];
                String targetToken = sync.get("token:" + id);
                return targetToken.equals(originToken);
            }
            else {
                return false;
            }
        });
    }

    private String encryptToken(String originToken, String salt) {
        byte[] decodedKey = Base64.decode(salt);
        DES des = SecureUtil.des(decodedKey);
        return des.encryptHex(originToken);
    }

    private String decryptToken(String encryptedToken, String salt) {
        byte[] decodedKey = Base64.decode(salt);
        DES des = SecureUtil.des(decodedKey);
        return des.decryptStr(encryptedToken);
    }

    private String getOriginToken() {
        return IdUtil.objectId();
    }
}
