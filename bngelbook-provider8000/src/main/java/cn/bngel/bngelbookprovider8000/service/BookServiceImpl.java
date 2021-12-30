package cn.bngel.bngelbookprovider8000.service;

import cn.bngel.bngelbookcommonapi.bean.Book;
import cn.bngel.bngelbookprovider8000.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;


    @Override
    public Integer saveBook(Book book) {
        return bookDao.saveBook(book);
    }

    @Override
    public Integer deleteBookById(Long id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public Integer updateBookById(Book book) {
        return bookDao.updateBookById(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> getBooksByUserId(Long userId) {
        return bookDao.getBooksByUserId(userId);
    }
}
