package com.ting.app;

import org.lwjgl.opengl.Display;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.IRenderer;
import com.ting.scene.Scene;

/**
 * A simple Scene using the OpenGL-renderer.
 */
public class MainOGL {

	private FrameBuffer buffer;
	private GLPointer pointer;
	private Scene scene;
	
	private boolean fullScreen = false;
	private int windowWidth = 800;
	private int windowHeight = 600;

	public static void main(String[] args) throws Exception {
		if (args.length > 0) System.out.println(args[0]);
		new MainOGL().loop();
	}

	public MainOGL() throws Exception {
		scene = new Scene();
	}
	
	private void init()  throws Exception {
		if (buffer != null)
			dispose();
		
		int frameWidth = fullScreen ? Display.getDesktopDisplayMode().getWidth() : windowWidth;
		int frameHeight = fullScreen ?  Display.getDesktopDisplayMode().getHeight() : windowHeight;
		
		buffer = new FrameBuffer(frameWidth, frameHeight, FrameBuffer.SAMPLINGMODE_NORMAL);
		buffer.disableRenderer(IRenderer.RENDERER_SOFTWARE);
		buffer.enableRenderer(IRenderer.RENDERER_OPENGL);

		Display.setFullscreen(fullScreen);
		Display.setTitle("Snork");

		pointer = new GLPointer(buffer);
	}

	private void dispose() {
		buffer.disableRenderer(IRenderer.RENDERER_OPENGL);
		buffer.dispose();
		buffer = null;
	}

	private void loop() throws Exception {
		init();
		
		while (!Display.isCloseRequested()) {
			if (pointer.isPressed(1)) {
				fullScreen = !fullScreen;
				init();
				continue;
			}
			if (pointer.isDown(0)) {
				pointer.hide();
				scene.move(pointer.getDX(), pointer.getDY());
			} else {
				pointer.show();
				scene.loop();
				// scene.move(-1, 0);  // animate
			}
			float dz = pointer.getDZ();
			if (dz != 0) {
				scene.zoom(dz);
			}
			scene.hud.setText(0, "Position: %d %d", pointer.getX(), pointer.getY());
			scene.hud.setText(1, "Scale: %d", (int)dz);
					
			buffer.clear(scene.background);
			scene.world.renderScene(buffer);
			scene.world.draw(buffer);
			buffer.update();

			scene.hud.draw(buffer);
			scene.hud.draw(buffer, "Snork", -50, 28);
			
			buffer.displayGLOnly();
			Thread.sleep(10);
		}
		dispose();
		System.exit(0);
	}
}
