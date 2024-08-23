package com.manueljsanchezg.libray_backend.controller;

import com.manueljsanchezg.libray_backend.model.Book;
import com.manueljsanchezg.libray_backend.model.BookDTO;
import com.manueljsanchezg.libray_backend.service.BookService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public List<Book> findAll() {
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookService.getBookById(id).get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody BookDTO updatedBook, @PathVariable Long id) {
        bookService.updateBook(id, updatedBook);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
