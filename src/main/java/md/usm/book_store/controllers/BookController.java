package md.usm.book_store.controllers;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.Book;
import md.usm.book_store.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> saveOrUpdateBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.saveOrUpdateBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/book/{book_id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer book_id){
        return new ResponseEntity<>(bookService.findBookById(book_id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @DeleteMapping("/book/{book_id}")
    public void deleteBookById(@PathVariable Integer book_id){
        bookService.deleteBookById(book_id);
    }
}
