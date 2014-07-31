package me.naftoreiclag.paintingthing;

import java.util.ArrayList;
import java.util.List;

public class Changes
{
	List<Change> changes = new ArrayList<Change>();
	
	
	public Changes()
	{
		
	}
	
	public void add(int x, int y, byte color)
	{
		// Make a new change
		Change newChange = new Change(x, y, color);
		
		// If it overwrites any, then remove those
		for(Change change : changes)
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
	
	public void apply(Image image, int x, int y)
	{
		for(Change change : changes)
		{
			image.setColor(change.color, x + change.x, y + change.y);
		}
	}
	
	private static class Change
	{
		private final int x, y;
		private final byte color;
		
		private Change(int x, int y, byte color)
		{
			this.x = x;
			this.y = y;
			
			this.color = color;
		}
		
		private boolean overwrites(Change other)
		{
			return other.x == x && other.y == y;
		}
	}
}
