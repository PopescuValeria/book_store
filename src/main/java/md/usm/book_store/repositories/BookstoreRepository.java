package md.usm.book_store.repositories;

import md.usm.book_store.models.Bookstore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookstoreRepository extends JpaRepository<Bookstore, Integer> {
}
