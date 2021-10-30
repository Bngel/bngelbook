package cn.bngel.bngelbookbillprovider8004.service;

import cn.bngel.bngelbookcommonapi.bean.Account;
import cn.bngel.bngelbookcommonapi.bean.Bill;
import cn.bngel.bngelbookbillprovider8004.dao.BillDao;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillDao billDao;

    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "bngelbook-bill-save", rollbackFor = Exception.class)
    public Integer saveBill(Bill bill) {
        Integer result = billDao.saveBill(bill);
        if (bill.getAccountId() != null) {
            CommonResult<Account> commonResult = accountService.getAccountById(bill.getAccountId());
            if (commonResult.getCode().equals(CommonResult.SUCCESS_CODE)) {
                Account account = commonResult.getData();
                Account newAccount = new Account();
                newAccount.setId(account.getId());
                newAccount.setBalance(account.getBalance() + ((bill.getIo() == 1) ? bill.getBalance() : -bill.getBalance()));
                accountService.updateAccountById(newAccount);
            }
        }
        return result;
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
