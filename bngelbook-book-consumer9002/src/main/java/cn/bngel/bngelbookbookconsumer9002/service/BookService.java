package cn.bngel.bngelbookbookconsumer9002.service;


import cn.bngel.bngelbookcommonapi.bean.Book;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient("bngelbook-book-provider")
public interface BookService {

    @PostMapping("/book")
    CommonResult saveBook(@RequestBody Book book);

    @DeleteMapping("/book")
    CommonResult deleteBookById(@RequestParam("id") Long id);

    @PutMapping("/book")
    CommonResult updateBookById(@RequestBody Book book);

    @GetMapping("/book")
    CommonResult getBookById(@RequestParam("id") Long id);

    @GetMapping("/book/{userId}")
    CommonResult getBooksByUserId(@PathVariable("userId") Long userId);
}
