package com.example.demo.service;

import com.example.demo.dto.BookRequest;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book create(BookRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .category(request.getCategory())
                .price(request.getPrice())
                .rating(request.getRating())
                .publishedDate(request.getPublishedDate())
                .build();
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book update(Long id, BookRequest request) {
        Book book = findById(id);
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setCategory(request.getCategory());
        book.setPrice(request.getPrice());
        book.setRating(request.getRating());
        book.setPublishedDate(request.getPublishedDate());
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> filterByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> filterByCategory(String category) {
        return bookRepository.findByCategoryContainingIgnoreCase(category);
    }

    public Page<Book> findAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }

    public List<Book> sortBy(String sortBy, String order) {
        Sort sort;

        if (order.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        return bookRepository.findAll(sort);
    }

    public List<Book> filterByRating(Double rating) {
        return bookRepository.findByRatingGreaterThanEqual(rating);
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}
