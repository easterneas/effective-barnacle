package sess15.security.simpleproduct;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import sess15.security.simpleproduct.entity.Book;
import sess15.security.simpleproduct.repository.BookRepository;
import sess15.security.simpleproduct.service.BookService;

@RunWith(SpringRunner.class)
public class BookServiceTest {

  @InjectMocks
  private BookService bookService = new BookService();

  @Mock
  private BookRepository bookRepository;

  @Before
  public void setup(){
    MockitoAnnotations.openMocks(this);
    ReflectionTestUtils.setField(bookService, "bookRepository", bookRepository);
  }

  @After
  public void cleanup(){
    // bersih-bersih setelah testing
  }

  // ? tambahkan unit testing di sini

  @Test
  public void findAllBooks(){
    System.out.println("Memulai test: findAllBooks");

    // siapkan "data palsu"
    final List<Book> mockBooks = TestObjectFactory.createBooks(10);

    Mockito.when(bookRepository.findAll()).thenReturn(mockBooks);

    // test dimulai

    final List<Book> actualBooks = bookService.getAllBooks();

    // tes hasil yang didapat (assertion)

    //                       data yang diambil dari BookService
    //                       v
    //                       v                   data yang kita bandingkan dengan mockBooks
    //                       v                   melalui Matchers.equalTo()
    //                       v                   v
    MatcherAssert.assertThat(actualBooks.size(), Matchers.equalTo(mockBooks.size()));
  }

  @Test
  public void findBookById(){
    System.out.println("Memulai test: findBookById");

    final Long testId = new Random().nextLong();
    final Book mockBook = TestObjectFactory.createBook();

    Mockito
      .when(bookRepository.findById(testId))
      .thenReturn(Optional.of(mockBook));

    final Book actualBook = bookService.getBookById(testId).get();

    MatcherAssert.assertThat(actualBook.getId(), Matchers.equalTo(mockBook.getId()));
    MatcherAssert.assertThat(actualBook.getIsbn(), Matchers.equalTo(mockBook.getIsbn()));
    MatcherAssert.assertThat(actualBook.getTitle(), Matchers.equalTo(mockBook.getTitle()));
    MatcherAssert.assertThat(actualBook.getWriter(), Matchers.equalTo(mockBook.getWriter()));
  }

  @Test
  public void findBookByIdThatReturnsNull() throws Exception {
    System.out.println("Memulai test: findBookByIdThatReturnsNull");

    Long id = new Random().nextLong();

    Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());

    final Optional<Book> actualBook = bookService.getBookById(id);

    MatcherAssert.assertThat(actualBook, Matchers.equalTo(Optional.empty()));
  }

  @Test
  public void createOrUpdateBook() {
    System.out.println("Memulai test: createOrUpdateBook");

    final Book newMockBook = TestObjectFactory.createBook();

    Mockito.when(bookRepository.save(newMockBook)).thenReturn(newMockBook);

    final Book newActualBook = bookService.saveOrUpdateBook(newMockBook);

    MatcherAssert.assertThat(newActualBook, Matchers.notNullValue());
  }

  @Test
  public void deleteBook(){
    System.out.println("Memulai test: deleteBook");

    final Long id = new Random().nextLong();
    final Book mockBook = TestObjectFactory.createBook();

    Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(mockBook));
    Mockito.doNothing().when(bookRepository).delete(mockBook);

    bookService.deleteBookById(id);

    Mockito.verify(bookRepository, times(1)).delete(mockBook);
  }
}
