package md.usm.book_store.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bookstore {
    @Id
    private Integer bookstore_id;
    private String name;
    private Integer city_id;
    private String street;
    private String bookstoreType;
    @ManyToMany
    @JoinTable(
            name = "book_to_bookstore",
            joinColumns = {@JoinColumn(name = "bookstore_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> bookList;

    public void addBook(Book book){
        if(Objects.isNull(bookList)){
            bookList = new ArrayList<>();
        }
        bookList.add(book);
    }

    public void deleteBook(Book book){
        bookList.remove(book);
        book.getBookstoreList().remove(this);
    }
}
