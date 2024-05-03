
package javaapplication;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static javaapplication.Client.formatLabel;

public class Client implements ActionListener   {
    
    JTextField text;
    static JPanel a1;
    static Box vertical= Box.createVerticalBox();
    static JFrame f1= new JFrame();
    
    static DataOutputStream dout;
    
    
    Client() {
        
        f1.setLayout(null);
        
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f1.add(p1);
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel back= new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);
        
        
        
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
        }
    } );
        
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5=i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6= new ImageIcon(i5);
        JLabel profile= new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);
        
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9= new ImageIcon(i8);
        JLabel video= new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);
        
        
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11=i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i12= new ImageIcon(i11);
        JLabel phone= new JLabel(i12);
        phone.setBounds(360, 20, 35, 30);
        p1.add(phone);
        
        
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14=i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15= new ImageIcon(i14);
        JLabel morevert= new JLabel(i15);
        morevert.setBounds(420, 20, 10, 25);
        p1.add(morevert);
        
        
        
        JLabel name1=new JLabel("Rockey");
        name1.setBounds(110, 15, 100, 18);
        name1.setForeground(Color.WHITE);
        name1.setFont(new Font("SAN-SERIF", Font.BOLD, 14));
        p1.add(name1);
        
        
        
        JLabel status=new JLabel("Active Now");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN-SERIF", Font.BOLD, 14));
        p1.add(status);
        
        
        
        a1= new JPanel();
        a1.setBounds(5, 75, 440, 495);
        f1.add(a1);
        
        text= new JTextField();
        text.setBounds(5, 570, 360, 25);
        text.setFont(new Font("SAN-SERIF", Font.PLAIN,14));
        f1.add(text);
        
        
        JButton send= new JButton("send");
        send.setBounds(350, 570, 94, 24);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this); 
        send.setFont(new Font("SAN-SERIF", Font.PLAIN,16));
        f1.add(send);
        
        f1.setSize(450, 600);
        f1.setLocation(800, 20);
        f1.setUndecorated(true);
        f1.getContentPane().setBackground(Color.WHITE);
        
        
        f1.setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        try {
        String out= text.getText();
        
        
        JPanel p2 = formatLabel(out);
        
        
        a1.setLayout(new BorderLayout());
        
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical, BorderLayout.PAGE_START);
        
        dout.writeUTF(out);
        text.setText("");
        
        f1.repaint();
        f1.invalidate();
        f1.validate();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    public static JPanel formatLabel(String out) {
     JPanel panel= new JPanel();
     panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
     
     JLabel output= new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
     output.setFont(new Font("Tohoma", Font.PLAIN, 16));
     output.setBackground(new Color(37, 211, 102));
     output.setOpaque(true);
     output.setBorder(new EmptyBorder(15, 15, 15, 50));
     
     panel.add(output);
     
     Calendar cal = Calendar.getInstance();
     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
     
     JLabel time = new JLabel();
     time.setText(sdf.format(cal.getTime()));
     
     panel.add(time);
      
     
     return panel;
    
   
        
    }
    
    public static void main(String args[]) {
        new Client();
        
        try {
            Socket s= new Socket("127.0.0.1", 6001);
            DataInputStream din= new DataInputStream(s.getInputStream());
              dout = new DataOutputStream(s.getOutputStream());
              
              while(true) {
                  a1.setLayout(new BorderLayout());
              String msg =din.readUTF();
              JPanel panel= formatLabel(msg);
              
              JPanel left= new JPanel(new BorderLayout());
              left.add(panel, BorderLayout.LINE_START);
              vertical.add(left);
              
              vertical.add(Box.createVerticalStrut(15));
              a1.add(vertical, BorderLayout.PAGE_START);
              
              f1.validate();
          }
            
        } catch(Exception e) {
            e.printStackTrace();
    }
    
}
}
     
        
    
    
    
        
        
        
        
    
    
    

