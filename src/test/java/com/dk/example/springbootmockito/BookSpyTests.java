package com.dk.example.springbootmockito;

import com.dk.example.springbootmockito.entity.Book;
import com.dk.example.springbootmockito.repository.BookRepository;
import com.dk.example.springbootmockito.service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookSpyTests {

    @InjectMocks
    private BookService bookService;

    @Spy
    private BookRepository bookRepository;


    @Test
    public void testSpyOnDiscount(){
        Book book = new Book(1, "testBook", 100, new Date());
        Mockito.when(bookRepository.findFirstByBookId(1)).thenReturn(book);

        int discountedBookPrice=bookService.applyDiscountOnBook(1,50);

        assertEquals(50,discountedBookPrice);
    }
}
