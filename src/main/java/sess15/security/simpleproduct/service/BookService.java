package sess15.security.simpleproduct.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import sess15.security.simpleproduct.entity.Book;
import sess15.security.simpleproduct.repository.BookRepository;

public class BookService {
  @Autowired
  BookRepository bookRepository;

  public List<Book> getAllBooks () {
    return bookRepository.findAll();
  }

  public Optional<Book> getBookById (Long id) {
    return bookRepository.findById(id);
  }

  public Book saveOrUpdateBook(Book bookPayload) {
    return bookRepository.save(bookPayload);
  }

  public Optional<Book> deleteBookById(Long bookId) {
    final Optional<Book> book = bookRepository.findById(bookId);

    bookRepository.delete(book.get());

    return book;
  }
}
