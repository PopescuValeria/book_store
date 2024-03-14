package md.usm.book_store.controllers.thymeleaf;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.dto.BookBookstoreDto;
import md.usm.book_store.services.BookService;
import md.usm.book_store.services.BookstoreService;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class BookToBookstoreControllerThymeleaf {

    private final BookService bookService;
    private final BookstoreService bookstoreService;
    @Persistent
    private Integer bookstore_id;
    @Persistent
    private String bookstore_name;

    @GetMapping("/bookToBookstore")
    public String getBookToBookstorePage(Model model){
        BookBookstoreDto bookBookstoreDto = new BookBookstoreDto();
        if(bookstore_id == null){
            bookstore_id = bookstoreService.findAllBookstores().get(0).getBookstore_id();
        }
        bookstore_name = bookstoreService.findBookstoreById(bookstore_id).getName();
        model.addAttribute("bookBookstoreDto", bookBookstoreDto);
        model.addAttribute("bookstore_id", bookstore_id);
        model.addAttribute("bookList", bookService.findAllBooks());
        model.addAttribute("bookListForTable", bookstoreService.findBookstoreById(bookstore_id).getBookList());
        model.addAttribute("bookstoreName", bookstore_name);
        model.addAttribute("bookstoreList", bookstoreService.findAllBookstores());
        return "bookToBookstore";
    }

    @PostMapping("/bookToBookstore")
    public String addBookToBookstore(@ModelAttribute("bookBookstoreDto")BookBookstoreDto bookBookstoreDto){
        bookstore_id = bookBookstoreDto.getBookstore_id();
        bookstoreService.addBookToBookstore(bookBookstoreDto.getBook_id(), bookBookstoreDto.getBookstore_id());
        return "redirect:bookToBookstore";
    }

    @GetMapping("/bookToBookstore/deleteBook")
    public String deleteBookFromBookstore(@RequestParam Integer book_id){
        bookstoreService.deleteBookFromBookstore(book_id, bookstore_id);
        return "redirect:/bookToBookstore";
    }

    @GetMapping("/bookToBookstore/bookstore")
    public ModelAndView getBooksByBookstoreId(@RequestParam(name = "bookstore_id", required = false) Integer bookstore_id){
        ModelAndView modelAndView = new ModelAndView("redirect:/bookToBookstore");
        modelAndView.addObject("bookstore_id", bookstore_id);
        this.bookstore_id = bookstore_id;
        return modelAndView;
    }
}
