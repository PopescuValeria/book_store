package md.usm.book_store.controllers.thymeleaf;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.constants.BookstoreTypes;
import md.usm.book_store.models.Book;
import md.usm.book_store.models.Bookstore;
import md.usm.book_store.models.dto.BookSearch;
import md.usm.book_store.services.BookService;
import md.usm.book_store.services.BookstoreService;
import md.usm.book_store.services.CityService;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class SearchBooksControllerThymeleaf {

    private final BookService bookService;
    private final CityService cityService;
    private final BookstoreService bookstoreService;
    @Persistent
    private List<Book> bookList;

    @GetMapping("/search_books")
    public String getSearchBooksPage(Model model){
        BookSearch bookSearch = new BookSearch();
        if(bookList == null){
            bookList = new ArrayList<>();
        }
        model.addAttribute("bookSearch", bookSearch);
        model.addAttribute("bookstoreTypes", BookstoreTypes.getAllBookstoreTypes());
        model.addAttribute("bookstoreList", bookstoreService.findAllBookstores());
        model.addAttribute("cityList", cityService.findAllCities());
        model.addAttribute("bookList", bookList);
        return "search_books";
    }

    @PostMapping("/search_books")
    public String searchRequest(@ModelAttribute("bookSearch") BookSearch bookSearch){
//        if(bookList.isEmpty()){
            bookList = bookService.findAllBooks();
//        }
        List<Bookstore> bookstoreList = bookstoreService.findAllBookstores();

        bookList = bookList.stream()
                        .filter(book -> book.getName().toLowerCase().contains(bookSearch.getName().toLowerCase()))
                        .filter(book -> book.getAuthor().toLowerCase().contains(bookSearch.getAuthor().toLowerCase()))
                        .collect(Collectors.toList());

        if(!bookSearch.getBookstore_id().equals(0)){
            bookList = bookList.stream()
                    .filter(book -> book.getBookstoreList().contains(bookstoreService.findBookstoreById(bookSearch.getBookstore_id())))
                    .collect(Collectors.toList());
        }

        if(!bookSearch.getBookstore_type().equals("0")){
            bookstoreList = bookstoreList.stream()
                                .filter(bookstore -> bookstore.getBookstoreType().equals(bookSearch.getBookstore_type()))
                                .collect(Collectors.toList());

//            bookList = bookList.stream()
//                        .filter(book -> new HashSet<>(book.getBookstoreList()).containsAll(finalBookstoreList))
//                        .collect(Collectors.toList());
            List<Book> finalBookList = new ArrayList<>();
            for(Book book : bookList){
                for(Bookstore bookstore : bookstoreList){
                    if(book.getBookstoreList().contains(bookstore)){
                        finalBookList.add(book);
                        break;
                    }
                }
            }
            bookList = finalBookList;
        }

        if(!bookSearch.getCity_id().equals(0)){
            bookstoreList = bookstoreList.stream()
                    .filter(bookstore -> bookstore.getCity_id().equals(bookSearch.getCity_id()))
                    .collect(Collectors.toList());

            List<Book> finalBookList = new ArrayList<>();
            for(Book book : bookList){
                for(Bookstore bookstore : bookstoreList){
                    if(book.getBookstoreList().contains(bookstore)){
                        finalBookList.add(book);
                        break;
                    }
                }
            }
            bookList = finalBookList;
        }
        return "redirect:search_books";
    }
}
