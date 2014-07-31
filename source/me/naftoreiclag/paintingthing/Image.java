package me.naftoreiclag.paintingthing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Image
{
	public final int width;
	public final int height;
	
	protected byte[][] colorData;
	private Map<Byte, Integer> palette;
	
	private BufferedImage displayImage;
	
	public Image(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		colorData = new byte[width][height];
		for(int x = 0; x < width; ++ x)
		{
			for(int y = 0; y < height; ++ y)
			{
				colorData[x][y] = 0;
			}
		}
		
		displayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		palette = new HashMap<Byte, Integer>();
		palette.put((byte) ( 0), 0xFF7F0046);
		palette.put((byte) ( 1), 0xFF4F59CC);
		palette.put((byte) ( 2), 0xFF0D2344);
		palette.put((byte) ( 3), 0xFF194280);
		palette.put((byte) ( 4), 0xFFA3D8FF);
		palette.put((byte) ( 5), 0xFF42B0FF);
		palette.put((byte) ( 6), 0xFF2F484E);
		palette.put((byte) ( 7), 0xFF197C80);
		palette.put((byte) ( 8), 0xFF51A307);
		palette.put((byte) ( 9), 0xFF3D7705);
		palette.put((byte) (10), 0xFF1F3D03);
		palette.put((byte) (11), 0xFF212903);
		palette.put((byte) (12), 0xFFA3CE27);
		palette.put((byte) (13), 0xFFFFFF68);
		palette.put((byte) (14), 0xFFFFEC00);
		palette.put((byte) (15), 0xFFA39960);
		palette.put((byte) (16), 0xFF737F49);
		palette.put((byte) (17), 0xFF4D462C);
		palette.put((byte) (18), 0xFF2F2A21);
		palette.put((byte) (19), 0xFFC18E4F);
		palette.put((byte) (20), 0xFFEFBD7F);
		palette.put((byte) (21), 0xFFFF931E);
		palette.put((byte) (22), 0xFF562708);
		palette.put((byte) (23), 0xFFB75A2C);
		palette.put((byte) (24), 0xFF894321);
		palette.put((byte) (25), 0xFFFF5D1E);
		palette.put((byte) (26), 0xFFFF6D68);
		palette.put((byte) (27), 0xFFAA0000);
		palette.put((byte) (28), 0xFFF23A3A);
		palette.put((byte) (29), 0xFFFFFFFF);
		palette.put((byte) (30), 0xFFC4C4C4);
		palette.put((byte) (31), 0xFF000000);
		
		updateDisplayImage();
	}
	
	public void setColor(byte color, int x, int y)
	{
		if(x < 0 || x >= width || y < 0 || y >= height)
		{
			return;
		}
		
		colorData[x][y] = color;
		displayImage.setRGB(x, y, palette.get(color));
	}
	
	public void updateDisplayImage()
	{
		for(int x = 0; x < width; ++ x)
		{
			for(int y = 0; y < height; ++ y)
			{
				displayImage.setRGB(x, y, palette.get(colorData[x][y]));
			}
		}
	}
	
	public void paint(Graphics2D painter)
	{
		painter.drawImage(displayImage, 0, 0, null);
	}

	public void clear()
	{
		for(int x = 0; x < width; ++ x)
		{
			for(int y = 0; y < height; ++ y)
			{
				colorData[x][y] = 0;
			}
		}
		
		updateDisplayImage();
	}
}
