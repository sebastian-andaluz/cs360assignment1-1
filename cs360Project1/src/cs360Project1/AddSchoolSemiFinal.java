package cs360Project1;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AddSchoolSemiFinal extends JFrame{
	public AddSchoolSemiFinal() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add...");
		panel.add(btnAdd);
		
		JButton btnNewButton = new JButton("Remove");
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JList list = new JList();
		panel_1.add(list);
	}
	

}
