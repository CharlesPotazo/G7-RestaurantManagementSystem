
package GUI;

import Models.Food;
import BusinessLogic.FoodService;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CJ
 */
public class ReportsMenu extends JFrame implements ActionListener {
    private JPanel whiteSquare, grayRectangle, divider, whiteRectangle;
    private JLabel txtWelcome, txtMessage, txtMessage2, BackGroundImage, Logo;
    private JButton btnPOS, btnInventory, btnReports, btnWasteTracking;
    private JTable foodTable;
    private DefaultTableModel tableModel;
    FoodService foodService = new FoodService();


    ReportsMenu(){
        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Menu");
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        
        //---------table----------------
       
        String[] columnNames = { "Food Name", "Sold" };
        tableModel = new DefaultTableModel(columnNames, 0);

        foodTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(foodTable);
        scrollPane.setBounds(220, 120, 600, 350);
        add(scrollPane);
        
        loadFoodData();
        
        //------------Buttons---------

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
        
        whiteRectangle = new JPanel();
        whiteRectangle.setBounds(200,100,650,400);
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
        grayRectangle.setBounds(0,0,150,600);
        grayRectangle.setBackground(new Color(87, 88, 90));
        add(grayRectangle);
        
        BackGroundImage = new JLabel();
        ImageIcon backgroundPic = new ImageIcon("Images/Background2.jpg");
        Image bg = backgroundPic.getImage().getScaledInstance(900,700, Image.SCALE_SMOOTH);
        BackGroundImage.setIcon(new ImageIcon(bg));
        BackGroundImage.setBounds(0, -20, 900,620);
        add(BackGroundImage);
        setVisible(true);
    }
    
    
    private void loadFoodData() {
        
        List<Food> foods = foodService.getAllFoods();
        
        tableModel.setRowCount(0);

        for (Food food : foods) {
            tableModel.addRow(new Object[]{food.foodName, food.sold});
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
    }
}
