package cn.bngel.bngelbookcommonapi.bean;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonResult<T> {

    public static Integer SUCCESS_CODE = 200;
    public static Integer FAILURE_CODE = 400;
    public static String SUCCESS_MESSAGE = "success";
    public static String FAILURE_MESSAGE = "failed";

    public static final String UNKNOWN_MESSAGE = "查询账户不存在";
    public static final String NULL_ID_ERROR_MESSAGE = "ID为空, 请确认输入信息是否正确";
    public static final Integer NULL_ID_ERROR_CODE = 413;

    @ApiModelProperty(value = "响应状态码 默认状态(成功: 200, 失败: 400)", name = "code")
    private Integer code;
    @ApiModelProperty(value = "响应数据", name = "data")
    private T data;
    @ApiModelProperty(value = "响应信息 默认状态(成功: success, 失败: failure)", name = "code")
    private String message;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CommonResult commonSuccessResult() {
        return new CommonResult(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public static CommonResult commonFailureResult() {
        return new CommonResult(FAILURE_CODE, FAILURE_MESSAGE);
    }

}
