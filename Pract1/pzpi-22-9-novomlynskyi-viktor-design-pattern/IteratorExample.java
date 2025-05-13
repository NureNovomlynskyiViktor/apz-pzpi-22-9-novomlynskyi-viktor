//Iterator Pattern — приклад на Java
//Інтерфейс та колекція
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

class Book {
    String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class BookCollection implements Iterable<Book> {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Iterator<Book> iterator() {
        return books.iterator();
    }
}

//Використання
public class Main {
    public static void main(String[] args) {
        BookCollection library = new BookCollection();

        library.addBook(new Book("Design Patterns"));
        library.addBook(new Book("Clean Code"));
        library.addBook(new Book("Refactoring"));

        for (Book book : library) {
            System.out.println("Книга: " + book.getTitle());
        }
    }
}

