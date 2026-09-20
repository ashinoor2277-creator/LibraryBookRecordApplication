package library;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Library library = new Library();

    private TableView<Book> table;

    private TextField idField;
    private TextField titleField;
    private TextField authorField;
    private TextField categoryField;

    private ComboBox<String> statusBox;

    private TextField searchField;

    private Label totalBooksLabel;
    private Label availableBooksLabel;
    private Label issuedBooksLabel;


    @Override
    public void start(Stage stage) {

        // =====================================================
        // HEADER
        // =====================================================

        Label title =
                new Label("📚  LIBRARY BOOK RECORD SYSTEM");

        title.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label subtitle =
                new Label(
                        "Manage your library books easily and efficiently"
                );

        subtitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #dbeafe;"
        );

        VBox header =
                new VBox(
                        6,
                        title,
                        subtitle
                );

        header.setAlignment(Pos.CENTER);

        header.setPadding(
                new Insets(25)
        );

        header.setStyle(
                "-fx-background-color: #1e3a8a;" +
                "-fx-background-radius: 15;"
        );


        // =====================================================
        // STATISTICS
        // =====================================================

        totalBooksLabel =
                new Label(
                        "Total Books: "
                                + library.getBooks().size()
                );

        availableBooksLabel =
                new Label(
                        "Available: "
                                + countAvailable()
                );

        issuedBooksLabel =
                new Label(
                        "Issued: "
                                + countIssued()
                );

        styleStatisticsLabel(totalBooksLabel);

        styleStatisticsLabel(
                availableBooksLabel
        );

        styleStatisticsLabel(
                issuedBooksLabel
        );


        HBox statistics =
                new HBox(
                        20,
                        createStatCard(
                                "📚",
                                totalBooksLabel,
                                "#dbeafe"
                        ),
                        createStatCard(
                                "✅",
                                availableBooksLabel,
                                "#dcfce7"
                        ),
                        createStatCard(
                                "📤",
                                issuedBooksLabel,
                                "#fee2e2"
                        )
                );

        statistics.setAlignment(
                Pos.CENTER
        );

        statistics.setPadding(
                new Insets(5, 0, 5, 0)
        );


        // =====================================================
        // INPUT FIELDS
        // =====================================================

        idField =
                new TextField();

        idField.setPromptText(
                "Enter Book ID"
        );


        titleField =
                new TextField();

        titleField.setPromptText(
                "Enter Book Title"
        );


        authorField =
                new TextField();

        authorField.setPromptText(
                "Enter Author"
        );


        categoryField =
                new TextField();

        categoryField.setPromptText(
                "Enter Category"
        );


        statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "Available",
                "Issued"
        );

        statusBox.setValue(
                "Available"
        );

        statusBox.setPrefWidth(
                200
        );


        // =====================================================
        // INPUT GRID
        // =====================================================

        GridPane inputGrid =
                new GridPane();

        inputGrid.setHgap(15);

        inputGrid.setVgap(12);

        inputGrid.setPadding(
                new Insets(10)
        );


        Label idLabel =
                new Label("Book ID");

        Label titleLabel =
                new Label("Book Title");

        Label authorLabel =
                new Label("Author");

        Label categoryLabel =
                new Label("Category");

        Label statusLabel =
                new Label("Status");


        styleFormLabel(idLabel);

        styleFormLabel(titleLabel);

        styleFormLabel(authorLabel);

        styleFormLabel(categoryLabel);

        styleFormLabel(statusLabel);


        inputGrid.add(
                idLabel,
                0,
                0
        );

        inputGrid.add(
                idField,
                1,
                0
        );

        inputGrid.add(
                titleLabel,
                2,
                0
        );

        inputGrid.add(
                titleField,
                3,
                0
        );


        inputGrid.add(
                authorLabel,
                0,
                1
        );

        inputGrid.add(
                authorField,
                1,
                1
        );

        inputGrid.add(
                categoryLabel,
                2,
                1
        );

        inputGrid.add(
                categoryField,
                3,
                1
        );


        inputGrid.add(
                statusLabel,
                0,
                2
        );

        inputGrid.add(
                statusBox,
                1,
                2
        );


        // =====================================================
        // FORM BUTTONS
        // =====================================================

        Button addButton =
                new Button("➕ Add Book");

        Button updateButton =
                new Button("✏ Update");

        Button deleteButton =
                new Button("🗑 Delete");

        Button clearButton =
                new Button("Clear");


        styleButton(
                addButton,
                "#2563eb"
        );

        styleButton(
                updateButton,
                "#7c3aed"
        );

        styleButton(
                deleteButton,
                "#dc2626"
        );

        styleButton(
                clearButton,
                "#64748b"
        );


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
                e -> clearFields()
        );


        HBox buttons =
                new HBox(
                        10,
                        addButton,
                        updateButton,
                        deleteButton,
                        clearButton
                );

        buttons.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // FORM TITLE
        // =====================================================

        Label formTitle =
                new Label(
                        "📖  Book Information"
                );

        formTitle.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #6d28d9;"
        );


        // =====================================================
        // FORM CARD
        // =====================================================

        VBox formCard =
                new VBox(
                        12,
                        formTitle,
                        inputGrid,
                        buttons
                );

        formCard.setPadding(
                new Insets(20)
        );

        formCard.setStyle(
                "-fx-background-color: #f5f3ff;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #c4b5fd;" +
                "-fx-border-radius: 15;" +
                "-fx-border-width: 2;"
        );


        // =====================================================
        // SEARCH
        // =====================================================

        searchField =
                new TextField();

        searchField.setPromptText(
                "🔍 Search by Book ID or Title"
        );

        searchField.setPrefWidth(
                350
        );


        Button searchButton =
                new Button("🔍 Search");

        Button showAllButton =
                new Button("📋 Show All");


        styleButton(
                searchButton,
                "#0891b2"
        );

        styleButton(
                showAllButton,
                "#0f766e"
        );


        searchButton.setOnAction(
                e -> searchBook()
        );

        showAllButton.setOnAction(
                e -> refreshTable()
        );


        HBox searchBox =
                new HBox(
                        10,
                        searchField,
                        searchButton,
                        showAllButton
                );

        searchBox.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // TABLE
        // =====================================================

        table =
                new TableView<>();

        table.setPrefHeight(
                300
        );


        // Book ID
        TableColumn<Book, String> idColumn =
                new TableColumn<>(
                        "Book ID"
                );

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );


        // Book Title
        TableColumn<Book, String> titleColumn =
                new TableColumn<>(
                        "Book Title"
                );

        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );


        // Author
        TableColumn<Book, String> authorColumn =
                new TableColumn<>(
                        "Author"
                );

        authorColumn.setCellValueFactory(
                new PropertyValueFactory<>("author")
        );


        // Category
        TableColumn<Book, String> categoryColumn =
                new TableColumn<>(
                        "Category"
                );

        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>("category")
        );


        // Status
        TableColumn<Book, String> statusColumn =
                new TableColumn<>(
                        "Status"
                );

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );


        // =====================================================
        // COLORFUL STATUS CELLS
        // =====================================================

        statusColumn.setCellFactory(
                column ->
                        new TableCell<Book, String>() {

                            @Override
                            protected void updateItem(
                                    String status,
                                    boolean empty) {

                                super.updateItem(
                                        status,
                                        empty
                                );


                                if (empty ||
                                        status == null) {

                                    setText(null);

                                    setStyle("");

                                } else {

                                    setText(status);

                                    if (status.equals(
                                            "Available")) {

                                        setStyle(
                                                "-fx-text-fill: #15803d;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-color: #dcfce7;"
                                        );

                                    } else {

                                        setStyle(
                                                "-fx-text-fill: #b91c1c;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-color: #fee2e2;"
                                        );
                                    }
                                }
                            }
                        }
        );


        table.getColumns().addAll(
                idColumn,
                titleColumn,
                authorColumn,
                categoryColumn,
                statusColumn
        );


        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );


        // =====================================================
        // TABLE ROW SELECTION
        // =====================================================

        table.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs,
                         oldBook,
                         selectedBook) -> {

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


        // =====================================================
        // ISSUE / RETURN BUTTONS
        // =====================================================

        Button issueButton =
                new Button(
                        "📤 Issue Book"
                );

        Button returnButton =
                new Button(
                        "📥 Return Book"
                );


        styleButton(
                issueButton,
                "#ea580c"
        );

        styleButton(
                returnButton,
                "#16a34a"
        );


        issueButton.setOnAction(
                e -> issueBook()
        );

        returnButton.setOnAction(
                e -> returnBook()
        );


        HBox issueReturnBox =
                new HBox(
                        10,
                        issueButton,
                        returnButton
                );

        issueReturnBox.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // TABLE TITLE
        // =====================================================

        Label tableTitle =
                new Label(
                        "📋  Library Books"
                );

        tableTitle.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0369a1;"
        );


        // =====================================================
        // TABLE SECTION
        // =====================================================

        VBox tableSection =
                new VBox(
                        12,
                        tableTitle,
                        searchBox,
                        table,
                        issueReturnBox
                );

        tableSection.setPadding(
                new Insets(20)
        );

        tableSection.setStyle(
                "-fx-background-color: #eff6ff;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #93c5fd;" +
                "-fx-border-radius: 15;" +
                "-fx-border-width: 2;"
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox mainContent =
                new VBox(
                        20,
                        header,
                        statistics,
                        formCard,
                        tableSection
                );

        mainContent.setPadding(
                new Insets(20)
        );

        mainContent.setAlignment(
                Pos.TOP_CENTER
        );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        mainContent
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );


        // =====================================================
        // ROOT
        // =====================================================

        BorderPane root =
                new BorderPane();

        root.setCenter(
                scrollPane
        );

        root.setStyle(
                "-fx-background-color: #dbeafe;" +
                "-fx-font-family: 'Segoe UI';"
        );


        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        root,
                        1000,
                        620
                );


        // =====================================================
        // STAGE
        // =====================================================

        stage.setTitle(
                "📚 Library Book Record System"
        );

        stage.setScene(
                scene
        );

        stage.setMinWidth(
                900
        );

        stage.setMinHeight(
                650
        );

        stage.show();


        refreshTable();
    }


    // =========================================================
    // STATISTICS CARD
    // =========================================================

    private VBox createStatCard(
            String icon,
            Label label,
            String backgroundColor) {

        Label iconLabel =
                new Label(icon);

        iconLabel.setStyle(
                "-fx-font-size: 30px;"
        );


        VBox card =
                new VBox(
                        5,
                        iconLabel,
                        label
                );

        card.setAlignment(
                Pos.CENTER
        );

        card.setPadding(
                new Insets(
                        15,
                        35,
                        15,
                        35
                )
        );

        card.setMinWidth(
                190
        );

        card.setStyle(
                "-fx-background-color: "
                        + backgroundColor + ";" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 15;" +
                "-fx-border-width: 2;"
        );

        return card;
    }


    // =========================================================
    // STATISTICS LABEL STYLE
    // =========================================================

    private void styleStatisticsLabel(
            Label label) {

        label.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e293b;"
        );
    }


    // =========================================================
    // FORM LABEL STYLE
    // =========================================================

    private void styleFormLabel(
            Label label) {

        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4c1d95;"
        );
    }


    // =========================================================
    // BUTTON STYLE
    // =========================================================

    private void styleButton(
            Button button,
            String color) {

        button.setStyle(
                "-fx-background-color: "
                        + color + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 9 15 9 15;" +
                "-fx-background-radius: 8;" +
                "-fx-border-radius: 8;"
        );

        button.setCursor(
                javafx.scene.Cursor.HAND
        );
    }


    // =========================================================
    // ADD BOOK
    // =========================================================

    private void addBook() {

        if (idField.getText().trim().isEmpty()
                || titleField.getText().trim().isEmpty()
                || authorField.getText().trim().isEmpty()
                || categoryField.getText().trim().isEmpty()) {

            showAlert(
                    "Error",
                    "Please fill all fields."
            );

            return;
        }


        if (library.searchById(
                idField.getText().trim()
        ) != null) {

            showAlert(
                    "Error",
                    "Book ID already exists."
            );

            return;
        }


        Book book =
                new Book(
                        idField.getText().trim(),
                        titleField.getText().trim(),
                        authorField.getText().trim(),
                        categoryField.getText().trim(),
                        statusBox.getValue()
                );


        library.addBook(book);

        refreshTable();

        clearFields();

        updateStatistics();


        showAlert(
                "Success",
                "Book added successfully! 📚"
        );
    }


    // =========================================================
    // UPDATE BOOK
    // =========================================================

    private void updateBook() {

        if (idField.getText().trim().isEmpty()) {

            showAlert(
                    "Error",
                    "Please select a book first."
            );

            return;
        }


        Book book =
                library.searchById(
                        idField.getText().trim()
                );


        if (book == null) {

            showAlert(
                    "Error",
                    "Book not found."
            );

            return;
        }


        if (titleField.getText().trim().isEmpty()
                || authorField.getText().trim().isEmpty()
                || categoryField.getText().trim().isEmpty()) {

            showAlert(
                    "Error",
                    "Please fill all fields."
            );

            return;
        }


        book.setTitle(
                titleField.getText().trim()
        );

        book.setAuthor(
                authorField.getText().trim()
        );

        book.setCategory(
                categoryField.getText().trim()
        );

        book.setStatus(
                statusBox.getValue()
        );


        library.saveChanges();


        refreshTable();

        clearFields();

        updateStatistics();


        showAlert(
                "Success",
                "Book updated successfully! ✏"
        );
    }


    // =========================================================
    // DELETE BOOK
    // =========================================================

    private void deleteBook() {

        String id =
                idField.getText().trim();


        if (id.isEmpty()) {

            showAlert(
                    "Error",
                    "Please select a book first."
            );

            return;
        }


        Book book =
                library.searchById(id);


        if (book == null) {

            showAlert(
                    "Error",
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
                "🗑 Delete Book Confirmation"
        );

        confirmation.setContentText(
                "Are you sure you want to delete \""
                        + book.getTitle()
                        + "\"?"
        );


        ButtonType yesButton =
                new ButtonType(
                        "Yes",
                        ButtonBar.ButtonData.YES
                );

        ButtonType noButton =
                new ButtonType(
                        "No",
                        ButtonBar.ButtonData.NO
                );


        confirmation.getButtonTypes()
                .setAll(
                        yesButton,
                        noButton
                );


        confirmation.showAndWait()
                .ifPresent(response -> {

                    if (response == yesButton) {

                        library.deleteBook(id);

                        refreshTable();

                        clearFields();

                        updateStatistics();


                        showAlert(
                                "Success",
                                "Book deleted successfully! 🗑"
                        );
                    }
                });
    }


    // =========================================================
    // ISSUE BOOK
    // =========================================================

    private void issueBook() {

        Book selectedBook =
                table.getSelectionModel()
                        .getSelectedItem();


        if (selectedBook == null) {

            showAlert(
                    "Error",
                    "Please select a book from the table."
            );

            return;
        }


        if (library.issueBook(
                selectedBook.getId()
        )) {

            refreshTable();

            updateStatistics();


            showAlert(
                    "Success",
                    "Book issued successfully! 📤"
            );

        } else {

            showAlert(
                    "Error",
                    "This book is already issued."
            );
        }
    }


    // =========================================================
    // RETURN BOOK
    // =========================================================

    private void returnBook() {

        Book selectedBook =
                table.getSelectionModel()
                        .getSelectedItem();


        if (selectedBook == null) {

            showAlert(
                    "Error",
                    "Please select a book from the table."
            );

            return;
        }


        if (library.returnBook(
                selectedBook.getId()
        )) {

            refreshTable();

            updateStatistics();


            showAlert(
                    "Success",
                    "Book returned successfully! 📥"
            );

        } else {

            showAlert(
                    "Error",
                    "This book is already available."
            );
        }
    }


    // =========================================================
    // SEARCH BOOK
    // =========================================================

    private void searchBook() {

        String search =
                searchField.getText()
                        .trim()
                        .toLowerCase();


        if (search.isEmpty()) {

            refreshTable();

            return;
        }


        ObservableList<Book> results =
                FXCollections.observableArrayList();


        for (Book book :
                library.getBooks()) {

            if (book.getId()
                    .toLowerCase()
                    .contains(search)
                    ||
                book.getTitle()
                    .toLowerCase()
                    .contains(search)) {

                results.add(book);
            }
        }


        table.setItems(
                results
        );


        if (results.isEmpty()) {

            showAlert(
                    "Search Result",
                    "No book found. 🔍"
            );
        }
    }


    // =========================================================
    // REFRESH TABLE
    // =========================================================

    private void refreshTable() {

        ObservableList<Book> list =
                FXCollections.observableArrayList(
                        library.getBooks()
                );

        table.setItems(
                list
        );

        updateStatistics();
    }


    // =========================================================
    // COUNT AVAILABLE
    // =========================================================

    private int countAvailable() {

        int count = 0;


        for (Book book :
                library.getBooks()) {

            if (book.getStatus()
                    .equals("Available")) {

                count++;
            }
        }


        return count;
    }


    // =========================================================
    // COUNT ISSUED
    // =========================================================

    private int countIssued() {

        int count = 0;


        for (Book book :
                library.getBooks()) {

            if (book.getStatus()
                    .equals("Issued")) {

                count++;
            }
        }


        return count;
    }


    // =========================================================
    // UPDATE STATISTICS
    // =========================================================

    private void updateStatistics() {

        if (totalBooksLabel != null) {

            totalBooksLabel.setText(
                    "Total Books: "
                            + library.getBooks().size()
            );

            availableBooksLabel.setText(
                    "Available: "
                            + countAvailable()
            );

            issuedBooksLabel.setText(
                    "Issued: "
                            + countIssued()
            );
        }
    }


    // =========================================================
    // CLEAR FIELDS
    // =========================================================

    private void clearFields() {

        idField.clear();

        titleField.clear();

        authorField.clear();

        categoryField.clear();

        statusBox.setValue(
                "Available"
        );

        table.getSelectionModel()
                .clearSelection();
    }


    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        launch(args);
    }
}