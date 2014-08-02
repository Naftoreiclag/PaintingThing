package me.naftoreiclag.paintingthing;

import java.awt.Color;
import java.awt.GradientPaint;

public class UI
{
	public static Color normalColor = new Color(0x758D92);
	public static Color lightColor = normalColor.brighter();
	public static Color lighterColor = lightColor.brighter();
	public static Color lightestColor = lighterColor.brighter();
	public static Color darkColor = normalColor.darker();
	public static Color darkerColor = darkColor.darker();
	public static Color darkestColor = darkerColor.darker().darker();
	
	public static GradientPaint gradient = new GradientPaint(0, 0, lightColor, 256, 256, normalColor);
	public static GradientPaint gradient2 = new GradientPaint(0, 0, lightColor, 30, 30, normalColor);
	public static GradientPaint gradient3 = new GradientPaint(0, 0, darkerColor, 200, 200, darkColor);
	public static GradientPaint gradient4 = new GradientPaint(0, 0, lighterColor, 50, 50, lightColor);
	public static GradientPaint gradient5 = new GradientPaint(0, 0, lightColor, 100, 100, normalColor);
}
