package cn.bngel.bngelbookbillprovider8008.controller;

import cn.bngel.bngelbookbillprovider8008.bean.Bill;
import cn.bngel.bngelbookbillprovider8008.service.BillService;
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
            return CommonResult.commonSuccessResult();
        }
        else {
            return CommonResult.commonFailureResult();
        }
    }

    @DeleteMapping("/bill")
    @ApiOperation(value = "Bill - 删除账单")
    public CommonResult deleteBillById(@RequestParam("id") Long id) {
        Integer result = billService.deleteBillById(id);
        if (result == 1) {
            return CommonResult.commonSuccessResult();
        }
        else {
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
            return CommonResult.commonSuccessResult();
        }
        else {
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/bill")
    @ApiOperation(value = "Bill - 查询账单")
    public CommonResult getBillById(@RequestParam("id") Long id) {
        Bill result = billService.getBillById(id);
        if (result != null) {
            return new CommonResult(CommonResult.SUCCESS_CODE, result, CommonResult.SUCCESS_MESSAGE);
        }
        else {
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/bill/book/{bookId}")
    @ApiOperation(value = "Bill - 查询账本账单")
    public CommonResult getBillsByBookId(@PathVariable("bookId") Long bookId) {
        List<Bill> results = billService.getBillsByBookId(bookId);
        if (results != null) {
            return new CommonResult(CommonResult.SUCCESS_CODE, results, CommonResult.FAILURE_MESSAGE);
        }
        else {
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/bill/account/{accountId}")
    @ApiOperation(value = "Bill - 查询账户账单")
    public CommonResult getBillsByAccountId(@PathVariable("accountId") Long accountId) {
        List<Bill> results = billService.getBillsByAccountId(accountId);
        if (results != null) {
            return new CommonResult(CommonResult.SUCCESS_CODE, results, CommonResult.SUCCESS_MESSAGE);
        }
        else {
            return CommonResult.commonFailureResult();
        }
    }
}
