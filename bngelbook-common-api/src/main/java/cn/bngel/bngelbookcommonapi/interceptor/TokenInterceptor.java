package cn.bngel.bngelbookcommonapi.interceptor;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.redis.SimpleRedisClient;
import cn.bngel.bngelbookcommonapi.redis.TokenRedisClient;
import cn.hutool.core.codec.Base32;
import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.lettuce.core.SetArgs;
import jdk.nashorn.internal.parser.Token;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
public class TokenInterceptor implements HandlerInterceptor {

    private final String password;
    private final String host;

    public TokenInterceptor(String host, String password) {
        this.host = host;
        this.password = password;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader("token");
        Boolean valid = tokenValid(token);
        if (valid != null && valid)
            return true;
        else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            String json = JSONUtil.toJsonStr(CommonResult.tokenErrorResult());
            writer.append(json);
            writer.close();
            return false;
        }
    }

    private Boolean tokenValid(String token) {
        TokenRedisClient tokenRedisClient = new TokenRedisClient(host, password);
        return tokenRedisClient.verifyToken(token);
    }
}
