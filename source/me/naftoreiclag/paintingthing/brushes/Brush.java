package me.naftoreiclag.paintingthing.brushes;

import me.naftoreiclag.paintingthing.Changes;
import me.naftoreiclag.paintingthing.View;

public abstract class Brush
{
	public abstract void apply(View view, Changes changes);
	
	// The largest interval between applications
	public abstract double maxInterval();
	
	// The smallest interval between applications
	public double minInterval() { return 0; };
}
