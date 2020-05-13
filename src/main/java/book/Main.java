package book;

import book.model.Book;
import com.google.inject.*;
import guice.PersistenceModule;

public class Main {
    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        BookDao bookDao = injector.getInstance(BookDao.class);

        String findIsbn="";
        System.out.println(bookDao.findByIsbn13(findIsbn));

        for (int i=0; i<1000; i++){
            Book book = BookGenerator.bookGenerate();
            bookDao.persist(book);

            if (i==999){
                findIsbn=book.getIsbn13();
            }
        }

        System.out.println("Az összes könyv:");
        bookDao.findAll().forEach(System.out::println);

        System.out.println("\nISBN:"+findIsbn);
        System.out.println(bookDao.findByIsbn13(findIsbn));
    }
}