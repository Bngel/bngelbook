package cn.bngel.bngelbookbookprovider8002.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private Long id;
    private String type;
    private Double balance;
    private Long accountId;
    private Long userId;
    private String tags;
    private Date createTime;
    private Integer io;
}
