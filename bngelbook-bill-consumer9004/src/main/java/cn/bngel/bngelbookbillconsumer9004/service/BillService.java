package cn.bngel.bngelbookbillconsumer9004.service;

import cn.bngel.bngelbookcommonapi.bean.Bill;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient("bngelbook-bill-provider")
public interface BillService {

    @PostMapping("/bill")
    CommonResult<Boolean> saveBill(@RequestBody Bill bill);

    @DeleteMapping("/bill")
    CommonResult<Boolean> deleteBillById(@RequestParam("id") Long id);

    @PutMapping("/bill")
    CommonResult<Boolean> updateBillById(@RequestBody Bill bill);

    @GetMapping("/bill")
    CommonResult<Bill> getBillById(@RequestParam("id") Long id);

    @GetMapping("/bill/book/{bookId}")
    CommonResult<List<Bill>> getBillsByBookId(@PathVariable("bookId") Long bookId);

    @GetMapping("/bill/account/{accountId}")
    CommonResult<List<Bill>> getBillsByAccountId(@PathVariable("accountId") Long accountId);

    @GetMapping("/bill/book/{bookId}/{month}")
    CommonResult<List<Bill>> getMonthBillsByBookId(@PathVariable("bookId") Long bookId,
                                                          @PathVariable("month") Integer month);
}
