package GUI;

import Models.Food;
import BusinessLogic.FoodService;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReportsMenu extends JFrame implements ActionListener {
    
    private JPanel whiteSquare, grayRectangle, divider, whiteRectangle;
    private JLabel txtMessage, txtTopSold, txtLowestSold, BackGroundImage, Logo, txtReports;
    private JButton btnPOS, btnInventory, btnWasteTracking;
    private JTable foodTable;
    private DefaultTableModel table;
    FoodService foodService = new FoodService();
    
    ReportsMenu() {
        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Menu");
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        //--------Text-------------
        txtTopSold = new JLabel("I-eat Reports", SwingConstants.CENTER);
        txtTopSold.setBounds(200, 110, 650, 30);
        txtTopSold.setFont(new Font("Arimo", Font.BOLD, 27));
        txtTopSold.setForeground(new Color(253, 190, 2));
        add(txtTopSold);
        
        txtTopSold = new JLabel();
        txtTopSold.setBounds(220, 200, 600, 30);
        txtTopSold.setFont(new Font("Arimo", Font.BOLD, 27));
        add(txtTopSold);
        
        txtLowestSold = new JLabel();
        txtLowestSold.setBounds(220, 240, 600, 30);
        txtLowestSold.setFont(new Font("Arimo", Font.BOLD, 27));
        add(txtLowestSold);
        //---------table----------------
        String[] columnNames = {"Food Name", "Sold"};
        table = new DefaultTableModel(columnNames, 0);
        
        foodTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(foodTable);
        scrollPane.setBounds(220, 300, 600, 150);
        add(scrollPane);
        
        foodTable.setEnabled(false);
        PutFoodInTheTable();

        //------------Buttons---------
        btnPOS = new JButton("POS");//POS Button
        btnPOS.setBounds(30, 150, 100, 27);
        btnPOS.setBackground(new Color(0, 0, 0, 0));
        btnPOS.setOpaque(false);
        btnPOS.setBorderPainted(false);
        btnPOS.setForeground(Color.white);
        btnPOS.setFont(new Font("impact", Font.PLAIN, 27));
        btnPOS.addActionListener(this);
        add(btnPOS);
        
        btnInventory = new JButton("Inventory");//Inventory Button
        btnInventory.setBounds(0, 250, 150, 27);
        btnInventory.setBackground(new Color(0, 0, 0, 0));
        btnInventory.setOpaque(false);
        btnInventory.setBorderPainted(false);
        btnInventory.setForeground(Color.white);
        btnInventory.setFont(new Font("impact", Font.PLAIN, 27));
        btnInventory.addActionListener(this);
        add(btnInventory);
        
        txtReports = new JLabel("Reports");//Reports Button
        txtReports.setBounds(30, 350, 130, 27);
        txtReports.setBackground(new Color(0, 0, 0, 0));
        txtReports.setOpaque(false);
        txtReports.setForeground(Color.white);
        txtReports.setFont(new Font("impact", Font.PLAIN, 27));
        add(txtReports);
        
        btnWasteTracking = new JButton("Waste");//Waste Button
        btnWasteTracking.setBounds(0, 450, 150, 27);
        btnWasteTracking.setBackground(new Color(0, 0, 0, 0));
        btnWasteTracking.setOpaque(false);
        btnWasteTracking.setBorderPainted(false);
        btnWasteTracking.setForeground(Color.white);
        btnWasteTracking.setFont(new Font("impact", Font.PLAIN, 27));
        btnWasteTracking.addActionListener(this);
        add(btnWasteTracking);

        //-------designs-----------------
        whiteRectangle = new JPanel();
        whiteRectangle.setBounds(200, 100, 650, 400);
        whiteRectangle.setBackground(Color.white);
        add(whiteRectangle);
        
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
        
        BackGroundImage = new JLabel();
        ImageIcon backgroundPic = new ImageIcon("Images/Background2.jpg");
        Image bg = backgroundPic.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);
        BackGroundImage.setIcon(new ImageIcon(bg));
        BackGroundImage.setBounds(0, -20, 900, 620);
        add(BackGroundImage);
        setVisible(true);
    }
    
    private void PutFoodInTheTable() { //put the food from our database
        List<Food> foods = foodService.getAllFoods(); // get all food from our business Logic

        table.setRowCount(0);
        
        for (Food food : foods) { // add all food from out table
            table.addRow(new Object[]{food.foodName, food.sold});
        }
        SetTextToHighestSale(); // We add the foods po before we set the texts 
        SetTextToLowestSale(); //
    }
    
    private void SetTextToHighestSale() {
        if (table.getRowCount() > 0) {//First Row po
            Object foodName = table.getValueAt(0, 0); // First column po 
            Object sold = table.getValueAt(0, 1); // Second column po 

            txtTopSold.setText("Top Sale: " + foodName + " sold " + sold);
        } else {
            txtTopSold.setText("The store did not sale anything");
        }
    }
    
    private void SetTextToLowestSale() {
        int lastRowIndex = table.getRowCount() - 1;
        
        if (lastRowIndex >= 0) {            
            Object foodName = table.getValueAt(lastRowIndex, 0); // First column 
            Object sold = table.getValueAt(lastRowIndex, 1); // Second column (

            txtLowestSold.setText("Lowest Sale: " + foodName + " sold " + sold);
        } else {
            txtLowestSold.setText("No sales data available.");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPOS) {
            new POSMenu();
            this.dispose();
        } else if (e.getSource() == btnInventory) {
            
        } else if (e.getSource() == btnWasteTracking) {
            
        }
    }
}
