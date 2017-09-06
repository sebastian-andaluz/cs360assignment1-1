package cs360Project1;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Opener extends JFrame{
	public Opener() {
		setTitle("Option");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(135, 11, 165, 39);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Modify Classes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(162, 170, 108, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add or Remove Classes");
		btnNewButton_1.setBounds(135, 99, 155, 23);
		getContentPane().add(btnNewButton_1);
	}
}
