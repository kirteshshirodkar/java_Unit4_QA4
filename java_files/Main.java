import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Database.createTable();

        TextField nameField = new TextField();
        nameField.setPromptText("Student Name");

        TextField rollField = new TextField();
        rollField.setPromptText("Roll Number");

        TextField courseField = new TextField();
        courseField.setPromptText("Course");

        Button addBtn = new Button("Add Student");

        TableView<Student> table = new TableView<>();

        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> 
            new javafx.beans.property.SimpleIntegerProperty(c.getValue().getId()).asObject()
        );

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(c ->
            new javafx.beans.property.SimpleStringProperty(c.getValue().getName())
        );

        TableColumn<Student, String> rollCol = new TableColumn<>("Roll No");
        rollCol.setCellValueFactory(c ->
            new javafx.beans.property.SimpleStringProperty(c.getValue().getRollno())
        );

        TableColumn<Student, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(c ->
            new javafx.beans.property.SimpleStringProperty(c.getValue().getCourse())
        );

        table.getColumns().addAll(idCol, nameCol, rollCol, courseCol);
        table.setItems(Database.getStudents());

        addBtn.setOnAction(e -> {
            Database.insertStudent(
                    nameField.getText(),
                    rollField.getText(),
                    courseField.getText()
            );
            table.setItems(Database.getStudents());
            nameField.clear();
            rollField.clear();
            courseField.clear();
        });

        VBox layout = new VBox(10, nameField, rollField, courseField, addBtn, table);
        layout.setPadding(new javafx.geometry.Insets(20));

        stage.setTitle("Student Management System");
        stage.setScene(new Scene(layout, 600, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
