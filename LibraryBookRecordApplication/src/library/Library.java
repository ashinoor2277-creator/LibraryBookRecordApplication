//package library;
//
//import java.io.*;
//import java.util.ArrayList;
//
//public class Library {
//
//    private ArrayList<Book> books;
//    private final String fileName = "books.txt";
//
//    public Library() {
//        books = new ArrayList<>();
//        loadBooks();
//    }
//
//    // Add a book
//    public void addBook(Book book) {
//        books.add(book);
//        saveBooks();
//    }
//
//    // Get all books
//    public ArrayList<Book> getBooks() {
//        return books;
//    }
//
//    // Search book by ID
//    public Book searchById(String id) {
//
//        for (Book book : books) {
//
//            if (book.getId().equalsIgnoreCase(id)) {
//                return book;
//            }
//        }
//
//        return null;
//    }
//
//    // Delete a book
//    public boolean deleteBook(String id) {
//
//        Book book = searchById(id);
//
//        if (book != null) {
//            books.remove(book);
//            saveBooks();
//            return true;
//        }
//
//        return false;
//    }
//
//    // Issue a book
//    public boolean issueBook(String id) {
//
//        Book book = searchById(id);
//
//        if (book != null &&
//                book.getStatus().equals("Available")) {
//
//            book.setStatus("Issued");
//            saveBooks();
//            return true;
//        }
//
//        return false;
//    }
//
//    // Return a book
//    public boolean returnBook(String id) {
//
//        Book book = searchById(id);
//
//        if (book != null &&
//                book.getStatus().equals("Issued")) {
//
//            book.setStatus("Available");
//            saveBooks();
//            return true;
//        }
//
//        return false;
//    }
//
//    // Save books to file
//    private void saveBooks() {
//
//        try {
//
//            PrintWriter writer =
//                    new PrintWriter(
//                            new FileWriter(fileName)
//                    );
//
//            for (Book book : books) {
//
//                writer.println(
//                        book.getId() + "|" +
//                        book.getTitle() + "|" +
//                        book.getAuthor() + "|" +
//                        book.getCategory() + "|" +
//                        book.getStatus()
//                );
//            }
//
//            writer.close();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//    }
//
//    // Load books from file
//    private void loadBooks() {
//
//        File file = new File(fileName);
//
//        if (!file.exists()) {
//            return;
//        }
//
//        try {
//
//            BufferedReader reader =
//                    new BufferedReader(
//                            new FileReader(file)
//                    );
//
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//
//                String[] data = line.split("\\|");
//
//                if (data.length == 5) {
//
//                    Book book = new Book(
//                            data[0],
//                            data[1],
//                            data[2],
//                            data[3],
//                            data[4]
//                    );
//
//                    books.add(book);
//                }
//            }
//
//            reader.close();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//    }
//}
package library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;

    private final String fileName = "books.txt";

    // Constructor
    public Library() {

        books = new ArrayList<>();

        loadBooks();
    }

    // Add a book
    public void addBook(Book book) {

        books.add(book);

        saveBooks();
    }

    // Get all books
    public ArrayList<Book> getBooks() {

        return books;
    }

    // Search book by ID
    public Book searchById(String id) {

        for (Book book : books) {

            if (book.getId().equalsIgnoreCase(id)) {

                return book;
            }
        }

        return null;
    }

    // Delete book
    public boolean deleteBook(String id) {

        Book book = searchById(id);

        if (book != null) {

            books.remove(book);

            saveBooks();

            return true;
        }

        return false;
    }

    // Issue book
    public boolean issueBook(String id) {

        Book book = searchById(id);

        if (book != null &&
                book.getStatus().equals("Available")) {

            book.setStatus("Issued");

            saveBooks();

            return true;
        }

        return false;
    }

    // Return book
    public boolean returnBook(String id) {

        Book book = searchById(id);

        if (book != null &&
                book.getStatus().equals("Issued")) {

            book.setStatus("Available");

            saveBooks();

            return true;
        }

        return false;
    }

    // Save books into file
    private void saveBooks() {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(fileName)
                    );

            for (Book book : books) {

                writer.println(
                        book.getId() + "|" +
                        book.getTitle() + "|" +
                        book.getAuthor() + "|" +
                        book.getCategory() + "|" +
                        book.getStatus()
                );
            }

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // Load books from file
    private void loadBooks() {

        File file = new File(fileName);

        // If file does not exist
        if (!file.exists()) {

            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data =
                        line.split("\\|");

                if (data.length == 5) {

                    Book book =
                            new Book(
                                    data[0],
                                    data[1],
                                    data[2],
                                    data[3],
                                    data[4]
                            );

                    books.add(book);
                }
            }

            reader.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    public void saveChanges() {
        saveBooks();
    }
}