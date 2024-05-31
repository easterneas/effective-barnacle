package sess15.security.simpleproduct;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import sess15.security.simpleproduct.entity.Book;

public class TestObjectFactory {
  public static Book createBook (){
    final Book createdBook = new Book();

    createdBook.setTitle(RandomStringUtils.randomAlphabetic(10, 30));
    createdBook.setWriter(RandomStringUtils.randomAlphabetic(3, 36));
    createdBook.setIsbn(RandomStringUtils.randomAlphabetic(16));

    return createdBook;
  }

  public static List<Book> createBooks (final Integer count) {
    final List<Book> books = new ArrayList<>();

    for (Integer i = 0; i < count; i++) {
      books.add(createBook());
    }

    return books;
  }
}
