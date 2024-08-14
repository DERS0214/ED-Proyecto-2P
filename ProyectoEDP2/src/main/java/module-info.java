module Clases {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens Clases to javafx.fxml;
    exports Clases;
}
