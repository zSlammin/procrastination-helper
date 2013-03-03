package textEditor;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PopUp extends JFrame {
	//Made usable for many events
	//consider extending- image popups vs. text popups vs. image + test
	public PopUp(String title, String contents, String buttonText){
		//@TODO- decide on preferred size for popups
		//set Title, content, and buttonText
		this.setTitle(title);
		JLabel label = new JLabel();
		label.setText(contents);
		JButton button = new JButton(buttonText);
		button.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e)
	            {  //Execute when button is pressed
	                disposeWindow();
	            }
	        }); 
		//add everything
		this.add(label, BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	private void disposeWindow(){
		this.dispose();
	}
}
