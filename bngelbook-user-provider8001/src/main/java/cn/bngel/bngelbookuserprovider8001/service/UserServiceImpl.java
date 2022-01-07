package cn.bngel.bngelbookuserprovider8001.service;

import cn.bngel.bngelbookcommonapi.bean.User;
import cn.bngel.bngelbookcommonapi.redis.SimpleRedisClient;
import cn.bngel.bngelbookcommonapi.redis.TokenRedisClient;
import cn.bngel.bngelbookuserprovider8001.dao.UserDao;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import io.lettuce.core.SetArgs;
import io.seata.spring.annotation.GlobalTransactional;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    @Value("${tencent-cloud.APPID}")
    private String appId;

    @Value("${tencent-cloud-sms.sdkAppId}")
    private String sdkAppId;

    @Value("${tencent-cloud-sms.signName}")
    private String signName;

    @Value("${tencent-cloud-sms.templateId}")
    private String templateId;

    @Value("${bngelbook-redis-config.password}")
    private String redisPassword;

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
            String checkNumber = String.format("%04d", new Random().nextInt(9999));
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
        SimpleRedisClient simpleRedisClient = new SimpleRedisClient(redisPassword);
        return (String) simpleRedisClient.sync( sync -> {
            SetArgs args = SetArgs.Builder.ex(1000 * 60 * 10);
            return sync.set("loginSms:" + phone, code, args);
        });
    }

    private Boolean checkCode(String phone, String code) {
        SimpleRedisClient simpleRedisClient = new SimpleRedisClient(redisPassword);
        return (Boolean)simpleRedisClient.sync( sync -> {
            String key = "loginSms:" + phone;
            String s = sync.get(key);
            if (s == null) {
                return false;
            }
            else {
                if (s.equals(code)) {
                    sync.del(key);
                    return true;
                }
                else {
                    return false;
                }
            }
        });
    }

    @Override
    public String uploadFile(MultipartFile file, String bucket, String cosPath) throws IOException {
        COSClient cosClient = getCosClient();
        String filepath = System.getProperty("user.dir");
        String fileName = file.getOriginalFilename();
        File dest = new File(filepath + '\\' + fileName);
        file.transferTo(dest);
        cosClient.putObject(bucket, cosPath, dest);
        return cosPath;
    }

    private COSClient getCosClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region("ap-guangzhou"));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }

    @Override
    @GlobalTransactional(name = "bngelbook-user-upload-profile", rollbackFor = Exception.class)
    public String updateProfile(Long id, MultipartFile profile) throws  IOException {
        String bucketName = "bngelbook-profile-" + appId;
        String profileUrl = uploadFile(profile, bucketName, id + "/profile.png");
        if (profileUrl == null)
            return null;
        User user = getUserById(id);
        if (user == null)
            return null;
        user.setProfile(profileUrl);
        Integer saveUser = updateUserById(user);
        if (saveUser == 1)
            return profileUrl;
        else
            return null;
    }

    @Override
    public String createToken(Long id, Integer expiredTime) {
        TokenRedisClient tokenRedisClient = new TokenRedisClient(redisPassword);
        return tokenRedisClient.createToken(id);
    }

    @Override
    public String createToken(Long id) {
        return createToken(id, null);
    }
}
