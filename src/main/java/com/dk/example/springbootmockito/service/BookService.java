package com.dk.example.springbootmockito.service;

import com.dk.example.springbootmockito.entity.Book;
import com.dk.example.springbootmockito.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public int applyDiscountOnBook(Integer bookId,int discountRate){
        Book book=findFirstById(bookId);
        int discountedPrice=book.getPrice()-discountRate;
        return discountedPrice;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void update(Book book){
        Book persistedBook=bookRepository.findFirstByBookId(book.getBookId());
        persistedBook.setPrice(book.getPrice());
        persistedBook.setTitle(book.getTitle());
        bookRepository.save(book);
    }

    public int findNumberOfBooks() {
        return bookRepository.findAll().size();
    }

    public Book findFirstById(Integer bookId) {
        return bookRepository.findFirstByBookId(bookId);
    }

    public long calculateTotalCost(List<Integer> bookIdList) {
        return bookIdList.stream()
                .map((e) -> findFirstById(e).getPrice())
                .count();
    }

    public void deleteById(int i) {
        bookRepository.deleteByBookId(i);
    }
}
