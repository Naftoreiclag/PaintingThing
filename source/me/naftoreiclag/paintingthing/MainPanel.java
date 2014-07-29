package me.naftoreiclag.paintingthing;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements MouseMotionListener, KeyListener, MouseListener, MouseWheelListener, ComponentListener
{
	public static void main(String[] args) { (new MainFrame()).setVisible(true); }
	
	public static int width = 500;
	public static int height = 500;
	
	public static class MainFrame extends JFrame
	{
		private MainFrame()
		{
			super("PaintingThing");
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(width, height);
			this.setLocationRelativeTo(null);

			this.add(new MainPanel());
		}
	}
	
	public MainPanel()
	{
		this.setFocusable(true);
		
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		this.addComponentListener(this);
	}

	@Override
	public void mouseMoved(MouseEvent event) {}

	@Override
	public void mouseDragged(MouseEvent event) {}

	@Override
	public void keyTyped(KeyEvent event) {}

	@Override
	public void keyReleased(KeyEvent event) {}

	@Override
	public void keyPressed(KeyEvent event) {}

	@Override
	public void mouseReleased(MouseEvent event) {}

	@Override
	public void mousePressed(MouseEvent event) {}

	@Override
	public void mouseExited(MouseEvent event) {}

	@Override
	public void mouseEntered(MouseEvent event) {}

	@Override
	public void mouseClicked(MouseEvent event) {}

	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {}

	@Override
	public void componentShown(ComponentEvent event) {}

	@Override
	public void componentResized(ComponentEvent event)
	{
		Dimension size = event.getComponent().getSize();
		
		width = size.width;
		height = size.height;
	}

	@Override
	public void componentMoved(ComponentEvent event) {}

	@Override
	public void componentHidden(ComponentEvent event){}
}
