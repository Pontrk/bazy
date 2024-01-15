package pwr;

import java.util.List;

public class Controller {
    private PostgressConnect pc;
    public Controller(){
        pc = new PostgressConnect();
    }

    public void test() {
        
    }

    public void login() {
       
    }

    public void showProducts() {
        List<List<String>> result = pc.executeQuery("SELECT * FROM pracownicy");
        for (List<String> i : result) {
            for (String column : i) {
                System.out.print(column + " ");
            }
            System.out.println("");
        }
    }
    
}
