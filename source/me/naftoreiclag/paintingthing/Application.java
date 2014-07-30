package me.naftoreiclag.paintingthing;

import java.awt.Graphics2D;

public class Application
{
	public static Image image;

	public static void update()
	{
		if(MainPanel.leftDown)
		{
			image.setColor((byte) 0x05, MainPanel.mouseX, MainPanel.mouseY);
		}
	}

	public static void paint(Graphics2D painter)
	{
		image.paint(painter);
	}

}
