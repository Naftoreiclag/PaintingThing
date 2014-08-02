package me.naftoreiclag.paintingthing;

import java.util.ArrayList;
import java.util.List;

import me.naftoreiclag.paintingthing.util.Vector2d;

public class Changes
{
	List<ColorChange> changes = new ArrayList<ColorChange>();
	
	public void addPixel(int x, int y, byte color)
	{
		// Make a new change
		ColorChange newChange = new ColorChange(x, y, color);
		
		// If it overwrites any, then remove those
		for(ColorChange change : changes)
		{
			if(newChange.overwrites(change))
			{
				changes.remove(change);
				break;
			}
		}
		
		// Add as normal
		changes.add(newChange);
		
	}

	public Changes addCircle(int x, int y, byte color, int rad)
	{
		int tx = rad;
		int ty = 0;
		
		int radiusError = 1 - tx;

		while(tx >= ty)
		{
			addPixel(tx + x, ty + y, color);
			addPixel(ty + x, tx + y, color);
			addPixel(-tx + x, ty + y, color);
			addPixel(-ty + x, tx + y, color);
			addPixel(-tx + x, -ty + y, color);
			addPixel(-ty + x, -tx + y, color);
			addPixel(tx + x, -ty + y, color);
			addPixel(ty + x, -tx + y, color);
			
			ty ++;
			
			if(radiusError < 0)
			{
				radiusError += (2 * ty) + 1;
			}
			else
			{
				tx --;
				radiusError += 2 * ((ty - tx) + 1);
			}
		}
		
		return this;
	}
	
	public Changes addLineDirty(int x1, int y1, int x2, int y2, byte color)
	{
		Vector2d direction = new Vector2d(x2 - x1, y2 - y1);
		
		double mag = direction.magnitude();
		
		direction.divideLocal(mag);
		
		int reps = (int) mag;
		
		double x = 0;
		double y = 0;
		for(int i = 0; i <= reps; ++ i)
		{
			this.addPixel((int) x + x1, (int) y + y1, color);
			
			x += direction.a;
			y += direction.b;
		}
		
		return this;
	}

	public Changes addCircleFilled(int x, int y, byte color, int rad)
	{
		addCircle(x, y, color, rad);
		
		int radsq = rad * rad;
		
		for(int tx = -rad; tx <= rad; ++ tx)
		{
			int txsq = tx * tx;
			for(int ty = -rad; ty <= rad; ++ ty)
			{
				if(txsq + (ty * ty) < radsq)
				{
					addPixel(tx + x, ty + y, color);
				}
			}
		}
		
		return this;
	}
	
	protected void apply(Image image, int x, int y)
	{
		for(ColorChange change : changes)
		{
			image.setColor(change.color, x + change.x, y + change.y);
		}
	}
	
	private static class ColorChange
	{
		private final int x, y;
		private final byte color;
		
		private ColorChange(int x, int y, byte color)
		{
			this.x = x;
			this.y = y;
			
			this.color = color;
		}
		
		private boolean overwrites(ColorChange other)
		{
			return other.x == x && other.y == y;
		}
	}
}
