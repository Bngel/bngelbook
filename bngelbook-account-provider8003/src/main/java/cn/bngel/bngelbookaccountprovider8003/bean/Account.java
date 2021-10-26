package cn.bngel.bngelbookaccountprovider8003.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    public static String UNKNOWN_MESSAGE = "查询账户不存在";
    private Long id;
    private String name;
    private Long userId;
    private Double balance;
}
