package com.polarbookshop.catalogservice.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    @Modifying
    @Transactional
    @Query("delete from book where isbn = :isbn")
    void deleteByIsbn(String isbn);
}
