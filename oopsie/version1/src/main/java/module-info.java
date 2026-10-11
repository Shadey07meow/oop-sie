module com.oopsie {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.oopsie to javafx.fxml;
    opens com.oopsie.sceneControllers to javafx.fxml;
    exports com.oopsie;
}
