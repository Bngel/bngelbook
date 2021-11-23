package cn.bngel.bngelbookbookconsumer9002.service;


import cn.bngel.bngelbookcommonapi.bean.Book;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient("bngelbook-book-provider")
public interface BookService {

    @PostMapping("/book")
    CommonResult<Boolean> saveBook(@RequestBody Book book);

    @DeleteMapping("/book")
    CommonResult<Boolean> deleteBookById(@RequestParam("id") Long id);

    @PutMapping("/book")
    CommonResult<Boolean> updateBookById(@RequestBody Book book);

    @GetMapping("/book")
    CommonResult<Book> getBookById(@RequestParam("id") Long id);

    @GetMapping("/book/{userId}")
    CommonResult<List<Book>> getBooksByUserId(@PathVariable("userId") Long userId);
}
