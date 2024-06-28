package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import BusinessLogic.*;

public class Login extends JFrame implements ActionListener {

    private JLabel hdrTxt, idTxt,passTxt, promptText,BackGroundImage, Logo;
    private JTextField empIDTxtFld;
    private JPasswordField passTxtFld;
    private JButton signinButton;
    private JPanel whiteSquare, yellowSquare;
    UserService userService = new UserService();
    Login(){
        
        
        setSize(400,470);//535
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        hdrTxt = new JLabel("Management System", SwingConstants.CENTER); //Header Text
        hdrTxt.setBounds(0, 127 , 400, 25); 
        hdrTxt.setForeground(Color.white);
        hdrTxt.setFont(new Font("impact", Font.PLAIN, 25));
        add(hdrTxt);
        
        idTxt = new JLabel("Employee ID:");//ID Text
        idTxt.setBounds(20, 220 , 300, 25); 
        idTxt.setForeground(new Color(199, 49, 49));
        idTxt.setFont(new Font("arimo", Font.BOLD, 25));
        add(idTxt);

        passTxt = new JLabel("Password:");//Password Text
        passTxt.setBounds(20, 290 , 300, 25); 
        passTxt.setForeground(new Color(199, 49, 49));
        passTxt.setFont(new Font("arimo", Font.BOLD, 25));
        add(passTxt);
    
        promptText = new JLabel();//Prompt Text
        promptText.setBounds(150,340 , 400, 20); 
        promptText.setForeground(Color.black);
        promptText.setFont(new Font("ARIMOS", Font.PLAIN, 20));
        add(promptText);
        
        //-------------------TextField------------
        
        empIDTxtFld = new JTextField("Enter_Employee_ID_Here"); //Employee TextField
        empIDTxtFld.setBounds(210, 220, 160, 27);
        empIDTxtFld.setBackground(new Color(167,54,49));
        empIDTxtFld.setBorder(null);
        empIDTxtFld.setForeground(Color.white);
        add(empIDTxtFld);
    
        passTxtFld = new JPasswordField();//Password TextField
        passTxtFld.setBounds(210, 290, 160, 27);
        passTxtFld.setBackground(new Color(167,54,49));
        passTxtFld.setBorder(null);
        passTxtFld.setForeground(Color.white);
        add(passTxtFld);
        
        //---------Buttons-------
        signinButton = new JButton("Login");//Sigin Button
        signinButton.setForeground(Color.white);
        signinButton.setBounds(140,370,100,30);
        signinButton.setBackground(new Color(167,54,49));
        signinButton.setBorder(null);
        signinButton.addActionListener(this);
        signinButton.setFont(new Font("impact", Font.PLAIN, 25));
        add(signinButton);
        
        
        //----------Panels---------
        
        Logo = new JLabel(); //Background
        ImageIcon logoPic = new ImageIcon("Images/Logo.png");
        Image scaledImage = logoPic.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        Logo.setIcon(logoPic);
        Logo.setBounds(130, 0, 120, 120);
        Logo.setIcon(new ImageIcon(scaledImage));
        add(Logo);
        
        yellowSquare = new JPanel(); //White square panel
        yellowSquare.setBounds(0, 165 , 500, 10);
        yellowSquare.setBackground(new Color(253,190,2));
        add(yellowSquare);
        
        whiteSquare = new JPanel(); //White square panel
        whiteSquare.setBounds(0,175  , 500, 350);
        whiteSquare.setBackground(Color.WHITE);
        add(whiteSquare);
        
        
        BackGroundImage = new JLabel(); //Background
        ImageIcon backgroundPic = new ImageIcon("Images/Background1.jpg");
        BackGroundImage.setIcon(backgroundPic);
        BackGroundImage.setBounds(-190,-50,750,500);
        add(BackGroundImage);
        
        
        
        
        
        setVisible(true);  
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signinButton) {
            String accountNumber = empIDTxtFld.getText();
            String Password = new String(passTxtFld.getPassword());
            
            if(userService.verifyUser(accountNumber, Password)){
                new MainMenu();
            }else{
                promptText.setText("INVALID");
            }
            
            
            
        }
    
    }
    
}
