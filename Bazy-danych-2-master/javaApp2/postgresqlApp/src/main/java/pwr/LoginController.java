package pwr;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
public class LoginController {


    @FXML
    private Text textError;
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    private DatabaseTranslator dt;
    public LoginController(){
        dt = new DatabaseTranslator();
    }
    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register");
    }
    @FXML
    private void switchToMain() throws IOException {
        if(dt.klientLogin(usernameTextField.getText(),passwordTextField.getText())){
            App.setLogin(usernameTextField.getText());
            App.setClientid(Integer.parseInt(dt.getClientId(App.getLogin())));
            App.setRoot("main");
        }else{
            setTextErrorVisibility(true);
        }
      
       
    }
    private void setTextErrorVisibility(boolean isVisible) {
        textError.setVisible(isVisible);
    }

}
