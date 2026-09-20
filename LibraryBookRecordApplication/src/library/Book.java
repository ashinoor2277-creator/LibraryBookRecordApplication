package library;

public class Book {

    private String id;
    private String title;
    private String author;
    private String category;
    private String status;

    // Constructor
    public Book(String id, String title, String author,
                String category, String status) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
    }

    // Get ID
    public String getId() {
        return id;
    }

    // Get Title
    public String getTitle() {
        return title;
    }

    // Get Author
    public String getAuthor() {
        return author;
    }

    // Get Category
    public String getCategory() {
        return category;
    }

    // Get Status
    public String getStatus() {
        return status;
    }

    // Set Title
    public void setTitle(String title) {
        this.title = title;
    }

    // Set Author
    public void setAuthor(String author) {
        this.author = author;
    }

    // Set Category
    public void setCategory(String category) {
        this.category = category;
    }

    // Set Status
    public void setStatus(String status) {
        this.status = status;
    }
}
//package library;
//
//public class Book {
//
//    private String id;
//    private String title;
//    private String author;
//    private String category;
//    private String status;
//
//    public Book(String id, String title, String author, String category, String status) {
//        this.id = id;
//        this.title = title;
//        this.author = author;
//        this.category = category;
//        this.status = status;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//}