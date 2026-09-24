module com.oopsie {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.oopsie to javafx.fxml;
    exports com.oopsie;
}
