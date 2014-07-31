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
	
	public static int pmx = 0;
	public static int pmy = 0;
	
	public static int mpxPrev = 0;
	public static int mpyPrev = 0;
	
	public static boolean stroking;
	
	public static void updateMp()
	{
		pmx = MainPanel.mouseX;
		pmy = MainPanel.mouseY;
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
				gX = pmx;
				gY = pmy;
				
				totalDistance = 0;
				
				stroking = true;
			}
			else
			{
				if(pmx != mpxPrev || pmy != mpyPrev)
				{
					Vector2d motion = new Vector2d(pmx - mpxPrev, pmy - mpyPrev);
					
					double magnitude = motion.magnitude();
					
					motion.divideLocal(magnitude);
					
					if(totalDistance + magnitude >= brush.maxInterval())
					{
						int reps = (int) magnitude;
						
						Vector2d pos = new Vector2d(mpxPrev, mpyPrev);
						for(int i = 0; i < reps; ++ i)
						{
							totalDistance += 1;
							
							pos.addLocal(motion);
							
							if(totalDistance >= brush.maxInterval())
							{
								gX = (int) pos.a;
								gY = (int) pos.b;
								apply(gX, gY);
								totalDistance -= brush.maxInterval();
							}
						}
					}
					else
					{
						totalDistance += magnitude;
					}
					
					/*
					totalDistance += magnitude;
					
					motion.divideLocal(magnitude);
					
					if(totalDistance > brush.maxInterval())
					{
						Vector2d direction = new Vector2d(pmx - gX, pmy - gY);
						
						direction.normalizeLocal();
						
						while(totalDistance > brush.maxInterval())
						{
							gX += direction.a * brush.maxInterval();
							gY += direction.b * brush.maxInterval();
							
							apply(gX, gY);
							
							totalDistance -= brush.maxInterval();
						}
						
						
					}*/
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
		
		mpxPrev = pmx;
		mpyPrev = pmy;
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
