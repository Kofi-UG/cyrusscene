module com.example.pharmacymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.pharmacymanagementsystem to javafx.fxml;
    opens com.example.pharmacymanagementsystem.controllers to javafx.fxml;
    opens com.example.pharmacymanagementsystem.Mapper to javafx.base;
    exports com.example.pharmacymanagementsystem;
}