package com.polarbookshop.catalogservice.domain;

import org.springframework.data.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.Instant;

public record Book(
        @Id
        Long id,
        @NotBlank(message = "The book The book ISBN must be defined.")
        @Pattern(
                regexp = "^(\\d{10}|\\d{13})$",
                message = "The ISBN format must be valid."
        )
        String isbn,
        @NotBlank(message = "The book title must be defined.")
        String title,
        @NotBlank(message = "The book author must be defined.")
        String author,

        String publisher,
        @NotNull(message = "The book price must be defined.")
        @Positive(message = "The book price must be greater than zero.")
        Double price,
        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate,
        @CreatedBy
        String createdBy,
        @LastModifiedBy
        String lastModifiedBy,
        @Version
        int version
) {
        public static Book of(String isbn, String title, String author, String publisher, Double price) {
                return new Book(null, isbn, title, author, publisher, price,null, null, null, null, 0);
        }
}
