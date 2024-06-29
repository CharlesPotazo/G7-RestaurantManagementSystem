package GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.Border;

public class POSMenu extends JFrame implements ActionListener{
    
    private JPanel panelFood, panelDrinks, panelDessert ;
    private JTextArea orderArea, productArea, totalArea;
    private JPanel whiteSquare, grayRectangle, divider;
    private JLabel POS, Logo;
    private JButton btnInventory, btnReports, btnWasteTracking;
    private JButton btnFood, btnDrinks , btnDessert, Food1,Food2,Food3, Drink1,Drink2, Dessert1,Dessert2; 
    private JLabel Orange, lblOrange, pesosignOrange, priceOrange, 
            Kungpao, lblKungpao, pesosignKungpao, priceKungpao,
            Mushroom, lblMushroom, pesosignMushroom, priceMushroom, totalLabel,
            Coke, lblCoke, pesosignCoke, priceCoke,
            Sprite, lblSprite, pesosignSprite, priceSprite,
            Ube, lblUbe, pesosignUbe, priceUbe,
            Mango, lblMango, pesosignMango, priceMango;
    private JPanel squareOrange, squareKungpao, squareMushroom, squareUbe, squareCoke, squareSprite, squareMango;;
    private JTextField quantityOrangeField, quantityKungpaoField, quantityMushroomField, quantityCokeField, quantitySpriteField, quantityUbeField, quantityMangoField, totalField;
    private JButton addOrange, subtractOrange,
            addKungpao, subtractKungpao,
            addMushroom, subtractMushroom, btnCheckout,
            addUbe, subtractUbe,
            addCoke, subtractCoke,
            addSprite, subtractSprite,
            addMango, subtractMango;

    private double orangePrice, kungpaoPrice, mushroomPrice, cokePrice, spritePrice, ubePrice, mangoPrice;
    private int orangeQuantity, kungpaoQuantity, mushroomQuantity, cokeQuantity, spriteQuantity, ubeQuantity, mangoQuantity;
    
    private static final String URL = "jdbc:mysql://localhost:3306/reports"; //your_localhost
    private static final String USER = "root";
    private static final String PASSWORD = "your_password"; //password in Mysql
    
    
    POSMenu(){
        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("POS");
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        
        //------Text Area Order-------
        
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        
        orderArea = new JTextArea();
        orderArea.setEditable(false);
        orderArea.setBorder(blackBorder);
        orderArea.setBounds(650, 10, 225, 380);
        add(orderArea);
        
        totalArea = new JTextArea();
        totalArea.setEditable(false);
        totalArea.setBorder(blackBorder);
        totalArea.setBounds(650, 390, 225, 100);
        add(totalArea);
        
        totalLabel = new JLabel("Total:");

        totalField = new JTextField("0.00");
        
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
        
        btnCheckout = new JButton("Checkout");//Checkout Button
        btnCheckout.setBounds(650, 500, 225, 30);
        btnCheckout.addActionListener(this);
        add(btnCheckout);
        
        //------Food_Panel--------------
        
        panelFood = new JPanel();
        panelFood.setLayout(null);
        panelFood.setBackground(new Color(87, 88, 90));
        panelFood.setBounds(170, 100, 470,500);
        panelFood.setVisible(true);
        add(panelFood);
        
        //--------------- Orange Chicken ---------------
        orangePrice = 130.00;
        orangeQuantity = 0;
        
        Orange = new JLabel();
        ImageIcon orangePic = new ImageIcon("images/orange chicken.jpg");
        Image orange = orangePic.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        Orange.setBounds(40, 30, 100, 80);
        Orange.setIcon(new ImageIcon(orange));
        panelFood.add(Orange);
        
        lblOrange = new JLabel("Orange Chicken");
        lblOrange.setBounds(45, 130, 100, 20);
        panelFood.add(lblOrange);

        pesosignOrange = new JLabel("₱");
        pesosignOrange.setBounds(65, 150, 100, 20);
        panelFood.add(pesosignOrange);

        priceOrange = new JLabel(Double.toString(orangePrice));
        priceOrange.setBounds(75, 150, 50, 20);
        panelFood.add(priceOrange);

        quantityOrangeField = new JTextField(Integer.toString(orangeQuantity));

        addOrange = new JButton("+");
        addOrange.setBounds(25, 185, 60, 20);
        addOrange.addActionListener(this);
        panelFood.add(addOrange);

        subtractOrange = new JButton("-");
        subtractOrange.setBounds(95, 185, 60, 20);
        subtractOrange.addActionListener(this);
        subtractOrange.setEnabled(false); 
        panelFood.add(subtractOrange);
        
        squareOrange = new JPanel(); 
        squareOrange.setBounds(25, 10, 130, 170);
        squareOrange.setBackground(Color.WHITE);
        squareOrange.setVisible(true);
        panelFood.add(squareOrange);
        
        //--------------- Kung Pao Chicken ---------------
        kungpaoPrice = 130.00;
        kungpaoQuantity = 0;
        
        Kungpao = new JLabel();
        ImageIcon kungpaoPic = new ImageIcon("images/kungpao chicken.jpg");
        Image kungpao = kungpaoPic.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        Kungpao.setBounds(180, 30, 100, 80);
        Kungpao.setIcon(new ImageIcon(kungpao));
        panelFood.add(Kungpao);
        
        lblKungpao = new JLabel("Kung Pao Chicken");
        lblKungpao.setBounds(180, 130, 150, 20);
        panelFood.add(lblKungpao);

        pesosignKungpao = new JLabel("₱");
        pesosignKungpao.setBounds(205, 150, 100, 20);
        panelFood.add(pesosignKungpao);

        priceKungpao = new JLabel(Double.toString(kungpaoPrice));
        priceKungpao.setBounds(215, 150, 50, 20);
        panelFood.add(priceKungpao);

        quantityKungpaoField = new JTextField(Integer.toString(kungpaoQuantity));

        addKungpao = new JButton("+");
        addKungpao.setBounds(165, 185, 60, 20);
        addKungpao.addActionListener(this);
        panelFood.add(addKungpao);

        subtractKungpao = new JButton("-");
        subtractKungpao.setBounds(235, 185, 60, 20);
        subtractKungpao.addActionListener(this);
        subtractKungpao.setEnabled(false); 
        panelFood.add(subtractKungpao);
        
        squareKungpao = new JPanel(); 
        squareKungpao.setBounds(165, 10, 130, 170);
        squareKungpao.setBackground(Color.WHITE);
        squareKungpao.setVisible(true);
        panelFood.add(squareKungpao);

        
        //--------------- Mushroom Chicken ---------------
        mushroomPrice = 130.00;
        mushroomQuantity = 0;
        
        Mushroom = new JLabel();
        ImageIcon mushroomPic = new ImageIcon("images/mushroom chicken.jpg");
        Image mushroom = mushroomPic.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        Mushroom.setBounds(315, 30, 100, 80);
        Mushroom.setIcon(new ImageIcon(mushroom));
        panelFood.add(Mushroom);
        
        lblMushroom = new JLabel("Mushroom Chicken");
        lblMushroom.setBounds(315, 130, 150, 20);
        panelFood.add(lblMushroom);

        pesosignMushroom = new JLabel("₱");
        pesosignMushroom.setBounds(345, 150, 100, 20);
        panelFood.add(pesosignMushroom);

        priceMushroom = new JLabel(Double.toString(kungpaoPrice));
        priceMushroom.setBounds(355, 150, 50, 20);
        panelFood.add(priceMushroom);

        quantityMushroomField = new JTextField(Integer.toString(kungpaoQuantity));

        addMushroom = new JButton("+");
        addMushroom.setBounds(305, 185, 60, 20);
        addMushroom.addActionListener(this);
        panelFood.add(addMushroom);

        subtractMushroom = new JButton("-");
        subtractMushroom.setBounds(375, 185, 60, 20);
        subtractMushroom.addActionListener(this);
        subtractMushroom.setEnabled(false); 
        panelFood.add(subtractMushroom);
        
        squareMushroom = new JPanel(); 
        squareMushroom.setBounds(305, 10, 130, 170);
        squareMushroom.setBackground(Color.WHITE);
        squareMushroom.setVisible(true);
        panelFood.add(squareMushroom);
        
                //panelFood.add(    button or panel) ipasok sa loob ng panel
        
        //------Drink_Panel--------------
        
        panelDrinks = new JPanel();
        panelDrinks.setBackground(Color.red);
        panelDrinks.setLayout(null);
        panelDrinks.setBounds(170, 100, 470,500);
        panelDrinks.setVisible(true);
        add(panelDrinks);
        
        //--------------- Coke ---------------
        cokePrice = 130.00;
        cokeQuantity = 0;
        
        Coke = new JLabel();
        ImageIcon cokePic = new ImageIcon("images/coke.jpg");
        Image coke = cokePic.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        Coke.setBounds(25, 30, 100, 80);
        Coke.setIcon(new ImageIcon(coke));
        panelDrinks.add(Coke);
        
        lblCoke = new JLabel("Coke");
        lblCoke.setBounds(30, 130, 100, 20);
        panelDrinks.add(lblCoke);

        pesosignCoke = new JLabel("₱");
        pesosignCoke.setBounds(50, 150, 100, 20);
        panelDrinks.add(pesosignCoke);

        priceCoke = new JLabel(Double.toString(cokePrice));
        priceCoke.setBounds(60, 150, 50, 20);
        panelDrinks.add(priceCoke);

        quantityCokeField = new JTextField(Integer.toString(cokeQuantity));

        addCoke = new JButton("+");
        addCoke.setBounds(10, 185, 60, 20);
        addCoke.addActionListener(this);
        panelDrinks.add(addCoke);

        subtractCoke = new JButton("-");
        subtractCoke.setBounds(80, 185, 60, 20);
        subtractCoke.addActionListener(this);
        subtractCoke.setEnabled(false); 
        panelDrinks.add(subtractCoke);
        
        squareCoke = new JPanel(); 
        squareCoke.setBounds(10, 10, 130, 170);
        squareCoke.setBackground(Color.WHITE);
        squareCoke.setVisible(true);
        panelDrinks.add(squareCoke);
        
        //--------------- Sprite ---------------
        spritePrice = 130.00;
        spriteQuantity = 0;
        
        Sprite = new JLabel();
        ImageIcon spritePic = new ImageIcon("images/sprite.jpg");
        Image sprite = spritePic.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        Sprite.setBounds(165, 30, 100, 80);
        Sprite.setIcon(new ImageIcon(sprite));
        panelDrinks.add(Sprite);
        
        lblSprite = new JLabel("Sprite");
        lblSprite.setBounds(165, 130, 150, 20);
        panelDrinks.add(lblSprite);

        pesosignSprite = new JLabel("₱");
        pesosignSprite.setBounds(190, 150, 100, 20);
        panelDrinks.add(pesosignSprite);

        priceSprite = new JLabel(Double.toString(spritePrice));
        priceSprite.setBounds(200, 150, 50, 20);
        panelDrinks.add(priceSprite);

        quantitySpriteField = new JTextField(Integer.toString(spriteQuantity));

        addSprite = new JButton("+");
        addSprite.setBounds(150, 185, 60, 20);
        addSprite.addActionListener(this);
        panelDrinks.add(addSprite);

        subtractSprite = new JButton("-");
        subtractSprite.setBounds(220, 185, 60, 20);
        subtractSprite.addActionListener(this);
        subtractSprite.setEnabled(false);
        panelDrinks.add(subtractSprite);
        
        squareSprite = new JPanel(); 
        squareSprite.setBounds(150, 10, 130, 170);
        squareSprite.setBackground(Color.WHITE);
        squareSprite.setVisible(true);
        panelDrinks.add(squareSprite);
        
        
        //------Dessert_Panel--------
        
        panelDessert = new JPanel();
        panelDessert.setBackground(Color.BLUE);
        panelDessert.setLayout(null);
        panelDessert.setBounds(170, 100, 470,500);
        panelDessert.setVisible(true);
        add(panelDessert);
        
        //--------------- Ube Pastillas ---------------
        ubePrice = 130.00;
        ubeQuantity = 0;
        
        Ube = new JLabel();
        ImageIcon ubePic = new ImageIcon("images/ubepastillas.jpg");
        Image ube = ubePic.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        Ube.setBounds(25, 30, 100, 80);
        Ube.setIcon(new ImageIcon(ube));
        panelDessert.add(Ube);
        
        lblUbe = new JLabel("Ube Pastillas");
        lblUbe.setBounds(30, 130, 100, 20);
        panelDessert.add(lblUbe);

        pesosignUbe = new JLabel("₱");
        pesosignUbe.setBounds(50, 150, 100, 20);
        panelDessert.add(pesosignUbe);

        priceUbe = new JLabel(Double.toString(ubePrice));
        priceUbe.setBounds(60, 150, 50, 20);
        panelDessert.add(priceUbe);

        quantityUbeField = new JTextField(Integer.toString(ubeQuantity));

        addUbe = new JButton("+");
        addUbe.setBounds(10, 185, 60, 20);
        addUbe.addActionListener(this);
        panelDessert.add(addUbe);

        subtractUbe = new JButton("-");
        subtractUbe.setBounds(80, 185, 60, 20);
        subtractUbe.addActionListener(this);
        subtractUbe.setEnabled(false); 
        panelDessert.add(subtractUbe);
        
        squareUbe = new JPanel(); 
        squareUbe.setBounds(10, 10, 130, 170);
        squareUbe.setBackground(Color.WHITE);
        squareUbe.setVisible(true);
        panelDessert.add(squareUbe);
        
        //--------------- Mango Sago ---------------
        mangoPrice = 130.00;
        mangoQuantity = 0;
        
        Mango = new JLabel();
        ImageIcon mangoPic = new ImageIcon("images/mangosago.jpg");
        Image mango = mangoPic.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        Mango.setBounds(165, 30, 100, 80);
        Mango.setIcon(new ImageIcon(mango));
        panelDessert.add(Mango);
        
        lblMango = new JLabel("Mango Sago");
        lblMango.setBounds(165, 130, 150, 20);
        panelDessert.add(lblMango);

        pesosignMango = new JLabel("₱");
        pesosignMango.setBounds(190, 150, 100, 20);
        panelDessert.add(pesosignMango);

        priceMango = new JLabel(Double.toString(mangoPrice));
        priceMango.setBounds(200, 150, 50, 20);
        panelDessert.add(priceMango);

        quantityMangoField = new JTextField(Integer.toString(mangoQuantity));

        addMango = new JButton("+");
        addMango.setBounds(150, 185, 60, 20);
        addMango.addActionListener(this);
        panelDessert.add(addMango);

        subtractMango = new JButton("-");
        subtractMango.setBounds(220, 185, 60, 20);
        subtractMango.addActionListener(this);
        subtractMango.setEnabled(false); 
        panelDessert.add(subtractMango);
        
        squareMango = new JPanel(); 
        squareMango.setBounds(150, 10, 130, 170);
        squareMango.setBackground(Color.WHITE);
        squareMango.setVisible(true);
        panelDessert.add(squareMango);
        
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
       else if (e.getSource() == addOrange) {
            orangeQuantity++;
            quantityOrangeField.setText(Integer.toString(orangeQuantity));
            updateTotal();
            subtractOrange.setEnabled(true); 
            updateProductInfoField();
            
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "UPDATE foods SET quantity = quantity - 1, sold = sold + 1 WHERE product_name = 'Orange Chicken'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Quantity for 'Orange Chicken' decreased by 1");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (e.getSource() == subtractOrange) {
            if (orangeQuantity > 0) {
                try {
                    // Database update for decreasing quantity (replace with your actual product name)
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql = "UPDATE foods SET quantity = quantity + 1, sold = sold - 1 WHERE product_name = 'Orange Chicken'";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Quantity for 'Orange Chicken' increased by 1");

                    orangeQuantity--;
                    quantityOrangeField.setText(Integer.toString(orangeQuantity));
                    updateTotal();
                    if (orangeQuantity == 0) {
                        subtractOrange.setEnabled(false); 
                    }
                    updateProductInfoField();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        //Kung Pao Chicken
        } else if (e.getSource() == addKungpao) {
            kungpaoQuantity++;
            quantityKungpaoField.setText(Integer.toString(kungpaoQuantity));
            updateTotal();
            subtractKungpao.setEnabled(true); 
            updateProductInfoField();
            
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "UPDATE foods SET quantity = quantity - 1, sold = sold + 1 WHERE product_name = 'Kung Pao Chicken'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Quantity for 'Kung Pao Chicken' decreased by 1");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (e.getSource() == subtractKungpao) {
            if (kungpaoQuantity > 0) {
                try {
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql = "UPDATE foods SET quantity = quantity + 1, sold = sold - 1 WHERE product_name = 'Kung Pao Chicken'";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Quantity for 'Kung Pao Chicken' increased by 1");

                    kungpaoQuantity--;
                    quantityKungpaoField.setText(Integer.toString(kungpaoQuantity));
                    updateTotal();
                    if (kungpaoQuantity == 0) {
                        subtractKungpao.setEnabled(false); 
                    }
                    updateProductInfoField();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        //Mushroom Chicken
        }else if (e.getSource() == addMushroom) {
            mushroomQuantity++;
            quantityMushroomField.setText(Integer.toString(mushroomQuantity));
            updateTotal();
            subtractMushroom.setEnabled(true); 
            updateProductInfoField();
            
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "UPDATE foods SET quantity = quantity - 1, sold = sold + 1 WHERE product_name = 'Mushroom Chicken'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Quantity for 'Mushroom Chicken' decreased by 1");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (e.getSource() == subtractMushroom) {
            if (mushroomQuantity > 0) {
                try {
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql = "UPDATE foods SET quantity = quantity + 1, sold = sold - 1 WHERE product_name = 'Orange Chicken'";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Quantity for 'Orange Chicken' increased by 1");

                    mushroomQuantity--;
                    quantityMushroomField.setText(Integer.toString(mushroomQuantity));
                    updateTotal();
                    if (mushroomQuantity == 0) {
                        subtractMushroom.setEnabled(false); 
                    }
                    updateProductInfoField();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            //Coke
            else if (e.getSource() == addCoke) {
            cokeQuantity++;
            quantityCokeField.setText(Integer.toString(cokeQuantity));
            updateTotal();
            subtractCoke.setEnabled(true);
            updateProductInfoField();
            
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "UPDATE foods SET quantity = quantity - 1, sold = sold + 1 WHERE product_name = 'Coke'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Quantity for 'Coke' decreased by 1");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (e.getSource() == subtractCoke) {
            if (cokeQuantity > 0) {
                try {
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql = "UPDATE foods SET quantity = quantity + 1, sold = sold - 1 WHERE product_name = 'Coke'";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Quantity for 'Coke' increased by 1");

                    cokeQuantity--;
                    quantityCokeField.setText(Integer.toString(cokeQuantity));
                    updateTotal();
                    if (cokeQuantity == 0) {
                        subtractCoke.setEnabled(false); 
                    }
                    updateProductInfoField();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
       //Sprite
       else if (e.getSource() == addSprite) {
            spriteQuantity++;
            quantitySpriteField.setText(Integer.toString(spriteQuantity));
            updateTotal();
            subtractSprite.setEnabled(true); 
            updateProductInfoField();
            
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "UPDATE foods SET quantity = quantity - 1, sold = sold + 1 WHERE product_name = 'Sprite'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Quantity for 'Sprite' decreased by 1");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (e.getSource() == subtractSprite) {
            if (spriteQuantity > 0) {
                try {
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql = "UPDATE foods SET quantity = quantity + 1, sold = sold - 1 WHERE product_name = 'Sprite'";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Quantity for 'Sprite' increased by 1");

                    cokeQuantity--;
                    quantityCokeField.setText(Integer.toString(cokeQuantity));
                    updateTotal();
                    if (cokeQuantity == 0) {
                        subtractOrange.setEnabled(false); 
                    }
                    updateProductInfoField();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        //Ube
    else if (e.getSource() == addUbe) {
            ubeQuantity++;
            quantityUbeField.setText(Integer.toString(ubeQuantity));
            updateTotal();
            subtractUbe.setEnabled(true); 
            updateProductInfoField();
            
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "UPDATE foods SET quantity = quantity - 1, sold = sold + 1 WHERE product_name = 'Ube Pastillas'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Quantity for 'Ube Pastillas' decreased by 1");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (e.getSource() == subtractUbe) {
            if (ubeQuantity > 0) {
                try {
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql = "UPDATE foods SET quantity = quantity + 1, sold = sold - 1 WHERE product_name = 'Ube Pastillas'";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Quantity for 'Ube Pastillas' increased by 1");

                    ubeQuantity--;
                    quantityUbeField.setText(Integer.toString(ubeQuantity));
                    updateTotal();
                    if (ubeQuantity == 0) {
                        subtractUbe.setEnabled(false); 
                    }
                    updateProductInfoField();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        //Mango
         else if (e.getSource() == addMango) {
            mangoQuantity++;
            quantityMangoField.setText(Integer.toString(mangoQuantity));
            updateTotal();
            subtractMango.setEnabled(true); 
            updateProductInfoField();
            
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "UPDATE foods SET quantity = quantity - 1, sold = sold + 1 WHERE product_name = 'Mango Sago'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Quantity for 'Mango Sago' decreased by 1");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (e.getSource() == subtractMango) {
            if (mangoQuantity > 0) {
                try {
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql = "UPDATE foods SET quantity = quantity + 1, sold = sold - 1 WHERE product_name = 'Mango Sago'";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Quantity for 'Mango Sago' increased by 1");

                    mangoQuantity--;
                    quantityMangoField.setText(Integer.toString(mangoQuantity));
                    updateTotal();
                    if (mangoQuantity == 0) {
                        subtractOrange.setEnabled(false); 
                    }
                    updateProductInfoField();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    
        //Checkout
        } else if (e.getSource() == btnCheckout) {
            
            String review = "Checkout:\n";
            if (orangeQuantity > 0) {
                review += "Orange Chicken: " + orangeQuantity + " x ₱" + orangePrice + " = ₱" + (orangeQuantity * orangePrice) + "\n";
            }
            if (kungpaoQuantity > 0) {
                review += "Kung Pao Chicken: " + kungpaoQuantity + " x ₱" + kungpaoPrice + " = ₱" + (kungpaoQuantity * kungpaoPrice) + "\n";
            }
            if (mushroomQuantity > 0) {
                review += "Mushroom Chicken: " + mushroomQuantity + " x ₱" + mushroomPrice + " = ₱" + (mushroomQuantity * mushroomPrice) + "\n";
            }
             if (cokeQuantity > 0) {
                review += "Coke: " + cokeQuantity + " x ₱" + cokePrice + " = ₱" + (cokeQuantity * cokePrice) + "\n";
            }
              if (spriteQuantity > 0) {
                review += "Sprite: " + spriteQuantity + " x ₱" + spritePrice + " = ₱" + (spriteQuantity * spritePrice) + "\n";
            }
               if (ubeQuantity > 0) {
                review += "Ube Pastillas: " + ubeQuantity + " x ₱" + ubePrice + " = ₱" + (ubeQuantity * ubePrice) + "\n";
            }
                if (mangoQuantity > 0) {
                review += "Mango Sago: " + mangoQuantity + " x ₱" + mangoPrice + " = ₱" + (mangoQuantity * mangoPrice) + "\n";
            }

            review += "Total: $" + totalField.getText();
            JOptionPane.showMessageDialog(this, review, "Order Details", JOptionPane.INFORMATION_MESSAGE);
        
            orangeQuantity = 0;
            kungpaoQuantity = 0;
            mushroomQuantity = 0;
            cokeQuantity = 0;
            spriteQuantity = 0;
            ubeQuantity = 0;
            mangoQuantity = 0;
            
            quantityOrangeField.setText("0");
            quantityKungpaoField.setText("0");
            quantityMushroomField.setText("0");
            quantityCokeField.setText("0");
            quantitySpriteField.setText("0");
            quantityUbeField.setText("0");
            quantityMangoField.setText("0");
            updateTotal();
            
            subtractOrange.setEnabled(false); 
            subtractKungpao.setEnabled(false); 
            subtractMushroom.setEnabled(false); 
            subtractCoke.setEnabled(false); 
            subtractSprite.setEnabled(false); 
            subtractUbe.setEnabled(false); 
            subtractMango.setEnabled(false); 
            updateProductInfoField();
            
        } 
    }
    
    private void updateTotal() {
        double total = orangeQuantity * orangePrice + kungpaoQuantity * kungpaoPrice + mushroomQuantity * mushroomPrice + cokeQuantity * cokePrice + spriteQuantity * spritePrice + ubeQuantity * ubePrice + mangoQuantity * mangoPrice;
        totalField.setText(String.format("%.2f", total));
        totalArea.setText(String.format(" Total: ₱%.2f", total));
    }
    
    private void updateProductInfoField() {
        StringBuilder productInfo = new StringBuilder();

        if (orangeQuantity > 0) {
            productInfo.append("Orange Chicken \n     Qty: ").append(orangeQuantity).append(" - subTotal: ₱").append(String.format("%.2f", orangeQuantity * orangePrice)).append("\n");
        }

        if (kungpaoQuantity > 0) {
            if (productInfo.length() > 0) {
                productInfo.append("\n"); // Add a newline if Product 1 is already displayed
            }
            productInfo.append("Kung Pao Chicken \n     Qty: ").append(kungpaoQuantity).append(" - subTotal: ₱").append(String.format("%.2f", kungpaoQuantity * kungpaoPrice)).append("\n");
        }

        if (mushroomQuantity > 0) {
            if (productInfo.length() > 0) {
                productInfo.append("\n"); 
            }
            productInfo.append("Mushroom Chicken \n     Qty: ").append(mushroomQuantity).append(" - subTotal: ₱").append(String.format("%.2f", mushroomQuantity * mushroomPrice)).append("\n");
        }
        if (cokeQuantity > 0) {
            if (productInfo.length() > 0) {
                productInfo.append("\n"); 
            }
            productInfo.append("Coke \n     Qty: ").append(cokeQuantity).append(" - subTotal: ₱").append(String.format("%.2f", cokeQuantity * cokePrice)).append("\n");
        }

        if (spriteQuantity > 0) {
            if (productInfo.length() > 0) {
                productInfo.append("\n"); 
            }
            productInfo.append("Sprite \n     Qty: ").append(spriteQuantity).append(" - subTotal: ₱").append(String.format("%.2f", spriteQuantity * spritePrice)).append("\n");
        }
        
        if (ubeQuantity > 0) {
            if (productInfo.length() > 0) {
                productInfo.append("\n"); 
            }
            productInfo.append("Ube Pastillas \n     Qty: ").append(ubeQuantity).append(" - subTotal: ₱").append(String.format("%.2f", ubeQuantity * ubePrice)).append("\n");
        }
       
        if (mangoQuantity > 0) {
            if (productInfo.length() > 0) {
                productInfo.append("\n"); 
            }
            productInfo.append("Mango Sago \n     Qty: ").append(mangoQuantity).append(" - subTotal: ₱").append(String.format("%.2f", mangoQuantity * mangoPrice)).append("\n");
        }

        orderArea.setText(productInfo.toString());
    }
}
