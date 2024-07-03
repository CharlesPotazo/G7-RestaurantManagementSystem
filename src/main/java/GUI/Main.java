package GUI;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { //Finollow po namin yung suggestion niyo. Thank You po
            @Override
            public void run() {
                InventoryMenu login = new InventoryMenu();
                login.setVisible(true);
            }
        });
    } // Good Day po Sir We tried to integrate the pillars of OOP by seperating our concern which are the GUI, Business Logic, Data Layer, Models 
}     // which ipepresent and ieexplain po namin sa sat. Thank you po

