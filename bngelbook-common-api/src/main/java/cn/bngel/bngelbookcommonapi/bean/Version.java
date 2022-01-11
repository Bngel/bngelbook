package cn.bngel.bngelbookcommonapi.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Version {
    @ApiModelProperty(value = "版本id", name = "id", required = true)
    private Integer id;
    @ApiModelProperty(value = "版本号", name = "version", required = true)
    private String version;
    @ApiModelProperty(value = "更新内容", name = "content")
    private String content;
}
