package com.dk.example.springbootmockito;


import com.dk.example.springbootmockito.entity.Book;
import com.dk.example.springbootmockito.repository.BookRepository;
import com.dk.example.springbootmockito.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookTests {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    @Test
    public void testFBookRetrieveTestakeWithEmptyList() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(bookService.findNumberOfBooks(), 0);
    }

    @Test
    public void testFBookRetrieveTestakeWithEmptyList2() {
        doReturn(Collections.emptyList()).when(bookRepository.findAll());
        assertEquals(bookService.findNumberOfBooks(), 0);
    }

    @Test
    public void testBookSave() {
        Book testBook = new Book();
        bookService.save(testBook);
        verify(bookRepository, Mockito.times(1)).save(testBook);
    }

    @Test
    public void doNothing() {
        Integer bookId = 1;
        Mockito.doNothing().when(bookRepository).deleteByBookId(bookId);
        bookService.deleteById(bookId);
        verify(bookRepository, Mockito.times(1)).deleteByBookId(bookId);
    }

    @Test
    public void checkOrder() {
        Book book = new Book(1);

        Mockito.when(bookRepository.findFirstByBookId(book.getBookId()))
                .thenReturn(book);

        bookService.update(book);

        InOrder inOrder = Mockito.inOrder(bookRepository);

        inOrder.verify(bookRepository)
                .findFirstByBookId(book.getBookId());

        inOrder.verify(bookRepository)
                .save(book);
    }

    @Test
    public void captArgument() {
        Book book = new Book(1);

        bookService.save(book);

        verify(bookRepository).save(bookArgumentCaptor.capture());

        Book capturedBook=bookArgumentCaptor.getValue();
        assertEquals(1,capturedBook.getBookId());
    }
}
