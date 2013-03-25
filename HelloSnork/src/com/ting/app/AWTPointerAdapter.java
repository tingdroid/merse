package com.ting.app;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import com.ting.scene.Pointer;

public class AWTPointerAdapter extends MouseInputAdapter {
	Pointer pointer;
		
	public AWTPointerAdapter(Pointer pointer) {
		super();
		this.pointer = pointer;
	}

	@Override
	public void mousePressed(MouseEvent me) {
		pointer.down(me.getX(), me.getY());
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		pointer.up();
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		pointer.move(me.getX(), me.getY());
	}

}
