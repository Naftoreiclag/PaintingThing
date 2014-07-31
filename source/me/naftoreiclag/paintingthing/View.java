package me.naftoreiclag.paintingthing;

public class View
{
	private final Image image;
	
	private final int lx;
	private final int ly;
	
	public View(Image image, int x, int y)
	{
		this.image = image;
		this.lx = x;
		this.ly = y;
	}
	
	public byte get(int x, int y)
	{
		int x2 = lx + x;
		int y2 = lx + y;
		
		if(x2 < 0 || x2 >= image.width || y2 < 0 || y >= image.height)
		{
			return 0;
		}
		
		return image.colorData[x2][y2];
	}
}
