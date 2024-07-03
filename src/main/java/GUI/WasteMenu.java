package GUI;

import Models.Food;
import BusinessLogic.FoodService;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WasteMenu extends JFrame implements ActionListener {

    private JPanel whiteSquare, grayRectangle, divider, whiteRectangle, whiteRectangle2;
    private JLabel txtMessage, txtTopWaste, txtTopTitle, txtLowestWaste, BackGroundImage, Logo, txtWaste;
    private JButton btnPOS, btnReports, btnnventory, btnAddWaste, btnAdd, btnReturn;
    private JTable foodTable;
    private JTextField txtFldwasteCount;
    private JComboBox foodComboBox;
    private DefaultTableModel table;
    FoodService foodService = new FoodService();

    WasteMenu() {
        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Waste Menu");
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        //-------View Table panel-----------------
        whiteRectangle = new JPanel();
        whiteRectangle.setBounds(200, 100, 650, 400);
        whiteRectangle.setBackground(Color.white);
        whiteRectangle.setLayout(null);
        whiteRectangle.setVisible(true);
        add(whiteRectangle);

        txtTopTitle = new JLabel("I-eat Waste Tracking", SwingConstants.CENTER);
        txtTopTitle.setBounds(0, 20, 650, 30);
        txtTopTitle.setFont(new Font("Arimo", Font.BOLD, 27));
        txtTopTitle.setForeground(new Color(253, 190, 2));
        whiteRectangle.add(txtTopTitle);

        txtTopWaste = new JLabel();
        txtTopWaste.setBounds(20, 60, 600, 30);
        txtTopWaste.setFont(new Font("Arimo", Font.BOLD, 20));
        whiteRectangle.add(txtTopWaste);

        txtLowestWaste = new JLabel();
        txtLowestWaste.setBounds(20, 100, 600, 30);
        txtLowestWaste.setFont(new Font("Arimo", Font.BOLD, 20));
        whiteRectangle.add(txtLowestWaste);

        String[] columnNames = {"Food Name", "wasteCount"};
        table = new DefaultTableModel(columnNames, 0);
        foodTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(foodTable);
        scrollPane.setBounds(25, 150, 600, 200);
        whiteRectangle.add(scrollPane);
        foodTable.setEnabled(false);
        PutFoodInTheTable();

        btnAddWaste = new JButton("Add");
        btnAddWaste.setBackground(new Color(253, 190, 2));
        btnAddWaste.setForeground(Color.black);
        btnAddWaste.setFont(new Font("arimo", Font.PLAIN, 15));
        btnAddWaste.setBounds(530, 360, 100, 25);
        btnAddWaste.setBorderPainted(false);
        btnAddWaste.addActionListener(this);
        whiteRectangle.add(btnAddWaste);
        //-------add waste Panel----------
        whiteRectangle2 = new JPanel();
        whiteRectangle2.setBounds(200, 100, 650, 250);
        whiteRectangle2.setBackground(Color.white);
        whiteRectangle2.setLayout(null);
        whiteRectangle2.setVisible(false);
        add(whiteRectangle2);

        String[] foodItems = {"kungPaoChicken", "orangeChicken", "mushroomChicken", "Coke", "Sprite", "Ube Pastillas", "Mango Sago"};
        foodComboBox = new JComboBox<>(foodItems);
        foodComboBox.setBounds(50, 30, 250, 30);
        whiteRectangle2.add(foodComboBox);

        txtFldwasteCount = new JTextField();
        txtFldwasteCount.setBounds(350, 30, 100, 30);
        whiteRectangle2.add(txtFldwasteCount);

        btnAdd = new JButton("ADD");
        btnAdd.setBackground(new Color(199, 49, 49));
        btnAdd.setForeground(Color.black);
        btnAdd.setFont(new Font("arimo", Font.PLAIN, 15));
        btnAdd.setBounds(275, 100, 100, 30);
        btnAdd.setBorderPainted(false);
        btnAdd.addActionListener(this);
        whiteRectangle2.add(btnAdd);

        btnReturn = new JButton("View");
        btnReturn.setBackground(new Color(253, 190, 2));
        btnReturn.setForeground(Color.black);
        btnReturn.setFont(new Font("arimo", Font.PLAIN, 15));
        btnReturn.setBounds(425, 100, 100, 30);
        btnReturn.setBorderPainted(false);
        btnReturn.addActionListener(this);
        whiteRectangle2.add(btnReturn);
        //------------Buttons---------
        btnPOS = new JButton("POS"); // POS Button
        btnPOS.setBounds(30, 150, 100, 27);
        btnPOS.setBackground(new Color(0, 0, 0, 0));
        btnPOS.setOpaque(false);
        btnPOS.setBorderPainted(false);
        btnPOS.setForeground(Color.white);
        btnPOS.setFont(new Font("Impact", Font.PLAIN, 27));
        btnPOS.addActionListener(this);
        add(btnPOS);

        txtWaste = new JLabel("Waste");
        txtWaste.setBounds(0, 250, 150, 27);
        txtWaste.setBackground(new Color(0, 0, 0, 0));
        txtWaste.setOpaque(false);
        txtWaste.setForeground(Color.white);
        txtWaste.setFont(new Font("Impact", Font.PLAIN, 27));
        add(txtWaste);

        btnReports = new JButton("Reports");
        btnReports.setBounds(30, 350, 130, 27);
        btnReports.setBorderPainted(false);
        btnReports.setBackground(new Color(0, 0, 0, 0));
        btnReports.setOpaque(false);
        btnReports.setForeground(Color.white);
        btnReports.addActionListener(this);
        btnReports.setFont(new Font("Impact", Font.PLAIN, 27));
        add(btnReports);

//        btnWasteTracking = new JButton("Waste");
//        btnWasteTracking.setBounds(0, 450, 150, 27);
//        btnWasteTracking.setBackground(new Color(0, 0, 0, 0));
//        btnWasteTracking.setOpaque(false);
//        btnWasteTracking.setBorderPainted(false);
//        btnWasteTracking.setForeground(Color.white);
//        btnWasteTracking.setFont(new Font("Impact", Font.PLAIN, 27));
//        btnWasteTracking.addActionListener(this);
//        add(btnWasteTracking);

        //-------designs-----------------
        Logo = new JLabel(); // Logo
        ImageIcon logoPic = new ImageIcon("Images/Logo.png");
        Image logo = logoPic.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        Logo.setBounds(30, 5, 100, 100);
        Logo.setIcon(new ImageIcon(logo));
        add(Logo);

        divider = new JPanel(); // black divider on the top
        divider.setBounds(150, 0, 5, 600);
        divider.setBackground(Color.BLACK);
        add(divider);

        whiteSquare = new JPanel(); // white square
        whiteSquare.setBounds(0, 0, 150, 100);
        whiteSquare.setBackground(Color.white);
        add(whiteSquare);

        grayRectangle = new JPanel(); // gray square
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPOS) {
            new POSMenu();
            this.dispose();
       /*} else if (e.getSource() == btnInventory) {
 // new InventoryMenu();
            //his.dispose();
        } else if (e.getSource() == btnWasteTracking) {

        } else if (e.getSource() == btnReport) {
new POSMenu();*/
            this.dispose();
        } else if (e.getSource() == btnAddWaste) {
            whiteRectangle.setVisible(false);
            whiteRectangle2.setVisible(true);
        } else if (e.getSource() == btnReturn) {
            whiteRectangle.setVisible(true);
            whiteRectangle2.setVisible(false);
        } else if (e.getSource() == btnAdd) {
            String selectedFood = (String) foodComboBox.getSelectedItem();
            String wasteCountText = txtFldwasteCount.getText(); // Get text from TextField

            if (!wasteCountText.isEmpty()) {
                try {
                    int wasteCount = Integer.parseInt(wasteCountText); // Convert  to int

                    foodService.AddWasteCountQuantity(selectedFood, wasteCount);
                    foodService.SubtractQuantity(selectedFood, wasteCount);
                    txtFldwasteCount.setText(""); // Clear the waste count text field

                    PutFoodInTheTable(); //reload the table

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number for waste count.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a waste count.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void PutFoodInTheTable() { //put the food from our database
        List<Food> foods = foodService.getAllFoods(); // get all food from our business Logic

        table.setRowCount(0);

        for (Food food : foods) { // add all food from out table
            table.addRow(new Object[]{food.foodName, food.wasteCount});
        }
        SetTextToHighestSale(); // We add the foods po before we set the texts 
        SetTextToLowestSale(); //
    }

    private void SetTextToHighestSale() {
        if (table.getRowCount() > 0) {//First Row po
            Object foodName = table.getValueAt(0, 0); // First column po 
            Object waste = table.getValueAt(0, 1); // Second column po 

            txtTopWaste.setText("Most Waste: " + foodName + " waste " + waste);
        } else {
            txtTopWaste.setText("The store did not sale anything");
        }
    }

    private void SetTextToLowestSale() {
        int lastRowIndex = table.getRowCount() - 1;

        if (lastRowIndex >= 0) {
            Object foodName = table.getValueAt(lastRowIndex, 0);
            Object wasteCount = table.getValueAt(lastRowIndex, 1);

            txtLowestWaste.setText("Lowest Waste: " + foodName + " wasteCount " + wasteCount);
        } else {
            txtLowestWaste.setText("No sales data available.");
        }
    }
}
