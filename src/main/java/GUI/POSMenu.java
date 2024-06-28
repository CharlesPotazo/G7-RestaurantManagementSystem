package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class POSMenu extends JFrame implements ActionListener{
    
    private JPanel panelFood, panelDrinks, panelDessert ;
    private JTextArea orderArea;
    private JPanel whiteSquare, grayRectangle, divider;
    private JLabel POS, Logo;
    private JButton btnInventory, btnReports, btnWasteTracking;
    private JButton btnFood, btnDrinks , btnDessert, Food1,Food2,Food3, Drink1,Drink2, Dessert1,Dessert2;
    
    POSMenu(){
        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("POS");
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        
        //------Text Area Order-------
        
        orderArea = new JTextArea();
        orderArea.setEditable(false);
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        orderArea.setBorder(blackBorder);
        orderArea.setBounds(660, 10, 240, 500);
        add(orderArea);
        
       
        
        //------------Buttons---------
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
        POS.setBounds(55,150,100,27);
        POS.setForeground(Color.white);
        POS.setFont(new Font("impact", Font.PLAIN, 27));
        add(POS);
        
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
        
        //------Food_Panel--------------
        
        panelFood = new JPanel();
        panelFood.setBackground(new Color(87, 88, 90));
        panelFood.setBounds(170, 100, 470,500);
        panelFood.setVisible(true);
        add(panelFood);
        
                //panelFood.add(    button or panel) ipasok sa loob ng panel
        
        //------Drink_Panel--------------
        
        panelDrinks = new JPanel();
        panelDrinks.setBackground(Color.red);
        panelDrinks.setBounds(170, 100, 470,500);
        panelDrinks.setVisible(false);
        add(panelDrinks);
        
        
        //------Dessert_Panel--------
        
        panelDessert = new JPanel();
        panelDessert.setBackground(Color.BLUE);
        panelDessert.setBounds(170, 100, 470,500);
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
        grayRectangle.setBounds(0,0,150,600);
        grayRectangle.setBackground(new Color(87, 88, 90));
        add(grayRectangle);
        
        setVisible(true);
    }
    
   

    @Override
    public void actionPerformed(ActionEvent e) {
       
       if(e.getSource() == btnFood){
        panelFood.setVisible(true);
        panelDrinks.setVisible(false);
        panelDessert .setVisible(false);
       }
       else if(e.getSource() == btnDrinks){
        panelFood.setVisible(false);
        panelDrinks.setVisible(true);
        panelDessert .setVisible(false);
       }
       else if(e.getSource() == btnDessert){
        panelFood.setVisible(false);
        panelDrinks.setVisible(false);
        panelDessert .setVisible(true);
       }
        
    }
}
