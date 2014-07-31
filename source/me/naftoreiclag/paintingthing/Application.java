package me.naftoreiclag.paintingthing;

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

	public static int mouseX = 0;
	public static int mouseY = 0;
	
	public static Stroke stroke;
	
	public static void update()
	{
		mouseX = MainPanel.mouseX;
		mouseY = MainPanel.mouseY;
		
		/*
		velX = MainPanel.mouseX - prevMouseX;
		velY = MainPanel.mouseY - prevMouseY;
		
		*/

		if(MainPanel.rightDown)
		{
			image.clear();
			
			MainPanel.rightDown = false;
		}
		
		if(MainPanel.leftDown)
		{
			Vector2d stroke = new Vector2d(mouseX - prevMouseX, mouseY - prevMouseY);
			
			double mag = Math.sqrt(stroke.magnitudeSquared());
			
			stroke.divideLocal(mag);
			
			int interval = (int) brush.maxInterval();
			
			int reps = (int) (mag / interval);
			
			for(int i = 0; i < reps; ++ i)
			{
				Vector2d pos = stroke.multiply(i * interval);
				
				apply((int) (prevMouseX + pos.a), (int) (prevMouseY + pos.b));
			}
			
			/*
			apply(0, 0);
			double mouseDist = mouseDist();
			if(mouseDist > brush.maxInterval())
			{
				Vector2d direction = new Vector2d(-velX, -velY);
				direction.normalizeLocal();
				direction.multiplyLocal(brush.maxInterval());
				
				int times = (int) (mouseDist / brush.maxInterval());
				
				double currX = 0;
				double currY = 0;
				
				for(int i = 0; i <= times; ++ i)
				{
					currX += direction.a;
					currY += direction.b;
					
					apply((int) currX, (int) currY);
				}
			}
			*/
		}
		
		prevMouseX = mouseX;
		prevMouseY = mouseY;
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
	}

}
