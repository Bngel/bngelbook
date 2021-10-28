package cn.bngel.bngelbookbookprovider8006.service;

import cn.bngel.bngelbookbookprovider8006.bean.Book;

import java.util.List;

public interface BookService {

    Integer saveBook(Book book);

    Integer deleteBookById(Long id);

    Integer updateBookById(Book book);

    Book getBookById(Long id);

    List<Book> getBooksByUserId(Long userId);
}
