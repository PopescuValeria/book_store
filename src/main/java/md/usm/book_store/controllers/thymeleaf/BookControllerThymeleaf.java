package md.usm.book_store.controllers.thymeleaf;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.Book;
import md.usm.book_store.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class BookControllerThymeleaf {

    private final BookService bookService;

    @PostMapping("/books")
    public String saveOrUpdateBook(@ModelAttribute("book") Book book){
        bookService.saveOrUpdateBook(book);
        return "redirect:books";
    }

    @GetMapping("/books/editBook")
    public ModelAndView getBookById(@RequestParam Integer book_id){
        ModelAndView modelAndView = new ModelAndView("books");
        Book book = bookService.findBookById(book_id);
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @GetMapping(value = "/books")
    public String getAllBooks(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("bookList", bookService.findAllBooks());
        return "books";
    }

    @GetMapping("/books/deleteBook")
    public String deleteBookById(@RequestParam Integer book_id){
        bookService.deleteBookById(book_id);
        return "redirect:/books";
    }
}
