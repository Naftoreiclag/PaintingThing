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
	
	public static int mousePixelX = 0;
	public static int mousePixelY = 0;
	
	public static int mpxPrev = 0;
	public static int mpyPrev = 0;
	
	public static boolean stroking;
	
	public static int lastPointX;
	public static int lastPointY;
	
	public static double acccumulatedMouseDistance = 0;
	
	public static void updateMousePixelLocaiton()
	{
		mousePixelX = MainPanel.mouseX;
		mousePixelY = MainPanel.mouseY;
	}

	public static void update()
	{
		updateMousePixelLocaiton();

		if(MainPanel.leftDown)
		{
			if(stroking == false)
			{
				lastPointX = mousePixelX;
				lastPointY = mousePixelY;
				
				acccumulatedMouseDistance = 0;
				
				stroking = true;
			}
			else
			{
				if(mousePixelX != mpxPrev || mousePixelY != mpyPrev)
				{
					Vector2d motion = new Vector2d(mousePixelX - mpxPrev, mousePixelY - mpyPrev);
					
					double magnitude = motion.magnitude();
					
					motion.divideLocal(magnitude);
					
					if(acccumulatedMouseDistance + magnitude >= brush.maxInterval())
					{
						int reps = (int) magnitude;
						
						Vector2d pos = new Vector2d(mpxPrev, mpyPrev);
						for(int i = 0; i < reps; ++ i)
						{
							acccumulatedMouseDistance += 1;
							
							pos.addLocal(motion);
							
							if(acccumulatedMouseDistance >= brush.maxInterval())
							{
								lastPointX = (int) pos.a;
								lastPointY = (int) pos.b;
								apply(lastPointX, lastPointY);
								acccumulatedMouseDistance -= brush.maxInterval();
							}
						}
					}
					else
					{
						acccumulatedMouseDistance += magnitude;
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
		
		mpxPrev = mousePixelX;
		mpyPrev = mousePixelY;
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
	}

}
