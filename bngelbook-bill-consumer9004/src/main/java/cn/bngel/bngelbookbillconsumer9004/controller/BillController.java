package cn.bngel.bngelbookbillconsumer9004.controller;

import cn.bngel.bngelbookbillconsumer9004.service.BillService;
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
@RequestMapping("/consumer")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/bill")
    @ApiOperation(value = "Bill - 创建账单")
    public CommonResult saveBill(@RequestBody Bill bill){
        CommonResult result = billService.saveBill(bill);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("创建账单: [" + bill + "] 成功");
        }
        else {
            log.info("创建账单: [" + bill + "] 失败");
        }
        return result;
    }

    @DeleteMapping("/bill")
    @ApiOperation(value = "Bill - 删除账单")
    public CommonResult deleteBillById(@RequestParam("id") Long id) {
        CommonResult result = billService.deleteBillById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("删除账单: [" + id + "] 成功");
        }
        else {
            log.info("删除账单: [" + id + "] 失败");
        }
        return result;
    }

    @PutMapping("/bill")
    @ApiOperation(value = "Bill - 更改账单")
    public CommonResult updateBillById(@RequestBody Bill bill) {
        CommonResult result = billService.updateBillById(bill);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("更改账单信息: [" + bill + "] 成功");
        }
        else {
            log.info("更改账单信息: [" + bill + "] 失败");
        }
        return result;
    }

    @GetMapping("/bill")
    @ApiOperation(value = "Bill - 查询账单")
    public CommonResult getBillById(@RequestParam("id") Long id) {
        CommonResult result = billService.getBillById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询账单: [" + id + "] 成功");
        }
        else {
            log.info("查询账单: [" + id + "] 失败");
        }
        return result;
    }

    @GetMapping("/bill/book/{bookId}")
    @ApiOperation(value = "Bill - 查询账本账单")
    public CommonResult getBillsByBookId(@PathVariable("bookId") Long bookId) {
        CommonResult result = billService.getBillsByBookId(bookId);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询账本账单: [" + bookId + "] 成功");
        }
        else {
            log.info("查询账本账单: [" + bookId + "] 失败");
        }
        return result;
    }

    @GetMapping("/bill/account/{accountId}")
    @ApiOperation(value = "Bill - 查询账户账单")
    public CommonResult getBillsByAccountId(@PathVariable("accountId") Long accountId) {
        CommonResult result = billService.getBillsByAccountId(accountId);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询账户账单: [" + accountId + "] 成功");
        }
        else {
            log.info("查询账户账单: [" + accountId + "] 失败");
        }
        return result;
    }
}