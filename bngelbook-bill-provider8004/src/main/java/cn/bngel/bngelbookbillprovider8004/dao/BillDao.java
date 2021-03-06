package cn.bngel.bngelbookbillprovider8004.dao;

import cn.bngel.bngelbookcommonapi.bean.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BillDao {

    Integer saveBill(@Param("bill") Bill bill);

    Integer deleteBillById(@Param("id") Long id);

    Integer updateBillById(@Param("bill") Bill bill);

    Bill getBillById(@Param("id") Long id);

    List<Bill> getBillsByBookId(@Param("bookId") Long bookId);

    List<Bill> getBillsByAccountId(@Param("accountId") Long accountId);

    List<Bill> getMonthBillsByBookId(@Param("bookId") Long bookId, @Param("month") Integer month);
}
