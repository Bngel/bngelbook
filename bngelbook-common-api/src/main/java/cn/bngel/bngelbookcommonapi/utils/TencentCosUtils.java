package cn.bngel.bngelbookcommonapi.utils;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class TencentCosUtils {

    private final String secretId;
    private final String secretKey;
    private final String region;

    public TencentCosUtils(String secretId, String secretKey, String region) {
        this.secretId = secretId;
        this.secretKey = secretKey;
        this.region = region;
    }

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
        clientConfig.setRegion(new Region(region));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }
}
