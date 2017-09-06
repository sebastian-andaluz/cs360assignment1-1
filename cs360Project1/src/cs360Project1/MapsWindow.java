package cs360Project1;

import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MapsWindow extends JFrame{
    
    
    
    
    public MapsWindow(){
    	setTitle("Map View");
        final Browser browser = new Browser();
        
        ;
        
        JPanel toolBar = new JPanel();
        
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BrowserView browserView = new BrowserView(browser);
        frame.add(browserView, BorderLayout.CENTER);
        frame.add(toolBar, BorderLayout.NORTH);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        browser.loadURL("C:\\file.html");
    }
}