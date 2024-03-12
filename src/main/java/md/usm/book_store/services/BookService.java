package md.usm.book_store.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.Book;
import md.usm.book_store.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book saveOrUpdateBook(Book book){
        return bookRepository.save(book);
    }

    public Book findBookById(Integer book_id){
        return bookRepository.findById(book_id).orElseGet(null);
    }

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public void deleteBookById(Integer book_id){
        bookRepository.deleteById(book_id);
    }
}
