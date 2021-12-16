package cn.bngel.bngelbookuserprovider8001.service;

import cn.bngel.bngelbookcommonapi.bean.User;
import cn.bngel.bngelbookuserprovider8001.dao.UserDao;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
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
    public Integer registerUser(User user, Integer type) {
        Integer userExists;
        if (type == 0)
            userExists = userDao.judgeUserExists(user.getPhone());
        else if (type == 1)
            userExists = userDao.judgeUserExists(user.getEmail());
        else
            userExists = -2;
        if (userExists == 0) {
            return userDao.saveUser(user);
        }
        else {
            return -1;
        }
    }

    @Override
    public String smsLogin(String area, String phone) {
        try {
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
            String checkNumber = String.format("%06d", new Random().nextInt(999999));
            String[] templateParamSet = {checkNumber, "10"};
            req.setTemplateParamSet(templateParamSet);
            SendSmsResponse res = client.SendSms(req);
            System.out.println(SendSmsResponse.toJsonString(res));
            System.out.println(res.getRequestId());
            return checkNumber;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public Integer updateUsernameById(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return userDao.updateUserById(user);
    }


}
