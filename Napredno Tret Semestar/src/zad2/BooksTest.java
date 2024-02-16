package zad2;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Book{
    private String title, category;
    private float price;

    public Book(String title, String category, float price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getPrice() { return price; }

    @Override
    public String toString() {
        return title + " (" + category + ") " + String.format("%.2f", price);
    }
}

class BookCollection{
    private List<Book> books;

    public BookCollection() {
        books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void printByCategory(String category){
        List<Book> filteredBooks = new ArrayList<>();
        for(Book book : books){
            if(book.getCategory().equalsIgnoreCase(category)){
                filteredBooks.add(book);
            }
        }
        filteredBooks.sort(Comparator.comparing(Book::getTitle).thenComparing(Book::getPrice));
        for(Book book : filteredBooks) {
            System.out.println(book);
        }
    }

    public List<Book> getCheapestN(int n){
        books.sort(Comparator.comparing(Book::getPrice));
        int numBooks = Math.min(n, books.size());
        return books.subList(0, numBooks);
    }
}



public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner, BookCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}

