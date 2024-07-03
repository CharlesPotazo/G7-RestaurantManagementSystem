package GUI;

import BusinessLogic.FoodService;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class POSMenu extends JFrame implements ActionListener {

    private JPanel panelFood, panelDrinks, panelDessert; //instead of switching frames po per category. We used po switching panels
    private JPanel whiteSquare, grayRectangle, divider;
    private JLabel POS, Logo, TotalTxt, TotalNmbr;
    private JButton btnInventory, btnReports, btnWasteTracking;  //buttons at the side
    private JButton btnFood, btnDrinks, btnDessert, btnPay, btnCancel; //main buttons
    private JButton kungPaoChicken, mushroomChicken, orangeChicken, coke, sprite, ubepastillas, mangosago; // Dishes Button
    private JLabel nmbrkungPaoChicken, nmbrmushroomChicken, nmbrorangeChicken, nmbrCoke, nmbrSprite, nmbrUbePastillas, nmbrMangoSago;//Counter po ng dishes kung ilan po yung order per item that will be used po to minus in our database
    private JTable table; // this JTable will act as text order area po for keeping track of orders
    private JScrollPane scrollPane;
    private FoodService foodService = new FoodService(); //private po namin so that yung class lang po nito yung makakagamit 

    POSMenu() {
        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("POS");
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        //------Text Area Order-------
        String[] columnNames = {"Food", "Quantity", "Price"}; //We used table insteaed of text area so that repeating order will not append 
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(Font.BOLD));
        table.setPreferredScrollableViewportSize(new Dimension(300, 200));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(660, 10, 240, 400);
        add(scrollPane);

        //------------Buttons---------
        TotalTxt = new JLabel("Total:");
        TotalTxt.setFont(new Font("arimo", Font.PLAIN, 15));
        TotalTxt.setBounds(660, 450, 100, 20);
        add(TotalTxt);

        TotalNmbr = new JLabel("0");
        TotalNmbr.setFont(new Font("arimo", Font.PLAIN, 15));
        TotalNmbr.setBounds(770, 450, 100, 20);
        add(TotalNmbr);

        btnPay = new JButton("Pay");
        btnPay.setBackground(new Color(253, 190, 2));
        btnPay.setForeground(Color.black);
        btnPay.setFont(new Font("arimo", Font.PLAIN, 15));
        btnPay.setBounds(660, 500, 100, 20);
        btnPay.setBorderPainted(false);
        btnPay.addActionListener(this);
        add(btnPay);

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(new Color(253, 190, 2));
        btnCancel.setForeground(Color.black);
        btnCancel.setFont(new Font("arimo", Font.PLAIN, 15));
        btnCancel.setBounds(660, 530, 100, 20);
        btnCancel.setBorderPainted(false);
        btnCancel.addActionListener(this);
        add(btnCancel);

        btnFood = new JButton("Food");
        btnFood.setBackground(new Color(253, 190, 2));
        btnFood.setForeground(Color.black);
        btnFood.setFont(new Font("arimo", Font.PLAIN, 15));
        btnFood.setBounds(170, 20, 150, 50);
        btnFood.setBorderPainted(false);
        btnFood.addActionListener(this);
        add(btnFood);

        btnDrinks = new JButton("Drinks");
        btnDrinks.setBackground(new Color(253, 190, 2));
        btnDrinks.setForeground(Color.black);
        btnDrinks.setFont(new Font("arimo", Font.PLAIN, 15));
        btnDrinks.setBounds(330, 20, 150, 50); //30 pagitan
        btnDrinks.setBorderPainted(false);
        btnDrinks.addActionListener(this);
        add(btnDrinks);

        btnDessert = new JButton("Dessert");
        btnDessert.setBackground(new Color(253, 190, 2));
        btnDessert.setForeground(Color.black);
        btnDessert.setFont(new Font("arimo", Font.PLAIN, 15));
        btnDessert.setBounds(490, 20, 150, 50); //30 pagitan
        btnDessert.setBorderPainted(false);
        btnDessert.addActionListener(this);
        add(btnDessert);

        POS = new JLabel("POS");//POS Button
        POS.setBounds(55, 150, 100, 27);
        POS.setForeground(Color.white);
        POS.setFont(new Font("impact", Font.PLAIN, 27));
        add(POS);

        btnInventory = new JButton("Inventory");//Inventory Button
        btnInventory.setBounds(0, 250, 150, 27);
        btnInventory.setBackground(new Color(0, 0, 0, 0));
        btnInventory.setOpaque(false);
        btnInventory.setBorderPainted(false);
        btnInventory.setForeground(Color.white);
        btnInventory.setFont(new Font("impact", Font.PLAIN, 27));
        btnInventory.addActionListener(this);
        add(btnInventory);

        btnReports = new JButton("Reports");//Reports Button
        btnReports.setBounds(10, 350, 130, 27);
        btnReports.setBackground(new Color(0, 0, 0, 0));
        btnReports.setOpaque(false);
        btnReports.setBorderPainted(false);
        btnReports.setForeground(Color.white);
        btnReports.setFont(new Font("impact", Font.PLAIN, 27));
        btnReports.addActionListener(this);
        add(btnReports);

        btnWasteTracking = new JButton("Waste");//Waste Button
        btnWasteTracking.setBounds(0, 450, 150, 27);
        btnWasteTracking.setBackground(new Color(0, 0, 0, 0));
        btnWasteTracking.setOpaque(false);
        btnWasteTracking.setBorderPainted(false);
        btnWasteTracking.setForeground(Color.white);
        btnWasteTracking.setFont(new Font("impact", Font.PLAIN, 27));
        btnWasteTracking.addActionListener(this);
        add(btnWasteTracking);

        //------Food_Panel--------------
        panelFood = new JPanel();
        panelFood.setBackground(new Color(87, 88, 90));
        panelFood.setBounds(170, 100, 470, 500);
        panelFood.setLayout(null);
        panelFood.setVisible(true);
        add(panelFood);

        kungPaoChicken = new JButton("Kung Pao Chicken"); //kungpaockn
        kungPaoChicken.setFont(new Font("impact", Font.PLAIN, 11));
        kungPaoChicken.setBounds(10, 10, 120, 140);
        ImageIcon kungPaoChickenIcon = new ImageIcon("images/kungpao chicken.jpg");
        kungPaoChicken.setIcon(kungPaoChickenIcon);
        kungPaoChicken.setHorizontalTextPosition(JButton.CENTER);
        kungPaoChicken.setVerticalTextPosition(JButton.BOTTOM);
        kungPaoChicken.setBackground(Color.white);
        kungPaoChicken.addActionListener(this);
        nmbrkungPaoChicken = new JLabel("0", SwingConstants.CENTER);
        nmbrkungPaoChicken.setBounds(10, 150, 120, 140);
        panelFood.add(kungPaoChicken);

        orangeChicken = new JButton("Orange Chicken"); //kungpaockn
        orangeChicken.setFont(new Font("impact", Font.PLAIN, 11));
        orangeChicken.setBounds(150, 10, 120, 140);
        ImageIcon orangeChickenIcon = new ImageIcon("images/orange chicken.jpg");
        orangeChicken.setIcon(orangeChickenIcon);
        orangeChicken.setHorizontalTextPosition(JButton.CENTER);
        orangeChicken.setVerticalTextPosition(JButton.BOTTOM);
        orangeChicken.setBackground(Color.white);
        orangeChicken.addActionListener(this);
        nmbrorangeChicken = new JLabel("0", SwingConstants.CENTER);
        nmbrorangeChicken.setBounds(150, 150, 120, 140);
        panelFood.add(orangeChicken);

        mushroomChicken = new JButton("Mushroom Chicken"); //kungpaockn
        mushroomChicken.setFont(new Font("impact", Font.PLAIN, 11));
        mushroomChicken.setBounds(290, 10, 130, 140);
        ImageIcon mushroomChickenIcon = new ImageIcon("images/mushroom chicken.jpg");
        mushroomChicken.setIcon(mushroomChickenIcon);
        mushroomChicken.setHorizontalTextPosition(JButton.CENTER);
        mushroomChicken.setVerticalTextPosition(JButton.BOTTOM);
        mushroomChicken.setBackground(Color.white);
        mushroomChicken.addActionListener(this);
        nmbrmushroomChicken = new JLabel("0", SwingConstants.CENTER);
        nmbrmushroomChicken.setBounds(290, 150, 120, 140);
        panelFood.add(mushroomChicken);

        //------Drink_Panel--------------
        panelDrinks = new JPanel();
        panelDrinks.setBackground(new Color(87, 88, 90));
        panelDrinks.setBounds(170, 100, 470, 500);
        panelDrinks.setVisible(false);
        add(panelDrinks);

        coke = new JButton("Coke"); //kungpaockn
        coke.setFont(new Font("impact", Font.PLAIN, 11));
        coke.setBounds(10, 10, 120, 140);
        ImageIcon cokeIcon = new ImageIcon("images/coke.jpg");
        coke.setIcon(cokeIcon);
        coke.setHorizontalTextPosition(JButton.CENTER);
        coke.setVerticalTextPosition(JButton.BOTTOM);
        coke.setBackground(Color.white);
        coke.addActionListener(this);
        nmbrCoke = new JLabel("0", SwingConstants.CENTER);
        nmbrCoke.setBounds(10, 150, 120, 140);
        panelDrinks.add(coke);

        sprite = new JButton("Sprite"); //kungpaockn
        sprite.setFont(new Font("impact", Font.PLAIN, 11));
        sprite.setBounds(150, 10, 120, 140);
        ImageIcon spriteIcon = new ImageIcon("images/sprite.jpg");
        sprite.setIcon(spriteIcon);
        sprite.setHorizontalTextPosition(JButton.CENTER);
        sprite.setVerticalTextPosition(JButton.BOTTOM);
        sprite.setBackground(Color.white);
        sprite.addActionListener(this);
        nmbrSprite = new JLabel("0", SwingConstants.CENTER);
        nmbrSprite.setBounds(150, 150, 120, 140);
        panelDrinks.add(sprite);

        //------Dessert_Panel--------
        panelDessert = new JPanel();
        panelDessert.setBackground(new Color(87, 88, 90));
        panelDessert.setBounds(170, 100, 470, 500);
        panelDessert.setVisible(false);
        add(panelDessert);

        ubepastillas = new JButton("Ube Pastillas"); //kungpaockn
        ubepastillas.setFont(new Font("impact", Font.PLAIN, 11));
        ubepastillas.setBounds(10, 10, 120, 140);
        ImageIcon ubepastillasIcon = new ImageIcon("images/ubepastillas.jpg");
        ubepastillas.setIcon(ubepastillasIcon);
        ubepastillas.setHorizontalTextPosition(JButton.CENTER);
        ubepastillas.setVerticalTextPosition(JButton.BOTTOM);
        ubepastillas.setBackground(Color.white);
        ubepastillas.addActionListener(this);
        nmbrUbePastillas = new JLabel("0", SwingConstants.CENTER);
        nmbrUbePastillas.setBounds(10, 150, 120, 140);
        panelDessert.add(ubepastillas);

        mangosago = new JButton("Mango Sago"); //kungpaockn
        mangosago.setFont(new Font("impact", Font.PLAIN, 11));
        mangosago.setBounds(150, 10, 120, 140);
        ImageIcon mangosagoIcon = new ImageIcon("images/mangosago.jpg");
        mangosago.setIcon(mangosagoIcon);
        mangosago.setHorizontalTextPosition(JButton.CENTER);
        mangosago.setVerticalTextPosition(JButton.BOTTOM);
        mangosago.setBackground(Color.white);
        mangosago.addActionListener(this);
        nmbrMangoSago = new JLabel("0", SwingConstants.CENTER);
        nmbrMangoSago.setBounds(150, 150, 120, 140);
        panelDessert.add(mangosago);

        //-------designs-----------------
        Logo = new JLabel(); //Background
        ImageIcon logoPic = new ImageIcon("Images/Logo.png");
        Image logo = logoPic.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        Logo.setBounds(30, 5, 100, 100);
        Logo.setIcon(new ImageIcon(logo));
        add(Logo);

        divider = new JPanel();//black divider on the top
        divider.setBounds(150, 0, 5, 600);
        divider.setBackground(Color.BLACK);
        add(divider);

        whiteSquare = new JPanel();//white square
        whiteSquare.setBounds(0, 0, 150, 100);
        whiteSquare.setBackground(Color.white);
        add(whiteSquare);

        grayRectangle = new JPanel();// gray square
        grayRectangle.setBounds(0, 0, 150, 600);
        grayRectangle.setBackground(new Color(87, 88, 90));
        add(grayRectangle);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //-----------Change Panels------------
        if (e.getSource() == btnFood) {
            panelFood.setVisible(true);
            panelDrinks.setVisible(false);
            panelDessert.setVisible(false);
        } else if (e.getSource() == btnDrinks) {
            panelFood.setVisible(false);
            panelDrinks.setVisible(true);
            panelDessert.setVisible(false);
        } else if (e.getSource() == btnDessert) {
            panelFood.setVisible(false);
            panelDrinks.setVisible(false);
            panelDessert.setVisible(true);
        } //-----------Change Frames------------  
        else if (e.getSource() == btnInventory) {

        } else if (e.getSource() == btnReports) {
            new ReportsMenu();
            this.dispose();
        } else if (e.getSource() == btnWasteTracking) {

        } //-----------Inputting foood in the table------------
        else if (e.getSource() == kungPaoChicken) {
            int quantity = Integer.valueOf(nmbrkungPaoChicken.getText());
            quantity++;
            nmbrkungPaoChicken.setText(String.valueOf(quantity));
            addTable("Kung Pao Chicken", quantity, 150.00);
            calculateTotal();
        } else if (e.getSource() == mushroomChicken) {
            int quantity = Integer.valueOf(nmbrmushroomChicken.getText());
            quantity++;
            nmbrmushroomChicken.setText(String.valueOf(quantity));
            addTable("Mushroom Chicken", quantity, 200.00);
            calculateTotal();
        } else if (e.getSource() == orangeChicken) {
            int quantity = Integer.valueOf(nmbrorangeChicken.getText());
            quantity++;
            nmbrorangeChicken.setText(String.valueOf(quantity));
            addTable("Orange Chicken", quantity, 200.00);
            calculateTotal();
        } else if (e.getSource() == coke) {
            int quantity = Integer.valueOf(nmbrCoke.getText());
            quantity++;
            nmbrCoke.setText(String.valueOf(quantity));
            addTable("Coke", quantity, 79.00);
            calculateTotal();
        } else if (e.getSource() == sprite) {
            int quantity = Integer.valueOf(nmbrSprite.getText());
            quantity++;
            nmbrSprite.setText(String.valueOf(quantity));
            addTable("Sprite", quantity, 79.00);
            calculateTotal();
        } else if (e.getSource() == ubepastillas) {
            int quantity = Integer.valueOf(nmbrUbePastillas.getText());
            quantity++;
            nmbrUbePastillas.setText(String.valueOf(quantity));
            addTable("Ube Pastillas", quantity, 99.00);
            calculateTotal();
        } else if (e.getSource() == mangosago) {
            int quantity = Integer.valueOf(nmbrMangoSago.getText());
            quantity++;
            nmbrMangoSago.setText(String.valueOf(quantity));
            addTable("Mango Sago", quantity, 99.00);
            calculateTotal();

        } else if (e.getSource() == btnPay) { //payment

            // Calculate total amount
            calculateTotal();
            double totalAmount = Double.parseDouble(TotalNmbr.getText());

            // payment using JOptionPane
            String inputAmount = JOptionPane.showInputDialog(this, "Enter amount to pay:", "Payment", JOptionPane.PLAIN_MESSAGE);

            // Validate input
            if (inputAmount == null || inputAmount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double paidAmount = Double.parseDouble(inputAmount);

            // Check if payment is sufficient
            if (paidAmount < totalAmount) {
                JOptionPane.showMessageDialog(this, "Insufficient payment. Please enter an amount greater than or equal to " + totalAmount, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //pag cinlick mababawasann quantity sa database through BusinessLogic
            foodService.SubtractQuantity("kungPaoChicken", Integer.parseInt(nmbrkungPaoChicken.getText().trim()));
            foodService.SubtractQuantity("orangeChicken", Integer.parseInt(nmbrorangeChicken.getText().trim()));
            foodService.SubtractQuantity("mushroomChicken", Integer.parseInt(nmbrmushroomChicken.getText().trim()));
            foodService.SubtractQuantity("Coke", Integer.parseInt(nmbrCoke.getText().trim()));
            foodService.SubtractQuantity("Sprite", Integer.parseInt(nmbrSprite.getText().trim()));
            foodService.SubtractQuantity("Ube Pastillas", Integer.parseInt(nmbrUbePastillas.getText().trim()));
            foodService.SubtractQuantity("Mango Sago", Integer.parseInt(nmbrMangoSago.getText().trim()));

            //pag cinlick madadagdagan sold sa database through BusinessLogic
            foodService.AddSoldQuantity("kungPaoChicken", Integer.parseInt(nmbrkungPaoChicken.getText().trim()));
            foodService.AddSoldQuantity("orangeChicken", Integer.parseInt(nmbrorangeChicken.getText().trim()));
            foodService.AddSoldQuantity("mushroomChicken", Integer.parseInt(nmbrmushroomChicken.getText().trim()));
            foodService.AddSoldQuantity("Coke", Integer.parseInt(nmbrCoke.getText().trim()));
            foodService.AddSoldQuantity("Sprite", Integer.parseInt(nmbrSprite.getText().trim()));
            foodService.AddSoldQuantity("Ube Pastillas", Integer.parseInt(nmbrUbePastillas.getText().trim()));
            foodService.AddSoldQuantity("Mango Sago", Integer.parseInt(nmbrMangoSago.getText().trim()));

            // ------------------Display receipt-------------------
            DecimalFormat df = new DecimalFormat("0.00");

            String receiptHeader = String.format("%-25s\n", "**  Receipt  **");
            String receiptItems = "------------------------------------\n";
            String receiptBody = "";

            for (int i = 0; i < table.getRowCount(); i++) {
                String itemName = (String) table.getValueAt(i, 0);
                int quantity = (int) table.getValueAt(i, 1);
                double price = Double.parseDouble((String) table.getValueAt(i, 2));
                receiptBody += String.format("%-20s \n        Qty: %d \t   -   Sub-total: ₱%.2f\n", itemName, quantity, price);
            }

            String receiptSubtotal = String.format("------------------------------------\n"
                    + "Total: ₱\t%.2f\n", totalAmount);
            String receiptCash = String.format("Cash:\t ₱%.2f\n", paidAmount);
            String receiptChange = String.format("Change:\t ₱%.2f\n\n", paidAmount - totalAmount);
            String receiptThankYou = "**  Thank you!  **";

            String receipt = receiptHeader + receiptItems + receiptBody + receiptSubtotal + receiptCash + receiptChange + receiptThankYou;

            JOptionPane.showMessageDialog(this, receipt, "I-eat", JOptionPane.PLAIN_MESSAGE);

            SetAllTextToZero();// Clear table and total after successful payment
        } else if (e.getSource() == btnCancel) {
            SetAllTextToZero();
        }
    }
    //POSMenu Methods

    private void addTable(String name, int quantity, double price) { //addding product in the table that acts as text area for food order
        DefaultTableModel dt = (DefaultTableModel) table.getModel();
        DecimalFormat df = new DecimalFormat("0.00");
        double totPrice = price * quantity;
        String totalPrice = df.format(totPrice);

        for (int row = 0; row < table.getRowCount(); row++) { // for each yung row
            if (name.equals(table.getValueAt(row, 0))) { // kuhain yung foodName
                dt.removeRow(table.convertRowIndexToModel(row)); // if same ng name reremove yung  row na yun then dun na lang sa same name ilalagay
            }
        }

        Vector<Object> rowData = new Vector<>(); //added the name, quantity, and the price of product
        rowData.add(name);
        rowData.add(quantity);
        rowData.add(totalPrice);

        dt.addRow(rowData);
    }

    private void calculateTotal() {
        int priceRow = table.getRowCount(); // Calculate all the rows for price
        double totalAmount = 0;

        for (int i = 0; i < priceRow; i++) {
            double value = Double.valueOf(table.getValueAt(i, 2).toString()); // get the row for the price
            totalAmount += value;
        }

        DecimalFormat df = new DecimalFormat("0.00");
        TotalNmbr.setText(df.format(totalAmount));
    }

    public void SetAllTextToZero() { // since 2 buttons are needed to set all label and our table to zero we've deceided to make it a method
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        TotalNmbr.setText("0.00");

        nmbrkungPaoChicken.setText("0");
        nmbrmushroomChicken.setText("0");
        nmbrorangeChicken.setText("0");
        nmbrCoke.setText("0");
        nmbrSprite.setText("0");
        nmbrUbePastillas.setText("0");
        nmbrMangoSago.setText("0");
    }
}
