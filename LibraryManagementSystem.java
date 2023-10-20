import java.util.ArrayList;
import java.util.List;

abstract class AbstractItem {
    private String title;
    private String author;

    public AbstractItem(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public abstract void checkOut();

    public abstract void returnItem();

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

class Book extends AbstractItem {
    private String ISBN;
    private boolean available;

    public Book(String title, String author, String ISBN) {
        super(title, author);
        this.ISBN = ISBN;
        this.available = true;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void checkOut() {
        if (isAvailable()) {
            setAvailable(false);
            System.out.println(getTitle() + " has been checked out.");
        } else {
            System.out.println(getTitle() + " is not available for checkout.");
        }
    }

    @Override
    public void returnItem() {
        if (!isAvailable()) {
            setAvailable(true);
            System.out.println(getTitle() + " has been returned.");
        } else {
            System.out.println(getTitle() + " is already available.");
        }
    }
}

class Magazine extends AbstractItem {
    public Magazine(String title, String author) {
        super(title, author);
    }

    @Override
    public void checkOut() {
        System.out.println(getTitle() + " magazine has been checked out.");
    }

    @Override
    public void returnItem() {
        System.out.println(getTitle() + " magazine has been returned.");
    }
}

class Library {
    private List<AbstractItem> items;

    public Library() {
        this.items = new ArrayList<>();
    }

    public void addItem(AbstractItem item) {
        items.add(item);
    }

    public void removeItem(AbstractItem item) {
        items.remove(item);
    }

    public void displayAllItems() {
        for (AbstractItem item : items) {
            System.out.println("Title: " + item.getTitle());
            System.out.println("Author: " + item.getAuthor());
            if (item instanceof Book) {
                Book book = (Book) item;
                System.out.println("ISBN: " + book.getISBN());
            }
            System.out.println();
        }
    }
}

class User {
    private String name;
   private int libraryCardNumber;

    public User(String name, int libraryCardNumber) {
        this.name = name;
        this.libraryCardNumber = libraryCardNumber;
    }

    public void checkOutItem(AbstractItem item) {
        item.checkOut();
    }

    public void returnItem(AbstractItem item) {
        item.returnItem();
    }
}

public class LibraryManagementSystem{
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Animalfarm", "George Orwell", "978-0-316-76948-7");
        Book book2 = new Book("Rich Dad and Poor Dad", "Robert", "978-0-06-112008-4");
        Magazine magazine1 = new Magazine("National Geographic", "Various Authors");

        library.addItem(book1);
        library.addItem(book2);
        library.addItem(magazine1);

        User user1 = new User("sreenu", 12345);

        System.out.println("All Items in the Library:");
        library.displayAllItems();

        user1.checkOutItem(book1);
        user1.returnItem(book1);
    }
}
