package cn.bngel.bngelbookbookprovider8002.controller;

import cn.bngel.bngelbookcommonapi.bean.Book;
import cn.bngel.bngelbookbookprovider8002.service.BookService;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "账本模块")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    @ApiOperation(value = "Book - 创建账本")
    public CommonResult saveBook(@RequestBody Book book) {
        Integer result = bookService.saveBook(book);
        if (result == 1) {
            log.info("创建账本: [" + book + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("创建账本: [" + book + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @DeleteMapping("/book")
    @ApiOperation(value = "Book - 删除账本")
    public CommonResult deleteBookById(@RequestParam("id") Long id) {
        Integer result = bookService.deleteBookById(id);
        if (result == 1) {
            log.info("删除账本: [" + id + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("删除账本: [" + id + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @PutMapping("/book")
    @ApiOperation(value = "Book - 修改账本信息")
    public CommonResult updateBookById(@RequestBody Book book) {
        if (book.getId() == null) {
            log.info("修改账本: [" + book + "] 失败");
            return new CommonResult(CommonResult.NULL_ID_ERROR_CODE, CommonResult.NULL_ID_ERROR_MESSAGE);
        }
        Integer result = bookService.updateBookById(book);
        if (result == 1) {
            log.info("修改账本: [" + book + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("修改账本: [" + book + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/book")
    @ApiOperation(value = "Book - 查询账本")
    public CommonResult getBookById(@RequestParam("id") Long id) {
        Book result = bookService.getBookById(id);
        if (result != null) {
            log.info("查询账本: [" + id + "] 成功");
            return new CommonResult(CommonResult.SUCCESS_CODE, result, CommonResult.SUCCESS_MESSAGE);
        }
        else {
            log.info("查询账本: [" + id + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/book/{userId}")
    @ApiOperation(value = "Book - 查询用户账本")
    public CommonResult getBooksByUserId(@PathVariable("userId") Long userId) {
        List<Book> results = bookService.getBooksByUserId(userId);
        if (results != null) {
            log.info("查询用户账本: [" + userId + "] 成功");
            return new CommonResult(CommonResult.SUCCESS_CODE, results, CommonResult.SUCCESS_MESSAGE);
        }
        else {
            log.info("查询用户账本: [" + userId + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }
}
