package me.naftoreiclag.paintingthing;

import java.awt.Color;
import java.awt.Graphics2D;

import me.naftoreiclag.paintingthing.brushes.Brush;
import me.naftoreiclag.paintingthing.brushes.SquareBrush;
import me.naftoreiclag.paintingthing.util.Vector2d;

public class Application
{
	public static Image image;
	
	public static Brush brush = new SquareBrush();
	
	public static int prevMouseX = 0;
	public static int prevMouseY = 0;
	
	public static int velX = 0;
	public static int velY = 0;
	
	public static int mpx = 0;
	public static int mpy = 0;
	
	public static Stroke stroke = null;
	
	public static void updateMp()
	{
		mpx = MainPanel.mouseX;
		mpy = MainPanel.mouseY;
	}
	
	public static void update()
	{
		updateMp();
		
		if(MainPanel.rightDown)
		{
			image.clear();
			
			MainPanel.rightDown = false;
		}
		
		if(MainPanel.leftDown)
		{
			if(stroke == null)
			{
				stroke = new Stroke(mpx, mpy);
			}
			stroke.addMovement(mpx, mpy);
		}
		
		if(stroke != null && !MainPanel.leftDown)
		{
			stroke.normalize();
			
			stroke = null;
		}
	}
	
	private static double mouseDist()
	{
		return Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
	}
	
	private static void apply(int x, int y)
	{
		Changes changes = new Changes();
		brush.apply(new View(image, x, y), changes);
		changes.apply(image, x, y);
	}

	public static void paint(Graphics2D painter)
	{
		image.paint(painter);
		
		painter.setColor(Color.LIGHT_GRAY);
		
		if(stroke != null)
		{
			int x = stroke.x;
			int y = stroke.y;
			for(Vector2d move : stroke.movements)
			{
				
				painter.drawLine(x, y, (int) (x + move.a), (int) (y + move.b));
				
				x += move.a;
				y += move.b;
			}
		}
	}

}
