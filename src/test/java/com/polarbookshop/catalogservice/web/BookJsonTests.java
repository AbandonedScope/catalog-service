package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTests {
    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = Book.of("1234567890", "Title", "Author", "Publisher", 9.90);
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.id")
                .isEqualTo(book.id());
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
        assertThat(jsonContent).extractingJsonPathStringValue("@.publisher")
                .isEqualTo(book.publisher());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.version")
                .isEqualTo(book.version());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
      {
        "id": null,
        "isbn": "1234567890",
        "title": "Title",
        "author": "Author",
        "publisher": "Publisher",
        "price": 9.90,
        "version": 0
      }
      """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(Book.of("1234567890", "Title", "Author", "Publisher", 9.90));
    }

}
