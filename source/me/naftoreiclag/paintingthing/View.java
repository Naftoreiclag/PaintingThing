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
		return image.getColor(lx + x, ly + y);
	}
}
