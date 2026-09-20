package library;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    private Library library;

    private TableView<Book> table;
    private ObservableList<Book> bookList;

    private TextField idField;
    private TextField titleField;
    private TextField authorField;
    private TextField categoryField;
    private TextField searchField;

    private ComboBox<String> statusBox;

    private Label totalLabel;
    private Label availableLabel;
    private Label issuedLabel;

    @Override
    public void start(Stage stage) {

        library = new Library();

        // =========================
        // MAIN WINDOW
        // =========================

        BorderPane root = new BorderPane();

        root.setPadding(new Insets(15));

        // =========================
        // HEADER
        // =========================

        Label heading =
                new Label("📚 LIBRARY BOOK RECORD SYSTEM");

        heading.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;"
        );

        Label subtitle =
                new Label("Manage your library books easily");

        subtitle.setStyle(
                "-fx-font-size: 15px;"
        );

        VBox header =
                new VBox(5, heading, subtitle);

        header.setAlignment(Pos.CENTER);

        root.setTop(header);

        // =========================
        // STATISTICS
        // =========================

        totalLabel = new Label();
        availableLabel = new Label();
        issuedLabel = new Label();

        VBox totalCard =
                createCard(
                        "📚 Total Books",
                        totalLabel
                );

        VBox availableCard =
                createCard(
                        "✅ Available",
                        availableLabel
                );

        VBox issuedCard =
                createCard(
                        "📕 Issued",
                        issuedLabel
                );

        HBox statistics =
                new HBox(
                        20,
                        totalCard,
                        availableCard,
                        issuedCard
                );

        statistics.setAlignment(Pos.CENTER);

        // =========================
        // INPUT FORM
        // =========================

        idField = new TextField();
        idField.setPromptText("Enter Book ID");

        titleField = new TextField();
        titleField.setPromptText("Enter Book Title");

        authorField = new TextField();
        authorField.setPromptText("Enter Author");

        categoryField = new TextField();
        categoryField.setPromptText("Enter Category");

        statusBox = new ComboBox<>();

        statusBox.getItems().addAll(
                "Available",
                "Issued"
        );

        statusBox.setValue("Available");

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(15));

        form.add(
                new Label("Book ID:"),
                0,
                0
        );

        form.add(
                idField,
                1,
                0
        );

        form.add(
                new Label("Book Title:"),
                2,
                0
        );

        form.add(
                titleField,
                3,
                0
        );

        form.add(
                new Label("Author:"),
                0,
                1
        );

        form.add(
                authorField,
                1,
                1
        );

        form.add(
                new Label("Category:"),
                2,
                1
        );

        form.add(
                categoryField,
                3,
                1
        );

        form.add(
                new Label("Status:"),
                0,
                2
        );

        form.add(
                statusBox,
                1,
                2
        );

        // =========================
        // BUTTONS
        // =========================

        Button addButton =
                new Button("➕ Add Book");

        Button updateButton =
                new Button("✏ Update");

        Button deleteButton =
                new Button("🗑 Delete");

        Button clearButton =
                new Button("🧹 Clear");

        Button issueButton =
                new Button("📕 Issue Book");

        Button returnButton =
                new Button("📗 Return Book");

        addButton.setStyle(
                "-fx-background-color: #16a34a;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        updateButton.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        deleteButton.setStyle(
                "-fx-background-color: #dc2626;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        clearButton.setStyle(
                "-fx-background-color: #64748b;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        issueButton.setStyle(
                "-fx-background-color: #f97316;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        returnButton.setStyle(
                "-fx-background-color: #059669;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        HBox buttonBox =
                new HBox(
                        10,
                        addButton,
                        updateButton,
                        deleteButton,
                        clearButton,
                        issueButton,
                        returnButton
                );

        buttonBox.setAlignment(Pos.CENTER);

        // =========================
        // SEARCH AREA
        // =========================

        searchField = new TextField();

        searchField.setPromptText(
                "Search by Book ID or Title..."
        );

        searchField.setPrefWidth(300);

        Button searchButton =
                new Button("🔍 Search");

        Button showAllButton =
                new Button("📋 Show All");

        Button resetSearchButton =
                new Button("🔄 Reset Search");

        searchButton.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        showAllButton.setStyle(
                "-fx-background-color: #0891b2;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        resetSearchButton.setStyle(
                "-fx-background-color: #9333ea;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        HBox searchBox =
                new HBox(
                        10,
                        searchField,
                        searchButton,
                        showAllButton,
                        resetSearchButton
                );

        searchBox.setAlignment(Pos.CENTER);

        // =========================
        // TABLE
        // =========================

        table = new TableView<>();

        TableColumn<Book, String> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().getId()
                        )
        );

        TableColumn<Book, String> titleColumn =
                new TableColumn<>("Title");

        titleColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().getTitle()
                        )
        );

        TableColumn<Book, String> authorColumn =
                new TableColumn<>("Author");

        authorColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().getAuthor()
                        )
        );

        TableColumn<Book, String> categoryColumn =
                new TableColumn<>("Category");

        categoryColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().getCategory()
                        )
        );

        TableColumn<Book, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().getStatus()
                        )
        );

        table.getColumns().addAll(
                idColumn,
                titleColumn,
                authorColumn,
                categoryColumn,
                statusColumn
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN
        );

        // =========================
        // STATUS COLORS
        // =========================

        statusColumn.setCellFactory(
                column ->
                        new TableCell<Book, String>() {

                            @Override
                            protected void updateItem(
                                    String status,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        status,
                                        empty
                                );

                                if (empty || status == null) {

                                    setText(null);
                                    setStyle("");

                                } else {

                                    setText(status);

                                    if (status.equals("Available")) {

                                        setStyle(
                                                "-fx-text-fill: green;" +
                                                "-fx-font-weight: bold;"
                                        );

                                    } else {

                                        setStyle(
                                                "-fx-text-fill: red;" +
                                                "-fx-font-weight: bold;"
                                        );
                                    }
                                }
                            }
                        }
        );

        bookList =
                FXCollections.observableArrayList(
                        library.getBooks()
                );

        table.setItems(bookList);

        // =========================
        // TABLE SELECTION
        // =========================

        table.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldBook, selectedBook) -> {

                            if (selectedBook != null) {

                                idField.setText(
                                        selectedBook.getId()
                                );

                                titleField.setText(
                                        selectedBook.getTitle()
                                );

                                authorField.setText(
                                        selectedBook.getAuthor()
                                );

                                categoryField.setText(
                                        selectedBook.getCategory()
                                );

                                statusBox.setValue(
                                        selectedBook.getStatus()
                                );
                            }
                        }
                );

        // =========================
        // BUTTON ACTIONS
        // =========================

        addButton.setOnAction(
                e -> addBook()
        );

        updateButton.setOnAction(
                e -> updateBook()
        );

        deleteButton.setOnAction(
                e -> deleteBook()
        );

        clearButton.setOnAction(
                e -> clearForm()
        );

        issueButton.setOnAction(
                e -> issueBook()
        );

        returnButton.setOnAction(
                e -> returnBook()
        );

        searchButton.setOnAction(
                e -> searchBooks()
        );

        // =========================
        // SHOW ALL
        // =========================

        showAllButton.setOnAction(
                e -> {

                    searchField.clear();

                    refreshTable();
                }
        );

        // =========================
        // RESET SEARCH
        // =========================

        resetSearchButton.setOnAction(
                e -> {

                    searchField.clear();

                    refreshTable();
                }
        );

        // Press Enter to search

        searchField.setOnAction(
                e -> searchBooks()
        );

        // =========================
        // CENTER CONTENT
        // =========================

        VBox centerContent =
                new VBox(
                        15,
                        statistics,
                        form,
                        buttonBox,
                        searchBox,
                        table
                );

        centerContent.setPadding(
                new Insets(20, 0, 0, 0)
        );

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        root.setCenter(centerContent);

        // =========================
        // STATISTICS
        // =========================

        updateStatistics();

        // =========================
        // SCENE
        // =========================

        Scene scene =
                new Scene(
                        root,
                        1100,
                        750
                );

        stage.setTitle(
                "📚 Library Book Record System"
        );

        stage.setScene(scene);

        /*
         * COMMIT 3 IMPROVEMENT:
         * The application window can now
         * be resized by the user.
         */
        stage.setResizable(true);

        stage.show();
    }

    // =====================================================
    // ADD BOOK
    // =====================================================

    private void addBook() {

        String id =
                idField.getText().trim();

        String title =
                titleField.getText().trim();

        String author =
                authorField.getText().trim();

        String category =
                categoryField.getText().trim();

        String status =
                statusBox.getValue();

        if (id.isEmpty()
                || title.isEmpty()
                || author.isEmpty()
                || category.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please fill all fields."
            );

            return;
        }

        if (library.searchById(id) != null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Book ID already exists."
            );

            return;
        }

        Book book =
                new Book(
                        id,
                        title,
                        author,
                        category,
                        status
                );

        library.addBook(book);

        refreshTable();

        clearForm();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Book added successfully!"
        );
    }

    // =====================================================
    // UPDATE BOOK
    // =====================================================

    private void updateBook() {

        String id =
                idField.getText().trim();

        if (id.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter Book ID."
            );

            return;
        }

        Book book =
                library.searchById(id);

        if (book == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Book not found."
            );

            return;
        }

        String title =
                titleField.getText().trim();

        String author =
                authorField.getText().trim();

        String category =
                categoryField.getText().trim();

        String status =
                statusBox.getValue();

        if (title.isEmpty()
                || author.isEmpty()
                || category.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please fill all fields."
            );

            return;
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setStatus(status);

        library.saveChanges();

        refreshTable();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Book updated successfully!"
        );
    }

    // =====================================================
    // DELETE BOOK
    // =====================================================

    private void deleteBook() {

        String id =
                idField.getText().trim();

        if (id.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter or select a Book ID."
            );

            return;
        }

        Book book =
                library.searchById(id);

        if (book == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Book not found."
            );

            return;
        }

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Delete Book"
        );

        confirmation.setHeaderText(
                "Delete this book?"
        );

        confirmation.setContentText(
                "Book: " + book.getTitle()
        );

        Optional<ButtonType> result =
                confirmation.showAndWait();

        if (result.isPresent()
                && result.get() == ButtonType.OK) {

            library.deleteBook(id);

            refreshTable();

            clearForm();

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Book deleted successfully!"
            );
        }
    }

    // =====================================================
    // ISSUE BOOK
    // =====================================================

    private void issueBook() {

        String id =
                idField.getText().trim();

        if (id.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter or select a Book ID."
            );

            return;
        }

        if (library.issueBook(id)) {

            refreshTable();

            clearForm();

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Book issued successfully!"
            );

        } else {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Book cannot be issued.\n"
                    + "It may not exist or is already issued."
            );
        }
    }

    // =====================================================
    // RETURN BOOK
    // =====================================================

    private void returnBook() {

        String id =
                idField.getText().trim();

        if (id.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter or select a Book ID."
            );

            return;
        }

        if (library.returnBook(id)) {

            refreshTable();

            clearForm();

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Book returned successfully!"
            );

        } else {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Book cannot be returned.\n"
                    + "It may not exist or is already available."
            );
        }
    }

    // =====================================================
    // SEARCH BOOKS
    // =====================================================

    private void searchBooks() {

        String searchText =
                searchField.getText()
                        .trim()
                        .toLowerCase();

        if (searchText.isEmpty()) {

            refreshTable();

            return;
        }

        ObservableList<Book> results =
                FXCollections.observableArrayList();

        for (Book book : library.getBooks()) {

            if (book.getId()
                    .toLowerCase()
                    .contains(searchText)
                    ||
                book.getTitle()
                    .toLowerCase()
                    .contains(searchText)) {

                results.add(book);
            }
        }

        table.setItems(results);

        if (results.isEmpty()) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "No book found."
            );
        }
    }

    // =====================================================
    // REFRESH TABLE
    // =====================================================

    private void refreshTable() {

        bookList =
                FXCollections.observableArrayList(
                        library.getBooks()
                );

        table.setItems(bookList);

        updateStatistics();
    }

    // =====================================================
    // CLEAR FORM
    // =====================================================

    private void clearForm() {

        idField.clear();

        titleField.clear();

        authorField.clear();

        categoryField.clear();

        statusBox.setValue("Available");

        table.getSelectionModel()
                .clearSelection();
    }

    // =====================================================
    // UPDATE STATISTICS
    // =====================================================

    private void updateStatistics() {

        int total =
                library.getBooks().size();

        int available = 0;

        int issued = 0;

        for (Book book : library.getBooks()) {

            if (book.getStatus()
                    .equals("Available")) {

                available++;

            } else if (book.getStatus()
                    .equals("Issued")) {

                issued++;
            }
        }

        totalLabel.setText(
                String.valueOf(total)
        );

        availableLabel.setText(
                String.valueOf(available)
        );

        issuedLabel.setText(
                String.valueOf(issued)
        );
    }

    // =====================================================
    // CREATE STATISTICS CARD
    // =====================================================

    private VBox createCard(
            String title,
            Label value
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        value.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;"
        );

        VBox card =
                new VBox(
                        5,
                        titleLabel,
                        value
                );

        card.setAlignment(
                Pos.CENTER
        );

        card.setPrefWidth(200);

        card.setPadding(
                new Insets(15)
        );

        card.setStyle(
                "-fx-background-color: #f1f5f9;" +
                "-fx-background-radius: 10;"
        );

        return card;
    }

    // =====================================================
    // ALERT
    // =====================================================

    private void showAlert(
            Alert.AlertType type,
            String message
    ) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                "Library Book Record System"
        );

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        launch(args);
    }
}