package cn.bngel.bngelbookaccountprovider8007.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    @ApiModelProperty(value = "账户id", name = "id", required = true)
    private Long id;
    @ApiModelProperty(value = "账户名", name = "name", required = true)
    private String name;
    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    private Long userId;
    @ApiModelProperty(value = "余额", name = "balance", required = true)
    private Double balance;
}
