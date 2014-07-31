package me.naftoreiclag.paintingthing.brushes;

import java.util.Random;

import me.naftoreiclag.paintingthing.Changes;
import me.naftoreiclag.paintingthing.View;

public class SquareSprayBrush extends Brush
{
	public static Random r = new Random();
	
	int rad = 6;
	@Override
	public void apply(View view, Changes changes)
	{
		for(int x = -rad; x <= rad; ++ x)
		{
			for(int y = -rad; y <= rad; ++ y)
			{
				if(r.nextFloat() > 0.9f)
				{
					changes.add(x, y, (byte) 5);
				}
			}
		}
	}
	
	@Override
	public double maxInterval()
	{
		return rad;
	}
}
