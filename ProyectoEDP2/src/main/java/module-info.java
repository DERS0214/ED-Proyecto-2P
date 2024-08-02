module Clases {
    requires javafx.controls;
    requires javafx.fxml;

    opens Clases to javafx.fxml;
    exports Clases;
}
