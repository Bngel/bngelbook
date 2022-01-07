package cn.bngel.bngelbookcommonapi.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class SimpleRedisClient {

    protected final RedisClient redisClient;

    public SimpleRedisClient(String password) {
        redisClient = RedisClient.create("redis://" + password + "@bngel.cn");
    }

    public interface SyncContext {
        Object invoke(RedisCommands<String, String> sync);
    }

    public Object sync(SyncContext syncContext) {
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommands<String, String> sync = connect.sync();
        try {
            return syncContext.invoke(sync);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connect.close();
            redisClient.shutdown();
        }
    }

}

