
package bank.management.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class PinChange extends JFrame implements ActionListener {

    JButton change,back;
    JPasswordField pinTextField,repinTextField;
    String pinnumber;
    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setBounds(250,280,500,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD,16));
        image.add(text);
        
        JLabel pintext = new JLabel("NEW PIN");
        pintext.setBounds(165,320,180,25);
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD,16));
        image.add(pintext);
        
        pinTextField = new JPasswordField();
        pinTextField.setFont(new Font("Raleway", Font.BOLD,25));
        pinTextField.setBounds(330,320,180,25);
        image.add(pinTextField);
        
        JLabel repintext = new JLabel("Re-Enter NEW PIN");
        repintext.setBounds(165,360,180,25);
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD,16));
        image.add(repintext);
        
        repinTextField = new JPasswordField();
        repinTextField.setFont(new Font("Raleway", Font.BOLD,25));
        repinTextField.setBounds(330,360,180,25);
        image.add(repinTextField);
        
        
        
        change = new JButton("CHANGE");
        change.setBounds(355,485,150,30);
        change.addActionListener(this);
        image.add(change);
        
        
    
        
        back = new JButton("BACK");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);
        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);

    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else if(ae.getSource() == change) {
            
            try {
            String newpin = pinTextField.getText();
            String repin = repinTextField.getText();
            
            if(!newpin.equals(repin)) {
                JOptionPane.showMessageDialog(null, "Entered PIN doesn't match ");
                return;
            }
            
            if(newpin.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter new PIN");
                return;
            }
            
            if(repin.equals("")) {
                JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                return;
            }
            
            Conn c = new Conn();
            
            String query1 = "update bank set pin = '"+repin+"' where pin = '"+pinnumber+"' ";
            String query2 = "update login set pin = '"+repin+"' where pin = '"+pinnumber+"' ";
            String query3 = "update signupthree set pin = '"+repin+"' where pin = '"+pinnumber+"' ";
            c.s.executeUpdate(query1);
            c.s.executeUpdate(query2);
            c.s.executeUpdate(query3);
            JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new Transactions(newpin).setVisible(true);
                
            } catch(Exception e) {
                System.out.println(e);
            }
        } 
    }
    
    public static void main(String[] args) {
        new PinChange("");
    }
}
