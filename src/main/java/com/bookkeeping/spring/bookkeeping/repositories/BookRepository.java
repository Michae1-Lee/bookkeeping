package com.bookkeeping.spring.bookkeeping.repositories;



import com.bookkeeping.spring.bookkeeping.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByperson_id(int person_id);
    List<Book> findByTitleStartingWith(String prefix);
    List<Book> findByTitleStartingWithIgnoreCase(String prefix);
}
