package cn.bngel.bngelbookbillprovider8008.service;

import cn.bngel.bngelbookbillprovider8008.bean.Bill;

import java.util.List;

public interface BillService {

    Integer saveBill(Bill bill);

    Integer deleteBillById(Long id);

    Integer updateBillById(Bill bill);

    Bill getBillById(Long id);

    List<Bill> getBillsByBookId(Long bookId);

    List<Bill> getBillsByAccountId(Long accountId);
}
