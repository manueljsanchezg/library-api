package com.manueljsanchezg.libray_backend.service;

import com.manueljsanchezg.libray_backend.model.Author;
import com.manueljsanchezg.libray_backend.model.AuthorDTO;
import com.manueljsanchezg.libray_backend.model.Book;
import com.manueljsanchezg.libray_backend.repository.AuthorRepository;
import com.manueljsanchezg.libray_backend.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return  authorRepository.findById(id);
    }

    public List<Book> getBooksByAuthor(Long authorId){
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            return author.get().getBooks();
        } else {
            throw new EntityNotFoundException("Author not found with id " + authorId);
        }
    }

    public Author createAuthor(AuthorDTO authorDTO) {
        Author existingAuthor = authorRepository.findByName(authorDTO.getName());
        if (existingAuthor != null) {
            return existingAuthor;
        }
        Author newAuthor = new Author();
        newAuthor.setName(authorDTO.getName());
        newAuthor.setBirthDate(authorDTO.getBirthDate());
        return authorRepository.save(newAuthor);

    }

    public Author updateAuthor(Long id, AuthorDTO authorDTO) {
        Optional<Author> existingAuthor = authorRepository.findById(id);

        if (existingAuthor.isPresent()) {
            Author author = existingAuthor.get();
            author.setName(authorDTO.getName());
            author.setBirthDate(authorDTO.getBirthDate());
            return authorRepository.save(author);
        } else {
            throw new EntityNotFoundException("Author not found with id " + id);
        }
    }

    public void deleteAuthor(Long id) {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isPresent()) {
            Author authorToDelete = author.get();

            List<Book> books = authorToDelete.getBooks();
            for (Book book : books) {
                book.setAuthor(null);
                bookRepository.save(book);
            }

            authorRepository.delete(authorToDelete);
        } else {
            throw new EntityNotFoundException("Author not found with id " + id);
        }
    }
}
