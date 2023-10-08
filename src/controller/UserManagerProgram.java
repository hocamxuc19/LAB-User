/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.Menu;
import repository.UserRepository;

/**
 *
 * @author Administrator
 */
public class UserManagerProgram extends Menu<String>{
    static String[] mc = {"Create a new account", "Login system","Exit"};
    UserRepository program;
    
    public UserManagerProgram() {
        super("     USER MANAGEMENT SYSTEM", mc);
        program = new UserRepository();
    }
    @Override
    public void execute(int n) {
        switch(n) {
            case 1:
                program.createNewAccount();
                break;
            case 2:
                program.loginSystem();
                break;
            case 3:
                System.exit(0);
        } 
    }
    
}