package md.usm.book_store.controllers.thymeleaf;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.constants.BookstoreTypes;
import md.usm.book_store.models.Bookstore;
import md.usm.book_store.models.City;
import md.usm.book_store.services.BookstoreService;
import md.usm.book_store.services.CityService;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookstoreControllerThymeleaf {

    private final BookstoreService bookstoreService;
    private final CityService cityService;
    @Persistent
    private Integer city_id;
    @Persistent
    private List<City> cityList;

    @PostMapping("/bookstores")
    public String saverOrUpdateBookstore(@ModelAttribute("bookstore") Bookstore bookstore){
        bookstoreService.saveOrUpdateBookstore(bookstore);
        return "redirect:bookstores";
    }

    @GetMapping("/bookstores")
    public String getAllBookstores(Model model){
        Bookstore bookstore = new Bookstore();
        if(cityList == null){
            cityList = cityService.findAllCities();
        }
        model.addAttribute("bookstore", bookstore);
        model.addAttribute("cityList", cityList);
        if(city_id == null){
            city_id = cityList.get(0).getCity_id();
        }
        model.addAttribute("city_id", city_id);
        model.addAttribute("bookstoreTypes", BookstoreTypes.getAllBookstoreTypes());
        model.addAttribute("bookstoreList", bookstoreService.findAllBookstores());
        return "bookstores";
    }

    @GetMapping("/bookstores/editBookstore")
    public ModelAndView getBookstoreById(@RequestParam Integer bookstore_id){
        ModelAndView modelAndView = new ModelAndView("bookstores");
        Bookstore bookstore = bookstoreService.findBookstoreById(bookstore_id);
        modelAndView.addObject("bookstoreType", bookstore.getBookstoreType());
        modelAndView.addObject("bookstoreTypes", BookstoreTypes.getAllBookstoreTypes());
        modelAndView.addObject("cityList", cityList);
        city_id = bookstore.getCity_id();
        modelAndView.addObject("bookstore", bookstore);
        return modelAndView;
    }

    @GetMapping("/bookstores/deleteBookstore")
    public String deleteBookstoreById(@RequestParam Integer bookstore_id){
        bookstoreService.deleteBookstoreById(bookstore_id);
        return "redirect:/bookstore";
    }
}
