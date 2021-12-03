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
    public CommonResult<Boolean> saveBill(@RequestBody Bill bill){
        CommonResult<Boolean> result = billService.saveBill(bill);
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
    public CommonResult<Boolean> deleteBillById(@RequestParam("id") Long id) {
        CommonResult<Boolean> result = billService.deleteBillById(id);
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
    public CommonResult<Boolean> updateBillById(@RequestBody Bill bill) {
        CommonResult<Boolean> result = billService.updateBillById(bill);
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
    public CommonResult<Bill> getBillById(@RequestParam("id") Long id) {
        CommonResult<Bill> result = billService.getBillById(id);
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
    public CommonResult<List<Bill>> getBillsByBookId(@PathVariable("bookId") Long bookId) {
        CommonResult<List<Bill>> result = billService.getBillsByBookId(bookId);
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
    public CommonResult<List<Bill>> getBillsByAccountId(@PathVariable("accountId") Long accountId) {
        CommonResult<List<Bill>> result = billService.getBillsByAccountId(accountId);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询账户账单: [" + accountId + "] 成功");
        }
        else {
            log.info("查询账户账单: [" + accountId + "] 失败");
        }
        return result;
    }

    @GetMapping("/bill/book/{bookId}/{month}")
    @ApiOperation(value = "Bill - 查询账本月账单")
    public CommonResult<List<Bill>> getMonthBillsByBookId(@PathVariable("bookId") Long bookId,
                                                          @PathVariable("month") Integer month) {
        CommonResult<List<Bill>> results = billService.getMonthBillsByBookId(bookId,month);
        if (results != null) {
            log.info("查询账本" + month +"月账单: [" + bookId + "] 成功");
        }
        else {
            log.info("查询账本" + month +"月账单: [" + bookId + "] 失败");
        }
        return results;
    }
}
