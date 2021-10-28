package cn.bngel.bngelbookbookconsumer9002.controller;

import cn.bngel.bngelbookbookconsumer9002.service.BookService;
import cn.bngel.bngelbookcommonapi.bean.Book;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    @ApiOperation(value = "Book - 创建账本")
    public CommonResult saveBook(@RequestBody Book book) {
        CommonResult result = bookService.saveBook(book);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("创建账本: [" + book + "] 成功");
        }
        else {
            log.info("创建账本: [" + book + "] 失败");
        }
        return result;
    }

    @DeleteMapping("/book")
    @ApiOperation(value = "Book - 删除账本")
    public CommonResult deleteBookById(@RequestParam("id") Long id) {
        CommonResult result = bookService.deleteBookById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("删除账本: [" + id + "] 成功");
        }
        else {
            log.info("删除账本: [" + id + "] 失败");
        }
        return result;
    }

    @PutMapping("/book")
    @ApiOperation(value = "Book - 修改账本信息")
    public CommonResult updateBookById(@RequestBody Book book) {
        CommonResult result = bookService.updateBookById(book);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("修改账本: [" + book + "] 成功");
        }
        else {
            log.info("修改账本: [" + book + "] 失败");
        }
        return result;
    }

    @GetMapping("/book")
    @ApiOperation(value = "Book - 查询账本")
    public CommonResult getBookById(@RequestParam("id") Long id) {
        CommonResult result = bookService.getBookById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询账本: [" + id + "] 成功");
        }
        else {
            log.info("查询账本: [" + id + "] 失败");
        }
        return result;
    }

    @GetMapping("/book/{userId}")
    @ApiOperation(value = "Book - 查询用户账本")
    public CommonResult getBooksByUserId(@PathVariable("userId") Long userId) {
        CommonResult result = bookService.getBooksByUserId(userId);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询用户账本: [" + userId + "] 成功");
        }
        else {
            log.info("查询用户账本: [" + userId + "] 失败");
        }
        return result;
    }
}
