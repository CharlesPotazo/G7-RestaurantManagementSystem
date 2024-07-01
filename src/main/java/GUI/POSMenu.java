package GUI;

import BusinessLogic.FoodService;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class POSMenu extends JFrame implements ActionListener {

    private JPanel panelFood, panelDrinks, panelDessert;
    private JPanel whiteSquare, grayRectangle, divider;
    private JLabel POS, Logo, TotalTxt, TotalNmbr;
    private JButton btnInventory, btnReports, btnWasteTracking;
    private JButton btnFood, btnDrinks, btnDessert, btnPay;
    private JButton kungPaoChicken, mushroomChicken, orangeChicken, sprite, coke, ubepastillas, mangosago;
    private JLabel nmbrkungPaoChicken, nmbrmushroomChicken, nmbrorangeChicken;
    private JTable table;
    private JScrollPane scrollPane;
    FoodService foodService = new FoodService();

    POSMenu() {
        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("POS");
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        //------Text Area Order-------
        String[] columnNames = {"Food", "Quantity", "Price"};
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
        
        TotalNmbr  = new JLabel("0");
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

        kungPaoChicken = new JButton("Kungpaochkn"); //kungpaockn
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
        panelFood.add(nmbrkungPaoChicken);
        panelFood.add(kungPaoChicken);

        orangeChicken = new JButton("orngchkn"); //kungpaockn
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
        panelFood.add(nmbrorangeChicken);
        panelFood.add(orangeChicken);

        mushroomChicken = new JButton("mshrmchkn"); //kungpaockn
        mushroomChicken.setFont(new Font("impact", Font.PLAIN, 11));
        mushroomChicken.setBounds(290, 10, 120, 140);
        ImageIcon mushroomChickenIcon = new ImageIcon("images/mushroom chicken.jpg");
        mushroomChicken.setIcon(mushroomChickenIcon);
        mushroomChicken.setHorizontalTextPosition(JButton.CENTER);
        mushroomChicken.setVerticalTextPosition(JButton.BOTTOM);
        mushroomChicken.setBackground(Color.white);
        mushroomChicken.addActionListener(this);
        nmbrmushroomChicken = new JLabel("0", SwingConstants.CENTER);
        nmbrmushroomChicken.setBounds(290, 150, 120, 140);
        panelFood.add(nmbrmushroomChicken);
        panelFood.add(mushroomChicken);

        //------Drink_Panel--------------
        panelDrinks = new JPanel();
        panelDrinks.setBackground(Color.red);
        panelDrinks.setBounds(170, 100, 470, 500);
        panelDrinks.setVisible(false);
        add(panelDrinks);

        //------Dessert_Panel--------
        panelDessert = new JPanel();
        panelDessert.setBackground(Color.BLUE);
        panelDessert.setBounds(170, 100, 470, 500);
        panelDessert.setVisible(false);
        add(panelDessert);

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

    private void addTable(String name, int quantity, double price) {
        DefaultTableModel dt = (DefaultTableModel) table.getModel();
        DecimalFormat df = new DecimalFormat("0.00");
        double totPrice = price * quantity;
        String totalPrice = df.format(totPrice);

        for (int row = 0; row < table.getRowCount(); row++) {
            if (name.equals(table.getValueAt(row, 0))) {
                dt.removeRow(table.convertRowIndexToModel(row));
            }
        }

        Vector<Object> rowData = new Vector<>();
        rowData.add(name);
        rowData.add(quantity);
        rowData.add(totalPrice);

        dt.addRow(rowData);
    }

    private void calculateTotal() {
        int numRows = table.getRowCount();
        double totalAmount = 0;

        for (int i = 0; i < numRows; i++) {
            double value = Double.valueOf(table.getValueAt(i, 2).toString());
            totalAmount += value;
        }

        DecimalFormat df = new DecimalFormat("0.00");
        TotalNmbr.setText(df.format(totalAmount));
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
        }

        //-----------Inputting foood in the table------------
        if (e.getSource() == kungPaoChicken) {
            int quantity = Integer.valueOf(nmbrkungPaoChicken.getText());
            quantity++;
            nmbrkungPaoChicken.setText(String.valueOf(quantity));
            addTable("kungPaoChicken", quantity, 150.00);
            calculateTotal();
        } else if (e.getSource() == mushroomChicken) {
            int quantity = Integer.valueOf(nmbrmushroomChicken.getText());
            quantity++;
            nmbrmushroomChicken.setText(String.valueOf(quantity));
            addTable("mushroomChicken", quantity, 200.00);
            calculateTotal();
        } else if (e.getSource() == orangeChicken) {
            int quantity = Integer.valueOf(nmbrorangeChicken.getText());
            quantity++;
            nmbrorangeChicken.setText(String.valueOf(quantity));
            addTable("orangeChicken", quantity, 200.00);
            calculateTotal();

        } else if (e.getSource() == btnPay) {
                           //pag cinlick madadagdagan sa database
                            //
                        foodService.SubtractQuantity("kungPaoChicken", Integer.parseInt(nmbrkungPaoChicken.getText().trim()));
                      
                        
                        foodService.SubtractQuantity("orangeChicken", Integer.parseInt(nmbrorangeChicken.getText().trim()));
                      
                        
                        foodService.SubtractQuantity("mushroomChicken", Integer.parseInt(nmbrmushroomChicken.getText().trim()));
                        
                        
        }

    }
    
    public void addSoldQuantity(){
    foodService.AddSoldQuantity("kungPaoChicken", Integer.parseInt(nmbrkungPaoChicken.getText().trim()));
    foodService.AddSoldQuantity("orangeChicken", Integer.parseInt(nmbrorangeChicken.getText().trim()));
    foodService.AddSoldQuantity("mushroomChicken", Integer.parseInt(nmbrmushroomChicken.getText().trim()));
                        
    }
    
}
