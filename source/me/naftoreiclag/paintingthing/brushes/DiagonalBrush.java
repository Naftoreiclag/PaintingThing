package me.naftoreiclag.paintingthing.brushes;

import me.naftoreiclag.paintingthing.Changes;
import me.naftoreiclag.paintingthing.View;

public class DiagonalBrush extends Brush
{
	int width = 0;
	int length = 3;
	
	@Override
	public void apply(View view, Changes changes)
	{
		byte color = 0x08;
		for(int i = -width; i <= width; ++ i)
		{
			for(int j = -length; j <= length; ++ j)
			{
				changes.addPixel(j - i, j + i, color);
				changes.addPixel(j - i, j + i - 1, color);
			}
		}
	}

	@Override
	public double maxInterval()
	{
		return width;
	}
	
}
