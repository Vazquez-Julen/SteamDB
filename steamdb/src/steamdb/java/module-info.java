module steamdb {
    requires javafx.controls;
    requires javafx.fxml;

    opens steamdb to javafx.fxml;
    exports steamdb;
}
