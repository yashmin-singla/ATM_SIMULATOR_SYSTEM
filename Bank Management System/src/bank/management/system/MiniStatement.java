
package bank.management.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class MiniStatement extends JFrame {

    JButton Rs100,Rs500,Rs1000,Rs2000,Rs5000,Rs10000,exit;
    String pinnumber;
    MiniStatement(String pinnumber) {
        setTitle("Mini Statement");
        this.pinnumber = pinnumber;
        setLayout(null);
        
        JLabel text = new JLabel();
        add(text);
        
        JLabel bank = new JLabel("Indian Bank");
        bank.setBounds(150,20,100,20);
        bank.setFont(new Font("System", Font.BOLD,16));
        add(bank);
        
        JLabel card = new JLabel(); 
        card.setBounds(20,80,300,20);
        add(card);
        
        JLabel balance = new JLabel(); 
        balance.setBounds(20,400,300,20);
        add(balance);
        
         try {
             Conn c = new Conn();
             ResultSet rs = c.s.executeQuery("select * from login where pin = '"+pinnumber+"'");
             while(rs.next()) {
                 card.setText("Card Number: " + rs.getString("cardnymber").substring(0,4) + "XXXXXXXX" + rs.getString("cardnymber").substring(12));
             }
             
             
             
         } catch(Exception e) {
             System.out.println(e);
         }
         
         try {
             Conn c = new Conn();
             int bal =0;
             ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
             while(rs.next()) {
                 text.setText(text.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                 if(rs.getString("type").equals("Deposit")) {
                       bal += Integer.parseInt(rs.getString("amount"));
                       
                   } else {
                       bal -= Integer.parseInt(rs.getString("amount"));
                   }
             }
             balance.setText("Your current account balance is Rs " + bal);
             
             
         } catch(Exception e) {
             System.out.println(e);
         }
         
         text.setBounds(20,140,400,20);
               
    }
    
    public static void main(String[] args) {
        new MiniStatement("");
    }
}
