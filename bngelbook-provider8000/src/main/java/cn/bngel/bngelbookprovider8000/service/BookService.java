package cn.bngel.bngelbookprovider8000.service;

import cn.bngel.bngelbookcommonapi.bean.Book;

import java.util.List;

public interface BookService {

    Integer saveBook(Book book);

    Integer deleteBookById(Long id);

    Integer updateBookById(Book book);

    Book getBookById(Long id);

    List<Book> getBooksByUserId(Long userId);
}