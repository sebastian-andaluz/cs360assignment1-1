package cs360Project1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ClassNumberBox extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public ClassNumberBox() {
		setTitle("Class Editor");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNumberOfClasses = new JLabel("Number Of Classes:");
		panel.add(lblNumberOfClasses);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Apply");
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Save");
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Minimum");
		lblNewLabel.setBounds(57, 53, 46, 14);
		panel_2.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(188, 50, 86, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Maximum");
		lblNewLabel_1.setBounds(57, 100, 46, 14);
		panel_2.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 97, 86, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCommit = new JButton("Apply");
		btnCommit.setBounds(185, 143, 89, 23);
		panel_2.add(btnCommit);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.WEST);
		
		JLabel lblSelectClass = new JLabel("Select Class");
		panel_3.add(lblSelectClass);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Select Class");
		panel_3.add(comboBox);
	}
}
