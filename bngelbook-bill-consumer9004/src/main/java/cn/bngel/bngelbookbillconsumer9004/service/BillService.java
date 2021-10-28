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
    CommonResult saveBill(@RequestBody Bill bill);

    @DeleteMapping("/bill")
    CommonResult deleteBillById(@RequestParam("id") Long id);

    @PutMapping("/bill")
    CommonResult updateBillById(@RequestBody Bill bill);

    @GetMapping("/bill")
    CommonResult getBillById(@RequestParam("id") Long id);

    @GetMapping("/bill/book/{bookId}")
    CommonResult getBillsByBookId(@PathVariable("bookId") Long bookId);

    @GetMapping("/bill/account/{accountId}")
    CommonResult getBillsByAccountId(@PathVariable("accountId") Long accountId);
}
