package com.ting.app;

import org.lwjgl.input.Mouse;

import com.threed.jpct.FrameBuffer;


/**
 * Also known as Mouse Mapper. 
 */
public class GLPointer {

	private boolean hidden = false;
	private boolean pressed[] = new boolean[]{false, false, false};

	private int height = 0;

	public GLPointer(FrameBuffer buffer) {
		height = buffer.getOutputHeight();
		init();
	}

	public void hide() {
		if (!hidden) {
			Mouse.setGrabbed(true);
			hidden = true;
		}
	}

	public void show() {
		if (hidden) {
			Mouse.setGrabbed(false);
			hidden = false;
		}
	}

	public boolean isVisible() {
		return !hidden;
	}

	public void destroy() {
		show();
		if (Mouse.isCreated()) {
			Mouse.destroy();
		}
	}

	public boolean isPressed(int button) {
		if (Mouse.isButtonDown(button)) {
			if (pressed[button]) return false;
			pressed[button] = true;
			return true;
		}
		pressed[button] = false;
		return false;
	}

	public boolean isDown(int button) {
		return Mouse.isButtonDown(button);
	}

	public int getX() {
		return Mouse.getX();
	}

	public int getY() {
		return height - Mouse.getY();
	}

	public int getDX() {
		if (Mouse.isGrabbed()) {
			return Mouse.getDX();
		} else {
			return 0;
		}
	}

	public int getDY() {
		if (Mouse.isGrabbed()) {
			return -Mouse.getDY();
		} else {
			return 0;
		}
	}

	public float getDZ() {
		if (Mouse.hasWheel()) {
			return Mouse.getDWheel() / -120f;
		} else {
			return 0;
		}
	}

	private void init() {
		try {
			if (!Mouse.isCreated()) {
				Mouse.create();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}