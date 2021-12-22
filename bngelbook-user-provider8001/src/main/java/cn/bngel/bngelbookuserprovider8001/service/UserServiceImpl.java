package cn.bngel.bngelbookuserprovider8001.service;

import cn.bngel.bngelbookcommonapi.bean.User;
import cn.bngel.bngelbookuserprovider8001.dao.UserDao;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import io.lettuce.core.RedisClient;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.netty.channel.ConnectTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

// 导入对应SMS模块的client
import com.tencentcloudapi.sms.v20210111.SmsClient;

// 导入要请求接口对应的request response类
import com.tencentcloudapi.sms.v20210111.models.PullSmsReplyStatusRequest;
import com.tencentcloudapi.sms.v20210111.models.PullSmsReplyStatusResponse;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Value("${tencent-cloud.SecretId}")
    private String secretId;

    @Value("${tencent-cloud.SecretKey}")
    private String secretKey;

    @Value("${tencent-cloud-sms.sdkAppId}")
    private String sdkAppId;

    @Value("${tencent-cloud-sms.signName}")
    private String signName;

    @Value("${tencent-cloud-sms.templateId}")
    private String templateId;

    @Override
    public Integer saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public Integer deleteUserById(Long id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public Integer updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    @Override
    public User login(String account, String password) {
        return userDao.login(account, password);
    }

    @Override
    public Integer getUserRegisterDays(Long id) {
        return userDao.getUserRegisterDays(id);
    }

    @Override
    public List<User> getFriendsById(Long id) {
        return userDao.getFriendsById(id);
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        return userDao.getUsersByUsername(username);
    }

    @Override
    public Integer registerUser(User user) {
        Integer userExists = userDao.judgeUserExists(user.getPhone());
        if (userExists == 0)
            return userDao.saveUser(user);
        else
            return -1;
    }

    @Override
    public String smsLogin(String area, String phone) {
        try {
            String checkNumber = String.format("%06d", new Random().nextInt(999999));
            String s = saveCode(phone, checkNumber);
            if (s.equals("OK")) {
                sendMessage(area, phone, checkNumber);
                return checkNumber;
            }
            else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public Boolean smsCheck(String phone, String code) {
        return checkCode(phone, code);
    }

    @Override
    public Integer judgeUserExists(String phone) {
        return userDao.judgeUserExists(phone);
    }

    @Override
    public Integer updateUsernameById(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return userDao.updateUserById(user);
    }

    private SendSmsResponse sendMessage(String area, String phone, String code) throws TencentCloudSDKException {
        Credential cred = new Credential(secretId, secretKey);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod("POST");
        httpProfile.setConnTimeout(60);
        httpProfile.setEndpoint("sms.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);
        SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);
        SendSmsRequest req = new SendSmsRequest();
        req.setSmsSdkAppId(sdkAppId);
        req.setSignName(signName);
        String senderid = "";
        req.setSenderId(senderid);
        String extendCode = "";
        req.setExtendCode(extendCode);
        req.setTemplateId(templateId);
        String phoneNumber = area + phone;
        String[] phoneNumberSet = {phoneNumber};
        req.setPhoneNumberSet(phoneNumberSet);
        String[] templateParamSet = {code, "10"};
        req.setTemplateParamSet(templateParamSet);
        SendSmsResponse res = client.SendSms(req);
        return res;
    }

    private String saveCode(String phone, String code) {
        try {
            RedisClient redisClient = RedisClient.create("redis://81.68.149.16");
            StatefulRedisConnection<String, String> connect = redisClient.connect();
            RedisCommands<String, String> sync = connect.sync();
            SetArgs args = SetArgs.Builder.ex(1000 * 60 * 10);
            String s = sync.set("loginSms:" + phone, code, args);
            connect.close();
            redisClient.shutdown();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private Boolean checkCode(String phone, String code) {
        try {
            String key = "loginSms:" + phone;
            RedisClient redisClient = RedisClient.create("redis://localhost");
            StatefulRedisConnection<String, String> connect = redisClient.connect();
            RedisCommands<String, String> sync = connect.sync();
            String s = sync.get(key);
            if (s == null) {
                connect.close();
                redisClient.shutdown();
                return false;
            }
            else {
                if (s.equals(code)) {
                    sync.del(key);
                    connect.close();
                    redisClient.shutdown();
                    return true;
                }
                else {
                    connect.close();
                    redisClient.shutdown();
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
