module open.data {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens open.data to javafx.fxml;
    exports open.data;
}
