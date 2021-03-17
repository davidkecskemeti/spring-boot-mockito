package com.dk.example.springbootmockito.entity;

import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Book {

    public Book(Integer bookId) {
        this.bookId = bookId;
    }

    public Book() {
    }

    public Book(Integer bookId, String title, Integer price, Date created) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.created = created;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;
    private String title;
    private Integer price;
    private Date created;

}
