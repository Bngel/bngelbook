package cn.bngel.bngelbookcommonapi.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @ApiModelProperty(value = "账本id", name = "id", required = true)
    private Long id;
    @ApiModelProperty(value = "账本名", name = "name", required = true)
    private String name;
    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    private Long userId;
    @ApiModelProperty(value = "账本类型", name = "type", required = true)
    private String type;
}