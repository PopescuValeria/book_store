package md.usm.book_store.repositories;

import md.usm.book_store.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
