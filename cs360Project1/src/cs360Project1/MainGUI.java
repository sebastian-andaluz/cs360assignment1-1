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

public class MainGUI extends JFrame{
	
	private JPanel sideOptions;
	
	private JPanel topOptions;
	
	private JPanel schoolList;
	
	private JButton loadButton;
	
	private JButton saveButton;
	
	private JButton exportButton;
	
	private JButton editButton;
	
	private JButton nameBar;
	
	private JButton addressBar;
	
	private JButton coordsBar;
	
	private JButton sizeBar;
	
	private JButton sectionalBar;
	
	private JButton regionalBar;
	
	private JButton semiBar;
	
	private JButton exitButton;
	
	private JButton mapButton;
	
	private JButton commandButton;
	private JLabel textPane;
	private JLabel lblNewLabel;
	

	// Constructor
		public MainGUI(){
			
			setTitle("Indiana Sports Manager");
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setSize(1280, 720);
			
			getContentPane().setLayout(new BorderLayout());
			
						
			buildPanel();
			
			
					
			setVisible(true);
			
		}
		// Building the panels
		public void buildPanel(){
			
			sideOptions = new JPanel();
			topOptions = new JPanel();
			schoolList = new JPanel();
			
			loadButton = new JButton("      Load      ");
			loadButton.setToolTipText("Loads a New School File");
			
			saveButton = new JButton("      Save      ");
			saveButton.setToolTipText("Saves Changes");
			
			exportButton = new JButton("    Export     ");
			exportButton.setToolTipText("Export Changes to New File");
			
			editButton = new JButton("      Edit        ");
			editButton.setToolTipText("Edit Selected School's Info");
			
			mapButton = new JButton("      Map       ");
			mapButton.setToolTipText("View Map");
			
			commandButton = new JButton("Commands");
			commandButton.setToolTipText("Enter Command");
			
			exitButton = new JButton("      Exit        ");
			exitButton.setToolTipText("Closes The Program");
			
			nameBar = new JButton("Name");
			nameBar.setHorizontalAlignment(SwingConstants.LEFT);
			nameBar.setVerticalAlignment(SwingConstants.TOP);
			coordsBar = new JButton("Location");
			sectionalBar = new JButton("Sectional");
			regionalBar = new JButton("Regional");
			semiBar = new JButton("Semi-Final");
			topOptions.setLayout(new BoxLayout(topOptions, BoxLayout.X_AXIS));
			
			topOptions.add(nameBar);
			addressBar = new JButton("Address");
			topOptions.add(addressBar);
			topOptions.add(coordsBar);
			topOptions.add(sectionalBar);
			topOptions.add(regionalBar);
			topOptions.add(semiBar);
			sideOptions.setLayout(new BoxLayout(sideOptions, BoxLayout.Y_AXIS));
			schoolList.setLayout(new GridLayout(391, 391, 391, 391));
			
			sideOptions.add(loadButton);
			sideOptions.add(saveButton);
			sideOptions.add(exportButton);
			sideOptions.add(editButton);
			sideOptions.add(mapButton);
			sideOptions.add(commandButton);
			sideOptions.add(exitButton);
			for(int i = 0; i < 500; i++){
				for(int j = 0; j < 8; j++){
			
					textPane = new JLabel();
					schoolList.add(textPane);
				}
			}
			
			
			getContentPane().add(topOptions, BorderLayout.NORTH);
			getContentPane().add(sideOptions, BorderLayout.EAST);
			getContentPane().add(schoolList, BorderLayout.CENTER);
			
			
			
			
}
}

