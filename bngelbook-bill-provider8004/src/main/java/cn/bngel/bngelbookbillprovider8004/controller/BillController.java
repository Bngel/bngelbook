package cn.bngel.bngelbookbillprovider8004.controller;


import cn.bngel.bngelbookbillprovider8004.service.BillService;
import cn.bngel.bngelbookcommonapi.bean.Bill;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "账单模块")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/bill")
    @ApiOperation(value = "Bill - 创建账单")
    public CommonResult saveBill(@RequestBody Bill bill){
        Integer result = billService.saveBill(bill);
        if (result == 1) {
            log.info("创建账单: [" + bill + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("创建账单: [" + bill + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @DeleteMapping("/bill")
    @ApiOperation(value = "Bill - 删除账单")
    public CommonResult deleteBillById(@RequestParam("id") Long id) {
        Integer result = billService.deleteBillById(id);
        if (result == 1) {
            log.info("删除账单: [" + id + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("删除账单: [" + id + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @PutMapping("/bill")
    @ApiOperation(value = "Bill - 更改账单")
    public CommonResult updateBillById(@RequestBody Bill bill) {
        if (bill.getId() == null) {
            return new CommonResult(CommonResult.NULL_ID_ERROR_CODE, CommonResult.NULL_ID_ERROR_MESSAGE);
        }
        Integer result = billService.updateBillById(bill);
        if (result == 1) {
            log.info("更改账单信息: [" + bill + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("更改账单信息: [" + bill + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/bill")
    @ApiOperation(value = "Bill - 查询账单")
    public CommonResult<Bill> getBillById(@RequestParam("id") Long id) {
        Bill result = billService.getBillById(id);
        if (result != null) {
            log.info("查询账单: [" + id + "] 成功");
            return CommonResult.commonSuccessResult(result);
        }
        else {
            log.info("查询账单: [" + id + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }


    @GetMapping("/bill/book/{bookId}")
    @ApiOperation(value = "Bill - 查询账本账单")
    public CommonResult<List<Bill>> getBillsByBookId(@PathVariable("bookId") Long bookId) {
        List<Bill> results = billService.getBillsByBookId(bookId);
        if (results != null) {
            log.info("查询账本账单: [" + bookId + "] 成功");
            return CommonResult.commonSuccessResult(results);
        }
        else {
            log.info("查询账本账单: [" + bookId + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/bill/book/{bookId}/{month}")
    @ApiOperation(value = "Bill - 查询账本月账单")
    public CommonResult<List<Bill>> getMonthBillsByBookId(@PathVariable("bookId") Long bookId,
                                                          @PathVariable("month") Integer month) {
        List<Bill> results = billService.getMonthBillsByBookId(bookId,month);
        if (results != null) {
            log.info("查询账本" + month +"月账单: [" + bookId + "] 成功");
            return CommonResult.commonSuccessResult(results);
        }
        else {
            log.info("查询账本" + month +"月账单: [" + bookId + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/bill/account/{accountId}")
    @ApiOperation(value = "Bill - 查询账户账单")
    public CommonResult<List<Bill>> getBillsByAccountId(@PathVariable("accountId") Long accountId) {
        List<Bill> results = billService.getBillsByAccountId(accountId);
        if (results != null) {
            log.info("查询账户账单: [" + accountId + "] 成功");
            return CommonResult.commonSuccessResult(results);
        }
        else {
            log.info("查询账户账单: [" + accountId + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }
}

