package md.usm.book_store.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookBookstoreDto {
    private Integer book_id;
    private Integer bookstore_id;
}
