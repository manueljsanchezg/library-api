package com.manueljsanchezg.libray_backend.controller;

import com.manueljsanchezg.libray_backend.model.Author;
import com.manueljsanchezg.libray_backend.model.AuthorDTO;
import com.manueljsanchezg.libray_backend.model.Book;
import com.manueljsanchezg.libray_backend.service.AuthorService;
import com.manueljsanchezg.libray_backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;

    @GetMapping
    public List<Author> findAll() {
        return authorService.getAllAuthors();
    }
    @GetMapping("/{id}")
    public Author findOne(@PathVariable Long id) {
        return authorService.getAuthorById(id).get();
    }
    @GetMapping("/{id}/books")
    public List<Book> findBooksByAuthor(@PathVariable Long id) {
        return authorService.getBooksByAuthor(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Author createAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.createAuthor(authorDTO);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateAuthor(@RequestBody AuthorDTO updatedAuthor, @PathVariable Long id) {
        authorService.updateAuthor(id, updatedAuthor);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
