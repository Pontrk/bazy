package pwr;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController {

    @FXML
    private TextField textLogin;

    @FXML
    private TextField textPassword;

    @FXML
    private TextField textPassword2;
    @FXML
    private TextField textCity;

    @FXML
    private TextField textPostalCode;

    @FXML
    private TextField textName;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textSurname;

    @FXML
    private TextField textStreet;

    @FXML
    private Text textError;

    @FXML
    private TextField textPhone;
    @FXML
    private Button secondaryButton;

    private DatabaseTranslator dt;
    public RegisterController(){
        dt = new DatabaseTranslator();
    }

    @FXML
    private void switchToLogin() throws IOException{
        String login = textLogin.getText();
        String password = textPassword.getText();
        String password2 = textPassword2.getText();
        String name = textName.getText();
        String surname = textSurname.getText();
        String street = textStreet.getText();
        String postalCode = textPostalCode.getText();
        String city = textCity.getText();
        String email = textEmail.getText();
        String phone = textPhone.getText();


        
        if(password.equals(password2) && dt.klientRegister(login, password, name, surname, street + " " + postalCode + " " + city, email, phone))
        {
            App.setRoot("login");
        }
        else
        {
            setTextErrorVisibility(true);
        }
      
            
        

       
    }
    private void setTextErrorVisibility(boolean isVisible) {
        textError.setVisible(isVisible);
    }
}
