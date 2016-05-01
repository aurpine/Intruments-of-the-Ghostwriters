
import java.io.*;
import javax.swing.*;
import java.awt.*;


class Test {
    public static void main(String[]args) {
        JFrame j = new JFrame();
        j.setLocationRelativeTo(null);
        j.setUndecorated(true);
        j.setVisible(true);
        j.setSize(800, 600);
        JLabel p = new JLabel();
        p.setIcon(new ImageIcon("splash.gif"));
        j.add(p);
        
        j.revalidate();
        j.repaint();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) { }
        j.setVisible(false);
        j.dispose();
    }
    
        
}