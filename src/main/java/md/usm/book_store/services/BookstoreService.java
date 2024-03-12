package md.usm.book_store.services;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.Book;
import md.usm.book_store.models.Bookstore;
import md.usm.book_store.repositories.BookstoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookstoreService {

    private final BookstoreRepository bookstoreRepository;
    private final BookService bookService;

    public Bookstore saveOrUpdateBookstore(Bookstore bookstore){
        return bookstoreRepository.save(bookstore);
    }

    public Bookstore findBookstoreById(Integer bookstore_id){
        return bookstoreRepository.findById(bookstore_id).orElseGet(null);
    }

    public List<Bookstore> findAllBookstores(){
        return bookstoreRepository.findAll();
    }

    public void deleteBookstoreById(Integer bookstore_id){
        bookstoreRepository.deleteById(bookstore_id);
    }

    public void addBookToBookstore(Integer book_id, Integer bookstore_id){
        Bookstore bookstore = findBookstoreById(bookstore_id);
        Book book = bookService.findBookById(book_id);

        bookstore.addBook(book);
        saveOrUpdateBookstore(bookstore);
    }

    public void deleteBookFromBookstore(Integer book_id, Integer bookstore_id){
        Bookstore bookstore = findBookstoreById(bookstore_id);
        Book book = bookService.findBookById(book_id);

        bookstore.deleteBook(book);
        saveOrUpdateBookstore(bookstore);
    }

    public List<Bookstore> getBookstoreListByBook(Book book){
        return book.getBookstoreList();
    }
}
