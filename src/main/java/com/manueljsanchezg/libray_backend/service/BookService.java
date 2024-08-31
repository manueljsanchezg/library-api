package com.manueljsanchezg.libray_backend.service;

import com.manueljsanchezg.libray_backend.model.Author;
import com.manueljsanchezg.libray_backend.model.Book;
import com.manueljsanchezg.libray_backend.model.BookDTO;
import com.manueljsanchezg.libray_backend.repository.AuthorRepository;
import com.manueljsanchezg.libray_backend.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
         Optional<Book> book = bookRepository.findById(id);
         if(book.isPresent()){
             return book;
         } else {
             throw new EntityNotFoundException("Book not found with id " + id);
         }
    }

    public Book createBook(BookDTO bookDTO) {
        Author author = authorRepository.findByName(bookDTO.getAuthorName());
        if (author == null) {
            author = new Author();
            author.setName(bookDTO.getAuthorName());
            authorRepository.save(author);
        }
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setYear(bookDTO.getYear());
        book.setGenre(bookDTO.getGenre());
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(bookDTO.getTitle());
            book.setDescription(bookDTO.getDescription());
            book.setYear(bookDTO.getYear());
            book.setGenre(bookDTO.getGenre());

            Author author = authorRepository.findByName(bookDTO.getAuthorName());
            if (author == null) {
                author = new Author();
                author.setName(bookDTO.getAuthorName());
                authorRepository.save(author);
            }
            book.setAuthor(author);
            return bookRepository.save(book);
        } else {
            throw new EntityNotFoundException("Book not found with id " + id);
        }
    }

    public void deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
        } else {
            throw new EntityNotFoundException("Book not found with id " + id);
        }
    }
}
