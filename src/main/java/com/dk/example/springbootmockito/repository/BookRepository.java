package com.dk.example.springbootmockito.repository;

import com.dk.example.springbootmockito.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Override
   public List<Book> findAll();

    Book findFirstByBookId(Integer bookId);

    void deleteByBookId(Integer bookId);
}
