
package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author CJ
 */
public class MainMenu extends JFrame implements ActionListener{
    
    
     private JPanel whiteSquare, grayRectangle, divider;
     private JLabel txtWelcome,txtMessage,txtMessage2, BackGroundImage, Logo;
     private JButton btnPOS, btnInventory, btnReports, btnWasteTracking;

    MainMenu(){
        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        
        //---------text----------------
        
        txtWelcome = new JLabel("Welcome!");
        txtWelcome.setFont(new Font("Arimo",Font.BOLD, 90));
        txtWelcome.setForeground(Color.white);
        txtWelcome.setBounds(300, 200,600 , 90);
        add(txtWelcome);
        
        
        txtMessage = new JLabel("Let me help you manage");
        txtMessage.setFont(new Font("Arimo",Font.BOLD, 30));
        txtMessage.setForeground(Color.white);
        txtMessage.setBounds(330, 295,600 , 35);
        add(txtMessage);
        
        txtMessage2 = new JLabel("the restaurant");
        txtMessage2.setFont(new Font("Arimo",Font.BOLD, 30));
        txtMessage2.setForeground(Color.white);
        txtMessage2.setBounds(390, 330,600 , 35);
        add(txtMessage2);
        
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
        ImageIcon backgroundPic = new ImageIcon("Images/Background1.jpg");
        Image bg = backgroundPic.getImage().getScaledInstance(900,620, Image.SCALE_SMOOTH);
        BackGroundImage.setIcon(new ImageIcon(bg));
        BackGroundImage.setBounds(0, 0, 900,620);
        add(BackGroundImage);
        setVisible(true);
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
