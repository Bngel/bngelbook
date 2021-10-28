package cn.bngel.bngelbookbookprovider8002.dao;

import cn.bngel.bngelbookcommonapi.bean.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookDao {

    Integer saveBook(@Param("book") Book book);

    Integer deleteBookById(@Param("id") Long id);

    Integer updateBookById(@Param("book") Book book);

    Book getBookById(@Param("id") Long id);

    List<Book> getBooksByUserId(@Param("userId") Long userId);
}
