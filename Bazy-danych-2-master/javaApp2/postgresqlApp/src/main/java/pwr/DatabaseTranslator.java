package pwr;

import pwr.DatabaseConnection;
import java.util.List;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseTranslator {

    private DatabaseConnection dc;

    public DatabaseTranslator(){
        dc = new DatabaseConnection();
    }

    public Boolean klientLogin(String login, String password) {
        String sqlQuery = "SELECT klient_login(?, ?)";
        List<Object> params = Arrays.asList(login, password);
    
        List<List<String>> result;
        try {
            result = dc.executeQuery(sqlQuery, params);
            return Boolean.parseBoolean(result.get(0).get(0));
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }
        
    }
    public boolean klientRegister(String login,String haslo,String imie,String nazwisko,String adres,String email,String telefon){
        String sqlQuery = "SELECT insert_klient_data(?,?,?,?,?,?,?)";
        List<Object> params = Arrays.asList(login, haslo, imie, nazwisko,adres, email,telefon);
        try {
           dc.executeQuery(sqlQuery, params);
           return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public List<List<String>> getProducts(){
        String sqlQuery = "SELECT towarid,nazwa,cena,kategoria FROM towary";
        List<List<String>> result;
        result = dc.executeQuery(sqlQuery);
        return result;
    }
    public List<List<String>> getCart(int id){
        String sqlQuery = "SELECT towary_w_koszyku.towarkoszykid, towary.nazwa, towary.cena,towary.kategoria,towary_w_koszyku.ilosc from towary_w_koszyku JOIN towary on towary_w_koszyku.towarid = towary.towarid where towary_w_koszyku.koszykid = ?";
        List<Object> params = Arrays.asList(id);
        List<List<String>> result;
        try {
            result = dc.executeQuery(sqlQuery,params);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
    public String getClientId(String login){
        String sqlQuery = "SELECT klientid from klienci where klienci.login = ?";
        List<Object> params = Arrays.asList(login);
        List<List<String>> result;
        try {
            result = dc.executeQuery(sqlQuery,params);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result.get(0).get(0);
    }
    public void newCart(Integer id){
        String sqlQuery = "INSERT INTO koszyki(klientid,cena) VALUES (?,0)";
        List<Object> params = Arrays.asList(id);
        try {
            dc.executeQuery(sqlQuery,params);
        } catch (SQLException e) {
            
            e.printStackTrace();

        }
    }
    public String getCartPrice(int id){
        String sqlQuery = "SELECT cena FROM koszyki where koszyki.koszykid = ?";
        List<Object> params = Arrays.asList(id);
        try {
            List<List<String>> result = dc.executeQuery(sqlQuery,params);
            return result.get(0).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }   
    public void addToCart(int towarid, int koszykid, int n){
        String sqlQuery = "insert into towary_w_koszyku(towarid,koszykid,ilosc) VALUES (?,?,?)";
        List<Object> params = Arrays.asList(towarid,koszykid,n);
        try {
            dc.executeQuery(sqlQuery,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeFromCart(int id){
        String sqlQuery = "DELETE FROM towary_w_koszyku where towarkoszykid = ?";
        List<Object> params = Arrays.asList(id);
        try {
            dc.executeQuery(sqlQuery,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void makeOrder(String id, String adres){
        String sqlQuery = "insert into zamowienia(koszykid, adres) VALUES (?,?)";
        List<Object> params = Arrays.asList(id);
        try {
            dc.executeQuery(sqlQuery,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getClientCartIdNoOrdrer(Integer clientId){
        String sqlQuery = "SELECT koszyki.koszykid from koszyki left join zamowienia on zamowienia.koszykid = koszyki.koszykid WHERE koszyki.klientid = ? AND zamowienia.koszykid is NULL";
        List<Object> params = Arrays.asList(clientId);
        try {
            List<List<String>> result = dc.executeQuery(sqlQuery,params);
            return result.get(0).get(0);
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }
}
