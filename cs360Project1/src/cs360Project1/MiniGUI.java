package cs360Project1;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


public class MiniGUI extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public MiniGUI(){
		
		setTitle("Select Options");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(320, 240);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Save");
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(4,2));
		
		JLabel lblNewLabel_1 = new JLabel("Select Class");
		panel_5.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		panel_5.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Number Of Sectionals?");
		panel_5.add(lblNewLabel_2);
		
		textField = new JTextField();
		panel_5.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Number Of Regionals");
		panel_5.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		panel_5.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Number Of Semifinals");
		panel_5.add(lblNewLabel);
		
		textField_2 = new JTextField();
		panel_5.add(textField_2);
		textField_2.setColumns(10);
		
					
		buildPanel();
		
		
				
		setVisible(true);
		
    	
    }
	public void buildPanel(){
		
	}

}
