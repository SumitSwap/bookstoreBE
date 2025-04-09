package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequest {
    private String title;
    private String author;
    private String category;
    private Double price;
    private Double rating;
    private LocalDate publishedDate;
}
