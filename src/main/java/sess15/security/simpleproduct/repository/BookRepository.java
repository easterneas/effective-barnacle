package sess15.security.simpleproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sess15.security.simpleproduct.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
