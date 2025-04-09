package com.example.demo.controller;

import com.example.demo.dto.BookRequest;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
@Tag(name = "Books", description = "Operations related to books")
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class BookController {

    private final BookService bookService;
    @Operation(summary = "Create a new book")
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.create(request));
    }
    @Operation(summary = "Get all books paginated")
    @GetMapping("/paginated")
    public ResponseEntity<Page<Book>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(bookService.findAllPaginated(page, size));
    }
    @Operation(summary = "Get all books")
    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.findAll());
    }
    @Operation(summary = "Get a book by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }
    @Operation(summary = "Update a book")
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.update(id, request));
    }
    @Operation(summary = "Delete a book")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
    @Operation(summary = "Filter books by author")
    @GetMapping("/filter/author")
    public ResponseEntity<List<Book>> filterByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(bookService.filterByAuthor(author));
    }
    @Operation(summary = "Filter books by category")
    @GetMapping("/filter/category")
    public ResponseEntity<List<Book>> filterByCategory(@RequestParam String category) {
        return ResponseEntity.ok(bookService.filterByCategory(category));
    }
    @Operation(summary = "Filter books by rating")
    @GetMapping("/filter/rating")
    public ResponseEntity<List<Book>> filterByRating(@RequestParam Double rating) {
        return ResponseEntity.ok(bookService.filterByRating(rating));
    }
    @Operation(summary = "Search books by title")
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchByTitle(title));
    }
    @Operation(summary = "Sort books by price or rating")
    @GetMapping("/sort")
    public ResponseEntity<List<Book>> sortBooks(
            @RequestParam("by") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        return ResponseEntity.ok(bookService.sortBy(sortBy, order));
    }


}
