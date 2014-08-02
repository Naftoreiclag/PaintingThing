package me.naftoreiclag.paintingthing;

import java.awt.Color;

public class UI
{
	public static Color normalColor = new Color(0x758D92);
	public static Color lightColor = normalColor.brighter();
	public static Color lighterColor = lightColor.brighter();
	public static Color lightestColor = lighterColor.brighter();
	public static Color darkColor = normalColor.darker();
	public static Color darkerColor = darkColor.darker();
	public static Color darkestColor = darkerColor.darker().darker();
}
