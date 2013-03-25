package com.ting.app;

import java.awt.Canvas;

import javax.swing.JFrame;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.IRenderer;
import com.ting.scene.Pointer;
import com.ting.scene.Scene;

/**
 * A simple HelloWorld using the AWTGL-Renderer and rendering into a frame.
 * @author EgonOlsen
 * 
 */
public class MainAWTGL {

	private JFrame frame;

	public static void main(String[] args) throws Exception {
		new MainAWTGL().loop();
	}

	public MainAWTGL() throws Exception {
		frame = new JFrame("Hello world");
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	private void loop() throws Exception {
		FrameBuffer buffer = new FrameBuffer(800, 600, FrameBuffer.SAMPLINGMODE_NORMAL);
		Canvas canvas = buffer.enableGLCanvasRenderer();
		buffer.disableRenderer(IRenderer.RENDERER_SOFTWARE);
		frame.add(canvas);

		Pointer pointer = new Pointer();
		AWTPointerAdapter pointerAdapter = new AWTPointerAdapter(pointer);
		canvas.addMouseListener(pointerAdapter);
		canvas.addMouseMotionListener(pointerAdapter);
		
		Scene scene = new Scene();

		while (frame.isShowing()) {
			if (pointer.isDown()) {
				scene.move(pointer.getDX(), pointer.getDY());
			} else {
				scene.loop();
			}
			buffer.clear(scene.background);
			scene.world.renderScene(buffer);
			scene.world.draw(buffer);
			buffer.update();
			buffer.displayGLOnly();
			canvas.repaint();
			Thread.sleep(10);
		}
		buffer.disableRenderer(IRenderer.RENDERER_OPENGL);
		buffer.dispose();
		frame.dispose();
		System.exit(0);
	}
}
