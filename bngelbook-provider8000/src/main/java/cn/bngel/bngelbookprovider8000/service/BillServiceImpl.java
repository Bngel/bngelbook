package cn.bngel.bngelbookprovider8000.service;

import cn.bngel.bngelbookcommonapi.bean.Account;
import cn.bngel.bngelbookcommonapi.bean.Bill;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookprovider8000.dao.BillDao;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillDao billDao;

    @Autowired
    private FeignAccountService accountService;

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
    @GlobalTransactional(name = "bngelbook-bill-delete", rollbackFor = Exception.class)
    public Integer deleteBillById(Long id) {
        Bill bill = billDao.getBillById(id);
        Integer result = billDao.deleteBillById(id);
        if (bill != null) {
            if (bill.getAccountId() == null){
                return result;
            }
            else {
                CommonResult<Account> commonResult = accountService.getAccountById(bill.getAccountId());
                if (commonResult.getCode().equals(CommonResult.SUCCESS_CODE)) {
                    Account account = commonResult.getData();
                    Account newAccount = new Account();
                    newAccount.setId(account.getId());
                    Double newBalance = account.getBalance();
                    if (bill.getIo() == 1) {
                        newBalance -= bill.getBalance();
                    } else if (bill.getIo() == 0) {
                        newBalance += bill.getBalance();
                    }
                    newAccount.setBalance(newBalance);
                    CommonResult<Account> accountResult = accountService.updateAccountById(newAccount);
                    if (accountResult.getCode().equals(CommonResult.SUCCESS_CODE)) {
                        return result;
                    }
                    else {
                        return -1;
                    }
                }
            }
        }
        else {
            return -1;
        }
        return result;
    }

    @Override
    @GlobalTransactional(name = "bngelbook-bill-update", rollbackFor = Exception.class)
    public Integer updateBillById(Bill bill) {
        Bill originBill = billDao.getBillById(bill.getId());
        Integer result = billDao.updateBillById(bill);
        if (originBill.getAccountId() != null) {
            CommonResult<Account> commonResult = accountService.getAccountById(originBill.getAccountId());
            Account account = commonResult.getData();
            Double originBalance = account.getBalance();
            if (originBill.getIo() == 1) {
                originBalance -= originBill.getBalance();
            }
            else if (originBill.getIo() == 0) {
                originBalance += originBill.getBalance();
            }
            Integer io;
            if (bill.getIo() != null) {
                io = bill.getIo();
            }
            else {
                io = originBill.getIo();
            }
            if (io == 1) {
                originBalance += bill.getBalance();
            }
            else if (io == 0) {
                originBalance -= bill.getBalance();
            }
            Account newAccount = new Account();
            newAccount.setId(account.getId());
            newAccount.setBalance(originBalance);
            accountService.updateAccountById(newAccount);
        }
        return result;
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

    @Override
    public List<Bill> getMonthBillsByBookId(Long bookId, Integer month) {
        return billDao.getMonthBillsByBookId(bookId, month);
    }
}
