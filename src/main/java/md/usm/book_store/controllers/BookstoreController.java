package md.usm.book_store.controllers;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.Bookstore;
import md.usm.book_store.services.BookstoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequestMapping("/bookstores")
@RequiredArgsConstructor
public class BookstoreController {

    private final BookstoreService bookstoreService;

    @PostMapping
    public ResponseEntity<Bookstore> saveOrUpdateBookstore(@RequestBody Bookstore bookstore){
        return new ResponseEntity<>(bookstoreService.saveOrUpdateBookstore(bookstore), HttpStatus.CREATED);
    }

    @GetMapping("/bookstore/{bookstore_id}")
    public ResponseEntity<Bookstore> getBookstoreById(@PathVariable Integer bookstore_id){
        return new ResponseEntity<>(bookstoreService.findBookstoreById(bookstore_id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Bookstore>> getAllBookstores(){
        return new ResponseEntity<>(bookstoreService.findAllBookstores(), HttpStatus.OK);
    }

    @DeleteMapping("/bookstore/{bookstore_id}")
    public void deleteBookstoreById(@PathVariable Integer bookstore_id){
        bookstoreService.deleteBookstoreById(bookstore_id);
    }

    @PostMapping("/book={book_id}/bookstore={bookstore_id}")
    public void addBookToBookstore(@PathVariable Integer book_id,
                                   @PathVariable Integer bookstore_id){
        bookstoreService.addBookToBookstore(book_id, bookstore_id);
    }

    @DeleteMapping("/book={book_id}/bookstore={bookstore_id}")
    public void deleteBookFromBookstore(@PathVariable Integer book_id,
                                        @PathVariable Integer bookstore_id){
        bookstoreService.deleteBookFromBookstore(book_id, bookstore_id);
    }
}
