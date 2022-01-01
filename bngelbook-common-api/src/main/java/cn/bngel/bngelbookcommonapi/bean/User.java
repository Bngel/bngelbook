package cn.bngel.bngelbookcommonapi.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    public static Integer MAX_LENGTH_OF_USERNAME = 15;
    public static Integer OVER_LENGTH_ERROR_CODE = 410;
    public static Integer USER_REGISTERED_ERROR_CODE = 411;
    public static String USER_REGISTERED_ERROR_MESSAGE = "用户已存在";
    public static String OVER_LENGTH_ERROR_MESSAGE = "用户名长度越界";
    public static String UNKNOWN_USER_MESSAGE = "查询用户不存在";
    public static String LOGIN_ERROR_MESSAGE = "用户名或密码错误";

    @ApiModelProperty(value = "用户id", name = "id", required = true)
    private Long id;
    @ApiModelProperty(value = "用户名", name = "username", required = true)
    private String username;
    @ApiModelProperty(value = "密码", name = "password", required = true)
    private String password;
    @ApiModelProperty(value = "手机号", name = "phone", required = true)
    private String phone;
    @ApiModelProperty(value = "邮箱", name = "email")
    private String email;
    @ApiModelProperty(value = "头像", name = "profile")
    private String profile;
    @ApiModelProperty(value = "性别", name = "gender")
    private Integer gender;
    @ApiModelProperty(value = "生日", name = "birthday", example = "2001-01-01")
    private Date birthday;
    @ApiModelProperty(value = "注册日期, 无需传入, 后端会自动填写", name = "registerDate")
    private Date registerDate;

    public void setRegisterDate() {
        setRegisterDate(new Date(new java.util.Date().getTime()));
    }
}
