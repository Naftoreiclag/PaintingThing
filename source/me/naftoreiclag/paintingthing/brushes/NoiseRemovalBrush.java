package me.naftoreiclag.paintingthing.brushes;

import java.util.HashMap;
import java.util.Map;

import me.naftoreiclag.paintingthing.Changes;
import me.naftoreiclag.paintingthing.View;

public class NoiseRemovalBrush extends Brush
{
	int rad = 6;
	int rad2 = 1;
	@Override
	public void apply(View view, Changes changes)
	{
		byte[] colors = new byte[(((rad2 * 2) + 1) * ((rad2 * 2) + 1)) - 1];
		
		for(int x = -rad; x <= rad; ++ x)
		{
			for(int y = -rad; y <= rad; ++ y)
			{
				int index = 0;
				
				for(int x2 = -rad2; x2 <= rad2; ++ x2)
				{
					for(int y2 = -rad2; y2 <= rad2; ++ y2)
					{
						if(x2 == 0 && y2 == 0)
						{
							continue;
						}
						
						colors[index ++] = view.get(x + x2, y + y2);
					}
				}
				
				changes.addPixel(x, y, getMostCommonColor(colors));
			}
		}
	}

	public static byte getMostCommonColor(byte[] pixels)
	{
		// Byte-Integer mapping of the byte and quantities
		Map<Byte, Integer> quantities = new HashMap<Byte, Integer>();
		
		for(byte color : pixels)
		{
			Integer foo = quantities.get(new Byte(color));
			quantities.put(color, (foo == null ? 1 : foo + 1));
		}

		int maxQuantity = 0;

		byte mostCommonColor = 0;
		for (Map.Entry<Byte, Integer> entry : quantities.entrySet())
		{
			int quantity = entry.getValue();
			if(quantity > maxQuantity)
			{
				maxQuantity = quantity;
				mostCommonColor = entry.getKey();
			}
		}

		return mostCommonColor;
	}
	
	@Override
	public double maxInterval()
	{
		return rad;
	}
}
