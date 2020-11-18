package Calc;

import javax.swing.JFrame;

public class mainn 
{
	public static void main(String[] args) 
	{
		//JFrame.setDefaultLookAndFeelDecorated(true);
		func f = new func();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(536,605);
		f.setResizable(false);
		f.setVisible(true);
	}
}
