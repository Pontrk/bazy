module pwr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens pwr to javafx.fxml;
    exports pwr;
}
