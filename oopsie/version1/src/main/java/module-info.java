module com.oopsie {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;    
    requires javafx.media;  

    opens com.oopsie to javafx.fxml;
    opens com.oopsie.sceneControllers to javafx.fxml;
    exports com.oopsie;
}
