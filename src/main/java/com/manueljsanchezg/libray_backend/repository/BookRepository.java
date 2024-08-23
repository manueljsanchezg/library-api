package com.manueljsanchezg.libray_backend.repository;

import com.manueljsanchezg.libray_backend.model.Book;
import org.hibernate.Incubating;
import org.hibernate.Internal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
