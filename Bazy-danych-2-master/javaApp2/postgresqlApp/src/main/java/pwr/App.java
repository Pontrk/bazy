package pwr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static String login;
    private static int clientid;
    private static String koszykid;

    public static int getClientid() {
        return clientid;
    }

    public static void setClientid(int clientid) {
        App.clientid = clientid;
    }

   



    public static String getKoszykid() {
        return koszykid;
    }

    public static void setKoszykid(String koszykid) {
        App.koszykid = koszykid;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        App.login = login;
    }


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}