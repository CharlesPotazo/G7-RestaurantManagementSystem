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
    } // Good Day po Sir tinry po namin mag 3 Layered which is GUI,Business Logic, and Data Layer which we will expain po sa presentation thank you po
}

