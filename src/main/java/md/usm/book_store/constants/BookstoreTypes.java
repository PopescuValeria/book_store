package md.usm.book_store.constants;

import java.util.Arrays;
import java.util.List;

public class BookstoreTypes {
    private static final String LIBRARY = "library";
    private static final String BOOKSTORE = "bookstore";

    public static List<String> getAllBookstoreTypes(){
        return Arrays.asList(LIBRARY, BOOKSTORE);
    }
}
