package com.ting.app;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.ting.scene.Pointer;

public class AWTPointer extends Pointer implements MouseListener, MouseMotionListener {
		
	public AWTPointer(Component component) {
		super();
		component.addMouseListener(this);
		component.addMouseMotionListener(this);
	}

	// Interface MouseListener
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent me) {
		down(me.getX(), me.getY());
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		up();
	}

	// Interface MouseMotionListener

	@Override
	public void mouseDragged(MouseEvent me) {
		move(me.getX(), me.getY());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

}
