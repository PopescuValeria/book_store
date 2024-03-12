package md.usm.book_store.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookSearch {
    private String name;
    private String author;
    private Integer bookstore_id;
    private String bookstore_type;
    private Integer city_id;
}
