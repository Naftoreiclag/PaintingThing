package me.naftoreiclag.paintingthing.brushes;

import me.naftoreiclag.paintingthing.Changes;
import me.naftoreiclag.paintingthing.View;

public class CircleBrush extends Brush
{
	boolean filled = true;
	int rad = 10;
	
	@Override
	public void apply(View view, Changes changes)
	{
		byte color = 0x08;
		
		if(filled)
		{
			//changes.addLineDirty(0, 0, 100, 40, color);
			changes.addCircleFilled(0, 0, color, rad);
		}
		else
		{
			changes.addCircle(0, 0, color, rad);
		}
	}

	@Override
	public double maxInterval()
	{
		return 1;
	}
	
}
