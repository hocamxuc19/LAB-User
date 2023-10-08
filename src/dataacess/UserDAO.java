/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataacess;

import common.Library;
import common.Validate;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Administrator
 */
public class UserDAO {
    private static UserDAO instance = null;
    Validate v;
    Library l;

    public UserDAO() {
        l = new Library();
        v = new Validate();
    }
    
    public static UserDAO Instance() {
        if (instance == null) {
            synchronized (UserDAO.class) {
                if (instance == null) {
                    instance = new UserDAO();
                }
            }
        }
        return instance;
    }
    
    //create a new account
    public void createNewAccount() {
        //check file data exist or not
        if (!v.checkFileExist()) {
            System.out.println("File is not exist");
            return;
        }
        String username = l.inputString("Enter Username: ");
        while(!v.checkInputUsername(username)) {
            username = l.inputString("Enter username again: ");
        }
        String password = l.inputString("Enter password: ");
        while(!v.checkInputPassword(password)) {
            password = l.inputString("Enter password again: ");
        }
        addAccountData(username, password);
  
    }

    //login system
    public void loginSystem() {
        //check file data exist or not
        if (!v.checkFileExist()) {
            System.out.println("File is not exist");
            return;
        }
        String username = l.inputString("Enter Username: ");
        while(!v.checkInputUsernameLogin(username)) {
            username = l.inputString("Enter Username: ");
        }
        String password = l.inputString("Enter Password: ");
        while(!v.checkInputPassword(password)) {
            password = l.inputString("Enter Password again: ");
        }
        //check username exist or not
        if (!v.checkUsernameExist(username)) {
            if (!password.equalsIgnoreCase(passwordByUsername(username))) {
                System.err.println("Username or Password is incorrect.");
            } else {
                System.err.println("Login success. Welcome to Hentaiz. Enjoy your day!!!");
                /*String url = "https://ihentai.de/"; 
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }*/
            }
            
        }
        else {
            System.err.println("Username or Password is incorrect.");
        }
    }

    //write new account to data
    public void addAccountData(String username, String password) {
        File file = new File("src\\user.dat");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(username + ";" + password + "\n");
            fileWriter.close();
            System.err.println("Create successly.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //get password by username
    public String passwordByUsername(String username) {
        File file = new File("src\\user.dat");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return account[1];
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}