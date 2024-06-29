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
    
    //fo
    private JLabel Orange, lblOrange, pesosignOrange, priceOrange, 
            Kungpao, lblKungpao, pesosignKungpao, priceKungpao,
            Mushroom, lblMushroom, pesosignMushroom, priceMushroom, totalLabel;
    private JPanel squareOrange, squareKungpao, squareMushroom;
    private JTextField quantityOrangeField, quantityKungpaoField, quantityMushroomField, totalField;
    private JButton addOrange, subtractOrange,
            addKungpao, subtractKungpao,
            addMushroom, subtractMushroom, btnCheckout;
    private double orangePrice, kungpaoPrice, mushroomPrice;
    private int orangeQuantity, kungpaoQuantity, mushroomQuantity;
    
    private static final String URL = "jdbc:mysql://localhost:3306/reports"; //your_localhost
    private static final String USER = "root";
    private static final String PASSWORD = "password"; //your_password
    
    
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
        subtractOrange.setEnabled(false); // Initially disable decrease button
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
        subtractKungpao.setEnabled(false); // Initially disable decrease button
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
        subtractMushroom.setEnabled(false); // Initially disable decrease button
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
       else if (e.getSource() == addOrange) {
            orangeQuantity++;
            quantityOrangeField.setText(Integer.toString(orangeQuantity));
            updateTotal();
            subtractOrange.setEnabled(true); // Enable decrease button after adding
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

                    // Update local product quantity and UI elements after successful database update
                    orangeQuantity--;
                    quantityOrangeField.setText(Integer.toString(orangeQuantity));
                    updateTotal();
                    if (orangeQuantity == 0) {
                        subtractOrange.setEnabled(false); // Disable decrease button if quantity is 0
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
            subtractKungpao.setEnabled(true); // Enable decrease button after adding
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
                    // Database update for decreasing quantity (replace with your actual product name)
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql = "UPDATE foods SET quantity = quantity + 1, sold = sold - 1 WHERE product_name = 'Kung Pao Chicken'";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Quantity for 'Kung Pao Chicken' increased by 1");

                    // Update local product quantity and UI elements after successful database update
                    kungpaoQuantity--;
                    quantityKungpaoField.setText(Integer.toString(kungpaoQuantity));
                    updateTotal();
                    if (kungpaoQuantity == 0) {
                        subtractKungpao.setEnabled(false); // Disable decrease button if quantity is 0
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
            subtractMushroom.setEnabled(true); // Enable decrease button after adding
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
                    // Database update for decreasing quantity (replace with your actual product name)
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql = "UPDATE foods SET quantity = quantity + 1, sold = sold - 1 WHERE product_name = 'Orange Chicken'";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Quantity for 'Orange Chicken' increased by 1");

                    // Update local product quantity and UI elements after successful database update
                    mushroomQuantity--;
                    quantityMushroomField.setText(Integer.toString(mushroomQuantity));
                    updateTotal();
                    if (mushroomQuantity == 0) {
                        subtractMushroom.setEnabled(false); // Disable decrease button if quantity is 0
                    }
                    updateProductInfoField();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
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

            review += "Total: $" + totalField.getText();
            JOptionPane.showMessageDialog(this, review, "Order Details", JOptionPane.INFORMATION_MESSAGE);
        
            orangeQuantity = 0;
            kungpaoQuantity = 0;
            mushroomQuantity = 0;
            quantityOrangeField.setText("0");
            quantityKungpaoField.setText("0");
            quantityMushroomField.setText("0");
            updateTotal();
            subtractOrange.setEnabled(false); // Reset decrease buttons
            subtractKungpao.setEnabled(false); // Reset decrease buttons
            subtractMushroom.setEnabled(false); // Reset decrease buttons
            updateProductInfoField();
            
        } 
    }
    
    private void updateTotal() {
        double total = orangeQuantity * orangePrice + kungpaoQuantity * kungpaoPrice + mushroomQuantity * mushroomPrice;
        totalField.setText(String.format("%.2f", total));
        // Update totalArea with the grand total
        totalArea.setText(String.format(" Total: ₱%.2f", total));
    }
    
    private void updateProductInfoField() {
        StringBuilder productInfo = new StringBuilder();

        // Add details for Product 1 (if selected)
        if (orangeQuantity > 0) {
            productInfo.append("Orange Chicken \n     Qty: ").append(orangeQuantity).append(" - subTotal: ₱").append(String.format("%.2f", orangeQuantity * orangePrice)).append("\n");
        }

        // Add details for Product 2 (if selected)
        if (kungpaoQuantity > 0) {
            if (productInfo.length() > 0) {
                productInfo.append("\n"); // Add a newline if Product 1 is already displayed
            }
            productInfo.append("Kung Pao Chicken \n     Qty: ").append(kungpaoQuantity).append(" - subTotal: ₱").append(String.format("%.2f", kungpaoQuantity * kungpaoPrice)).append("\n");
        }

        // Add details for Product 3 (Mushroom Chicken)
        if (mushroomQuantity > 0) {
            if (productInfo.length() > 0) {
                productInfo.append("\n"); // Add a newline if previous products are displayed
            }
            productInfo.append("Mushroom Chicken \n     Qty: ").append(mushroomQuantity).append(" - subTotal: ₱").append(String.format("%.2f", mushroomQuantity * mushroomPrice)).append("\n");
        }

        orderArea.setText(productInfo.toString());
    }
}
