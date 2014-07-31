package me.naftoreiclag.paintingthing.brushes;

import java.util.HashMap;
import java.util.Map;

import me.naftoreiclag.paintingthing.Changes;
import me.naftoreiclag.paintingthing.View;

public class SquareBrush extends Brush
{
	int rad = 6;
	@Override
	public void apply(View view, Changes changes)
	{
		for(int x = -rad; x <= rad; ++ x)
		{
			for(int y = -rad; y <= rad; ++ y)
			{
				changes.add(x, y, (byte) 5);
			}
		}
	}
	
	@Override
	public double maxInterval()
	{
		return rad;
	}
}
