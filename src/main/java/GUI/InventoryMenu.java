package GUI;

import Models.Food ;
import BusinessLogic.FoodService;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class InventoryMenu extends JFrame implements ActionListener {
    
    JFrame frameinv = new JFrame("");
    private JPanel whiteSquare, grayRectangle, divider, whiteRectangle, whiteRectangle2 ;
    private JLabel txtWelcome, txtMessage, txtMessage2, BackGroundImage, Logo;
    private JComboBox foodComboBox;
    private JTextField tfquantity;
    private JButton btnPOS, btnInventory, btnReports, btnWasteTracking, btnAdd, btnAdd2, btnReturn;
    private JTable tableinv;
    private DefaultTableModel tablemodel;
    FoodService foodService = new FoodService();


    InventoryMenu(){
        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Inventory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnPOS = new JButton("POS");//POS Button
        btnPOS.setBounds(30,150,100,27);
        btnPOS.setBackground(new Color(0, 0, 0, 0));
        btnPOS.setOpaque(false); 
        btnPOS.setBorderPainted(false);
        btnPOS.setForeground(Color.white);
        btnPOS.setFont(new Font("impact", Font.PLAIN, 27));
        btnPOS.addActionListener(this);
        add(btnPOS);
        
        btnInventory = new JButton("Inventory");//Inventory Button
        btnInventory.setBounds(0,250,150,27);
        btnInventory.setBackground(new Color(0, 0, 0, 0));
        btnInventory.setOpaque(false); 
        btnInventory.setBorderPainted(false);
        btnInventory.setForeground(Color.white);
        btnInventory.setFont(new Font("impact", Font.PLAIN, 27));
        btnInventory.addActionListener(this);
        add(btnInventory);
        
        btnReports = new JButton("Reports");//Reports Button
        btnReports.setBounds(10,350,130,27);
        btnReports.setBackground(new Color(0, 0, 0, 0));
        btnReports.setOpaque(false); 
        btnReports.setBorderPainted(false);
        btnReports.setForeground(Color.white);
        btnReports.setFont(new Font("impact", Font.PLAIN, 27));
        btnReports.addActionListener(this);
        add(btnReports);
        
        btnWasteTracking = new JButton("Waste");//Waste Button
        btnWasteTracking.setBounds(0,450,150,27);
        btnWasteTracking.setBackground(new Color(0, 0, 0, 0));
        btnWasteTracking.setOpaque(false); 
        btnWasteTracking.setBorderPainted(false);
        btnWasteTracking.setForeground(Color.white);
        btnWasteTracking.setFont(new Font("impact", Font.PLAIN, 27));
        btnWasteTracking.addActionListener(this);
        add(btnWasteTracking);  
        
        
        //-------designs-----------------
        //----------Table Panel-----------
        whiteRectangle = new JPanel();
        whiteRectangle.setBounds(200, 90, 650, 450);
        whiteRectangle.setBackground(Color.white);
        whiteRectangle.setLayout(null);
        add(whiteRectangle);
        
        btnAdd = new JButton("Add");//Add Button
        btnAdd.setBounds(400,340,190,65);
        btnAdd.setBackground(new Color(176,54,49));
        btnAdd.setBorderPainted(false);
        btnAdd.setForeground(new Color(255,255,255));
        btnAdd.setFont(new Font("impact", Font.PLAIN, 26));
        btnAdd.addActionListener(this);
        whiteRectangle.add(btnAdd);
        
        // Table and table model
        String[] columnNames = {"Food Name", "Quantity"};
        tablemodel = new DefaultTableModel(columnNames, 0);
        tableinv = new JTable(tablemodel);
        tableinv.getTableHeader().setBackground(new Color(176, 54, 49));
        tableinv.getTableHeader().setForeground(new Color(255, 255, 255));
        tableinv.getTableHeader().setFont(new Font("impact", Font.PLAIN, 20));
        
        // Scroll pane
        JScrollPane scp = new JScrollPane(tableinv);
        scp.setBounds(20, 20, 610, 310);
        tableinv.setEnabled(false);
        
        loadFood();
        whiteRectangle.add(scp); 
        setVisible(true);
        
        //----------Food Panel-----------
            whiteRectangle2 = new JPanel();
        whiteRectangle2.setBounds(200, 110, 650, 430);
        whiteRectangle2.setBackground(Color.white);whiteRectangle2.setVisible(false);
        whiteRectangle2.setLayout(null); // Use null layout for absolute positioning
        add(whiteRectangle2);

        btnReturn = new JButton("Return to \nInventory");
        btnReturn.setBounds(40, 331, 190, 65); // Set at bottom left
        btnReturn.setBackground(new Color(176, 54, 49));
        btnReturn.setBorderPainted(false);
        btnReturn.setForeground(new Color(255, 255, 255));
        btnReturn.setFont(new Font("Impact", Font.PLAIN, 18));
        btnReturn.addActionListener(this);
        whiteRectangle2.add(btnReturn);

        String[] foodItems = {"kungPaoChicken", "orangeChicken", "mushroomChicken", "Coke", "Sprite", "Ube Pastillas", "Mango Sago"};
        foodComboBox = new JComboBox<>(foodItems);
        foodComboBox.setBounds(50, 30, 250, 30);
        whiteRectangle2.add(foodComboBox);
        
        tfquantity = new JTextField("Add Quantity");
        tfquantity.setBounds(360, 53, 170, 35);
        tfquantity.setBackground(new Color(167,54,49));
        tfquantity.setBorder(null);
        tfquantity.setForeground(Color.white);
        
        whiteRectangle2.add(tfquantity);
        
        btnAdd2 = new JButton("Add");//Add Button
        btnAdd2.setBounds(400,340,190,65);
        btnAdd2.setBackground(new Color(176,54,49));
        btnAdd2.setBorderPainted(false);
        btnAdd2.setForeground(new Color(255,255,255));
        btnAdd2.setFont(new Font("impact", Font.PLAIN, 26));
        btnAdd2.addActionListener(this);
        whiteRectangle2.add(btnAdd2);
        
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
        grayRectangle.setBounds(0,0,150,600);
        grayRectangle.setBackground(new Color(87, 88, 90));
        add(grayRectangle);
        
        BackGroundImage = new JLabel();
        ImageIcon backgroundPic = new ImageIcon("Images/background3.jpg");
        Image bg = backgroundPic.getImage().getScaledInstance(900,700, Image.SCALE_SMOOTH);
        BackGroundImage.setIcon(new ImageIcon(bg));
        BackGroundImage.setBounds(0, -20, 900,620);
        add(BackGroundImage);
        
        setVisible(true);
    }

    
    private void loadFood() {
        
        List<Food> foods = foodService.getAllFoods();
        
        tablemodel.setRowCount(0);

        for (Food food : foods) {
            tablemodel.addRow(new Object[]{food.foodName, food.quantity});
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == btnPOS){
            
       }
       else if(e.getSource() == btnInventory){
       
       }
       else if(e.getSource() == btnReports){
       
       }
       else if(e.getSource() == btnWasteTracking){
       
       }
       else if (e.getSource() ==btnAdd){
           whiteRectangle.setVisible(false);
           whiteRectangle2.setVisible(true);
       }
       else if (e.getSource() ==btnAdd2){
          try {
                    String selectedFood = (String) foodComboBox.getSelectedItem();
           int quantityToAdd = Integer.parseInt(tfquantity.getText());
           
           foodService.AddQuantity(selectedFood, quantityToAdd);
           loadFood();
           JOptionPane.showMessageDialog(this, "Successfully Added", "Added", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Not Added", "Input Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
       }
       }
       else if (e.getSource() ==btnReturn){
           whiteRectangle.setVisible(true);
           whiteRectangle2.setVisible(false);
       }
    }
}
