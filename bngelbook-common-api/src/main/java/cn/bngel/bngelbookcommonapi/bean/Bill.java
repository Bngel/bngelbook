package cn.bngel.bngelbookcommonapi.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bill{

    @ApiModelProperty(value = "账单id", name = "id", required = true)
    private Long id;
    @ApiModelProperty(value = "账单类型", name = "type", required = true)
    private String type;
    @ApiModelProperty(value = "账单金额", name = "balance", required = true)
    private Double balance;
    @ApiModelProperty(value = "账户id", name = "accountId")
    private Long accountId;
    @ApiModelProperty(value = "账本id", name = "bookId", required = true)
    private Long bookId;
    @ApiModelProperty(value = "标签", name = "tags")
    private String tags;
    @ApiModelProperty(value = "创建时间", name = "createTime", required = true)
    private Date createTime;
    @ApiModelProperty(value = "账单收支类型", name = "io", required = true)
    private Integer io;
}
