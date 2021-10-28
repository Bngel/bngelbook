package cn.bngel.bngelbookbillprovider8008.service;

import cn.bngel.bngelbookbillprovider8008.bean.Bill;
import cn.bngel.bngelbookbillprovider8008.dao.BillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillDao billDao;

    @Override
    public Integer saveBill(Bill bill) {
        return billDao.saveBill(bill);
    }

    @Override
    public Integer deleteBillById(Long id) {
        return billDao.deleteBillById(id);
    }

    @Override
    public Integer updateBillById(Bill bill) {
        return billDao.updateBillById(bill);
    }

    @Override
    public Bill getBillById(Long id) {
        return billDao.getBillById(id);
    }

    @Override
    public List<Bill> getBillsByBookId(Long bookId) {
        return billDao.getBillsByBookId(bookId);
    }

    @Override
    public List<Bill> getBillsByAccountId(Long accountId) {
        return billDao.getBillsByAccountId(accountId);
    }
}
