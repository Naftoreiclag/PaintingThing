package me.naftoreiclag.paintingthing;

import java.util.ArrayList;
import java.util.List;

import me.naftoreiclag.paintingthing.util.Vector2d;

public class Stroke
{
	public int x, y;
	
	public int accX, accY;
	
	public List<Vector2d> movements = new ArrayList<Vector2d>();
	
	public Stroke(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		this.accX = this.x;
		this.accY = this.y;
	}
	
	public void addMovement(int x, int y)
	{
		int dx = x - accX;
		int dy = y - accY;
		
		if(dx == 0 && dy == 0)
		{
			return;
		}
		
		movements.add(new Vector2d(dx, dy));
		accX = x;
		accY = y;
	}
	
	public List<Vector2d> normalized = new ArrayList<Vector2d>();

	public void normalize()
	{
		normalized.add(new Vector2d(0, 0));
		
		for(Vector2d move : movements)
		{
			
		}
	}
	
	/*
	public void addRelativeMovement(int deltaX, int deltaY)
	{
		movements.add(new Vector2d(deltaX, deltaY));
		accX += deltaX;
		accY += deltaY;
	}
	*/
}
