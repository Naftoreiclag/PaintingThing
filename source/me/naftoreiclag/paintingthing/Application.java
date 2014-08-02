package me.naftoreiclag.paintingthing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import me.naftoreiclag.paintingthing.brushes.*;
import me.naftoreiclag.paintingthing.util.Vector2d;

public class Application
{
	public static Image image;
	
	public static Brush brush1 = new SquareSprayBrush();
	public static Brush brush2 = new CircleBrush();
	
	public static Brush brush = brush1;
	
	public static int mousexPrev = 0;
	public static int mouseyPrev = 0;
	
	public static int mousePixelX = 0;
	public static int mousePixelY = 0;
	
	public static int mpxPrev = 0;
	public static int mpyPrev = 0;
	
	public static boolean stroking;
	
	public static int lastPointX;
	public static int lastPointY;
	
	public static double zoom = 1.0d;
	
	public static int camX = 0;
	public static int camY = 0;
	
	public static int toolDrawerExtension;
	
	public static double acccumulatedMouseDistance = 0;
	
	//public static int viewCenterX;
	//public static int viewCenterY;
	
	public static int toolScroll = 0;
	
	public static int hotbarSelection = 0;
	public static int hotbarSize = 10;
	
	public static boolean hoveringOverDrawerHandle = false;
	
	public static void updateMousePixelLocaiton()
	{
		mousePixelX = (int) ((double) camX + ((MainPanel.mouseX - (MainPanel.width / 2)) / zoom));
		mousePixelY = (int) ((double) camY + ((MainPanel.mouseY - (MainPanel.height / 2)) / zoom));
	}

	public static void update()
	{
		// Scrolling
		handleScrollChange();
		
		if(MainPanel.middleDown)
		{
			camX += (mousexPrev - MainPanel.mouseX) / zoom;
			camY += (mouseyPrev - MainPanel.mouseY) / zoom;
		}
		updateMousePixelLocaiton();
		

		if(MainPanel.leftDown)
		{
			if(stroking == false)
			{
				lastPointX = mousePixelX;
				lastPointY = mousePixelY;
				apply(lastPointX, lastPointY);
				
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
		
		mousexPrev = MainPanel.mouseX;
		mouseyPrev = MainPanel.mouseY;
	}

	private static void handleScrollChange()
	{
		if(MainPanel.mouseWithin(0, 0, 80, MainPanel.height))
		{
			toolScroll += MainPanel.scrollDistance;
		}
		else
		{
			zoom += -(MainPanel.scrollDistance / 20d);
			
			if(zoom < 0.1d)
			{
				zoom = 0.1d;
			}
		}
		
		MainPanel.scrollDistance = 0;
	}
	
	private static void apply(int x, int y)
	{
		Changes changes = new Changes();
		brush.apply(new View(image, x, y), changes);
		changes.apply(image, x, y);
	}

	private static Graphics2D painter;
	public static void paint(Graphics2D g2)
	{
		painter = g2;
		
		painter.setColor(UI.normalColor);
		painter.fillRect(0, 0, MainPanel.width, MainPanel.height);
		
		paintProject();
		
		AffineTransform at = painter.getTransform();
		painter.translate(((MainPanel.width - hotbarSize * 50) / 2), MainPanel.height - 60);
		for(int i = 0; i < hotbarSize; ++ i)
		{
			if(hotbarSelection == i)
			{
				painter.translate(0, -10);
			}
			
			if(i % 2 == 0)
			{
				painter.setPaint(UI.gradient4);
			}
			else
			{
				painter.setPaint(UI.gradient5);
			}
			painter.fillRect(0, 0, 50, 50);
			painter.setPaint(null);
			painter.setColor(UI.darkerColor);
			painter.drawRect(0, 0, 50, 50);
			painter.translate(50, 0);
			
			if(hotbarSelection == i)
			{
				painter.translate(0, 10);
			}
		}
		
		painter.setTransform(at);
		

		painter.setColor(UI.lightColor);
		painter.fillRect(0, 0, 70, MainPanel.height);
		painter.setColor(UI.darkColor);
		painter.drawRect(0, 0, 70, MainPanel.height);
		

		painter.setPaint(UI.gradient3);
		painter.fillRect(70, 0, 20, MainPanel.height);
		painter.setColor(UI.darkerColor);
		painter.drawRect(70, 0, 20, MainPanel.height);
		

		painter.setPaint(UI.gradient2);
		painter.translate(70, 0);
		painter.fillRect(0, 0, 20, 20);
		painter.translate(0, MainPanel.height - 20);
		painter.fillRect(0, 0, 20, 20);
		painter.translate(-70, -(MainPanel.height - 20));
		painter.setPaint(null);
		painter.setColor(UI.darkerColor);
		painter.drawRect(70, 0, 20, 20);
		painter.drawRect(70, MainPanel.height - 20, 20, 20);
		
		/*
		painter.setColor(UI.lighterColor);
		painter.fillRect(0, 0, toolDrawerExtension, MainPanel.height);
		
		painter.translate(toolDrawerExtension, 0);
		
		painter.setColor(UI.lightColor);
		painter.fillRect(0, 0, 20, MainPanel.height);
		painter.setColor(UI.darkColor);
		painter.drawRect(0, 0, 20, MainPanel.height);

		painter.translate(0, MainPanel.height / 2);
		painter.setColor(UI.darkColor);
		painter.fillRect(8, -18, 4, 10);
		painter.fillRect(8, -5, 4, 10);
		painter.fillRect(8, 8, 4, 10);
		painter.translate(0, MainPanel.height / -2);
		
		painter.translate(-toolDrawerExtension, 0);
		*/
	}
	
	public static void paintProject()
	{
		AffineTransform at = painter.getTransform();
		
		painter.translate(MainPanel.width / 2, MainPanel.height / 2);
		painter.translate(-camX * zoom, -camY * zoom);
		painter.scale(zoom, zoom);
		
		image.paint(painter);
		
		painter.setTransform(at);
	}

}
