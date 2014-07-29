package me.naftoreiclag.paintingthing;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel
{
	public static class MainFrame extends JFrame
	{
		private MainFrame() throws Exception
		{
			super("PaintingThing");
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(500, 500);
			this.setLocationRelativeTo(null);

			this.add(new MainPanel());
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		MainFrame main = new MainFrame();
		main.setVisible(true);
	}
}
