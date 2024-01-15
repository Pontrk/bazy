package pwr;
import java.io.IOException;
import javafx.fxml.FXML;
public class OrderController {
    @FXML
    private void switchToMain() throws IOException {
        App.setRoot("main");
    }
}
