package GUI;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
                login.setVisible(true);
          
            }
        });
    }
    
}
