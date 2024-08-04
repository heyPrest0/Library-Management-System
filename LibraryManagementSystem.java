import java.util.ArrayList;
import java.util.List;

// Abstract class Item
abstract class Item {
    private String title;
    private int year;

    public Item(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public abstract void printDetails();
}

// Derived class Book
class Book extends Item {
    private String author;
    private String ISBN;

    public Book(String title, int year, String author, String ISBN) {
        super(title, year);
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    @Override
    public void printDetails() {
        System.out.println("Book: " + getTitle() + ", Year: " + getYear() + ", Author: " + author + ", ISBN: " + ISBN);
    }
}

// Derived class Magazine
class Magazine extends Item {
    private int issueNumber;

    public Magazine(String title, int year, int issueNumber) {
        super(title, year);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public void printDetails() {
        System.out.println("Magazine: " + getTitle() + ", Year: " + getYear() + ", Issue Number: " + issueNumber);
    }
}

// Derived class DVD
class DVD extends Item {
    private String director;
    private int duration; // in minutes

    public DVD(String title, int year, String director, int duration) {
        super(title, year);
        this.director = director;
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public void printDetails() {
        System.out.println("DVD: " + getTitle() + ", Year: " + getYear() + ", Director: " + director + ", Duration: " + duration + " minutes");
    }
}

// Interface Borrowable
interface Borrowable {
    void checkOut();
    void returnItem();
}

// Library class
class Library {
    private List<Item> items = new ArrayList<>();
    private List<Borrowable> borrowedItems = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void borrowItem(Borrowable item) {
        borrowedItems.add(item);
        item.checkOut();
    }

    public void returnItem(Borrowable item) {
        borrowedItems.remove(item);
        item.returnItem();
    }

    public void printLibraryItems() {
        for (Item item : items) {
            item.printDetails();
        }
    }

    public void printBorrowedItems() {
        for (Borrowable item : borrowedItems) {
            if (item instanceof Item) {
                ((Item) item).printDetails();
            }
        }
    }
}

// Borrowable Book
class BorrowableBook extends Book implements Borrowable {
    private boolean isBorrowed;

    public BorrowableBook(String title, int year, String author, String ISBN) {
        super(title, year, author, ISBN);
    }

    @Override
    public void checkOut() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println(getTitle() + " has been borrowed.");
        } else {
            System.out.println(getTitle() + " is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println(getTitle() + " has been returned.");
        } else {
            System.out.println(getTitle() + " was not borrowed.");
        }
    }
}

// Borrowable DVD
class BorrowableDVD extends DVD implements Borrowable {
    private boolean isBorrowed;

    public BorrowableDVD(String title, int year, String director, int duration) {
        super(title, year, director, duration);
    }

    @Override
    public void checkOut() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println(getTitle() + " has been borrowed.");
        } else {
            System.out.println(getTitle() + " is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println(getTitle() + " has been returned.");
        } else {
            System.out.println(getTitle() + " was not borrowed.");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Add items to the library
        Book book1 = new BorrowableBook("Effective Java", 2008, "Joshua Bloch", "123456789");
        DVD dvd1 = new BorrowableDVD("Inception", 2010, "Christopher Nolan", 148);
        Magazine magazine1 = new Magazine("National Geographic", 2023, 10);

        library.addItem(book1);
        library.addItem(dvd1);
        library.addItem(magazine1);

        // Print all items in the library
        System.out.println("Library Items:");
        library.printLibraryItems();

        // Borrow an item
        System.out.println("\nBorrowing an item:");
        library.borrowItem((Borrowable) book1);

        // Print borrowed items
        System.out.println("\nBorrowed Items:");
        library.printBorrowedItems();

        // Return an item
        System.out.println("\nReturning an item:");
        library.returnItem((Borrowable) book1);

        // Print borrowed items
        System.out.println("\nBorrowed Items:");
        library.printBorrowedItems();
    }
}
