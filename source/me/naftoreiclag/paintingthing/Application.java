package me.naftoreiclag.paintingthing;

import java.awt.Color;
import java.awt.Graphics2D;

import me.naftoreiclag.paintingthing.brushes.Brush;
import me.naftoreiclag.paintingthing.brushes.SquareBrush;
import me.naftoreiclag.paintingthing.brushes.SquareSprayBrush;
import me.naftoreiclag.paintingthing.util.Vector2d;

public class Application
{
	public static Image image;
	
	public static Brush brush = new SquareSprayBrush();
	
	public static int prevMouseX = 0;
	public static int prevMouseY = 0;
	
	public static int velX = 0;
	public static int velY = 0;
	
	public static int mpx = 0;
	public static int mpy = 0;
	
	public static int mpxPrev = 0;
	public static int mpyPrev = 0;
	
	public static Stroke stroke = null;
	
	public static boolean stroking;
	
	public static void updateMp()
	{
		mpx = MainPanel.mouseX;
		mpy = MainPanel.mouseY;
	}
	
	public static int gX;
	public static int gY;
	
	public static double totalDistance = 0;
	
	public static double distance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	public static void update()
	{
		updateMp();

		if(MainPanel.leftDown)
		{
			if(stroking == false)
			{
				gX = mpx;
				gY = mpy;
				
				totalDistance = 0;
				
				stroking = true;
			}
			else
			{
				if(mpx != mpxPrev || mpy != mpyPrev)
				{
					Vector2d motion = new Vector2d(mpx - mpxPrev, mpy - mpyPrev);
					
					double magnitude = motion.magnitude();
					
					totalDistance += magnitude;
					
					motion.divideLocal(magnitude);
					
					boolean sync = false;
					if(totalDistance > brush.maxInterval())
					{
						sync = true;
					}
					
					while(totalDistance > brush.maxInterval())
					{
						gX += motion.a * brush.maxInterval();
						gY += motion.b * brush.maxInterval();
						
						apply(gX, gY);
						
						totalDistance -= brush.maxInterval();
					}
					
					if(sync)
					{
						gX = mpx;
						gY = mpy;
					}
				}
			}
		}
		
		if(!MainPanel.leftDown)
		{
			stroking = false;
		}
		
		
		if(MainPanel.rightDown)
		{
			image.clear();
			
			MainPanel.rightDown = false;
		}
		
		mpxPrev = mpx;
		mpyPrev = mpy;
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
