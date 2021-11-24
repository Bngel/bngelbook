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
public class Friend {

    public static final String FRIEND_EXISTS_ERROR_MESSAGE = "好友已存在";
    public static final Integer FRIEND_EXISTS_ERROR_CODE = 484;
    public static final String USER_NOT_EXISTS_ERROR_MESSAGE = "用户不存在";
    public static final Integer USER_NOT_EXISTS_ERROR_CODE = 488;
    public static final String USER_ADD_SELF_ERROR_MESSAGE = "禁止添加自己为好友";
    public static final Integer USER_ADD_SELF_ERROR_CODE = 423;


    @ApiModelProperty(value = "关系id", name = "id", required = true)
    private Long id;
    @ApiModelProperty(value = "用户1 id", name = "user1Id", required = true)
    private Long user1Id;
    @ApiModelProperty(value = "用户2 id", name = "user2Id", required = true)
    private Long user2Id;
}
