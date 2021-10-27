package cn.bngel.bngelbookbillprovider8004.service;

import cn.bngel.bngelbookbillprovider8004.bean.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillService {

    Integer saveBill(Bill bill);

    Integer deleteBillById(Long id);

    Integer updateBillById(Bill bill);

    Bill getBillById(Long id);

    List<Bill> getBillsByBookId(Long bookId);

    List<Bill> getBillsByAccountId(Long accountId);
}
