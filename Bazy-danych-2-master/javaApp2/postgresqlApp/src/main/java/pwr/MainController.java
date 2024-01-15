package pwr;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pwr.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private String selectedProductId = null;
    private String selectedCartId;
    @FXML
    private ListView<String> cartList;
    private String cartId;
    @FXML
    private ListView<String> productList;

    @FXML
    private Label price;

    @FXML TextField filterField; 
    private DatabaseTranslator dt;

    public MainController(){
        dt = new DatabaseTranslator();
    }

    @FXML
    private void initialize() {


        refresh();

        // Set the selection mode for the lists
        cartList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


            // Add event handler for mouse clicks on the orderList
            cartList.setOnMouseClicked(this::handleCartListClick);
        
            // Add event handler for mouse clicks on the productList
            productList.setOnMouseClicked(this::handleProductListClick);
        }
    
        private void handleCartListClick(MouseEvent event) {
            // Get the selected item in the orderList
            String selectedItem = cartList.getSelectionModel().getSelectedItem();
            selectedCartId = selectedItem.split(" ")[0];

        }
    
        private void handleProductListClick(MouseEvent event) {
            // Get the selected item in the orderList
            String selectedItem = productList.getSelectionModel().getSelectedItem();
            selectedProductId = selectedItem.split(" ")[0];
        }
    public void refresh(){

        productList.getItems().clear();
        cartList.getItems().clear();

        //pobranie produktów
        List<List<String>> products = dt.getProducts();
        for (List<String> list : products) {
            String product = list.get(0) + " "+list.get(1) + " "+list.get(2) + "zł "+list.get(3);
            String filter = filterField.getText();

            if(filter != null)
            {
                if(product.contains(filter)) productList.getItems().add(product);
            }
            else
            {
                productList.getItems().add(product);
            }
            
           
        }
        

        //pobranie koszyka klienta który nie ma powiazanego zamowienia, jeśli nie ma takiego to stworz nowy
        System.out.println("\n" + App.getClientid() + "\n");
        cartId = dt.getClientCartIdNoOrdrer(App.getClientid());
        if(cartId == null){
            dt.newCart(App.getClientid());
            cartId = dt.getClientCartIdNoOrdrer(App.getClientid());  
        } 

        List<List<String>> productsInCart = dt.getCart(Integer.parseInt(cartId));
        if(productsInCart != null){

            for (List<String> list : productsInCart) {
                cartList.getItems().add(list.get(0) + " "+list.get(1) + " "+list.get(2) + "zł "+list.get(3));
            }
        }
        price.setText(dt.getCartPrice(Integer.parseInt(cartId)));
      
    }

    @FXML
    private void addToCart() throws IOException {
        if(selectedProductId != null) {
            dt.addToCart(Integer.parseInt(selectedProductId), Integer.parseInt(cartId), 1);
            refresh();
        }
    }
    @FXML
    private void removeFromCart(){
        if(selectedCartId != null) {
            dt.removeFromCart(Integer.parseInt(selectedCartId));
            refresh();
        }
    }
    @FXML
    private void switchToOrder() throws IOException {
        App.setRoot("order");
    }
}