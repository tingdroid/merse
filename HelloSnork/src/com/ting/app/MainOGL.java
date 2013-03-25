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
	private Scene scene;

	public static void main(String[] args) throws Exception {
		if (args.length > 0) System.out.println(args[0]);
		new MainOGL().loop();
	}

	public MainOGL() throws Exception {
		scene = new Scene();
	}

	private void loop() throws Exception {
		buffer = new FrameBuffer(800, 600, FrameBuffer.SAMPLINGMODE_NORMAL);
		buffer.disableRenderer(IRenderer.RENDERER_SOFTWARE);
		buffer.enableRenderer(IRenderer.RENDERER_OPENGL);

		// Display.setFullscreen(true);
		Display.setTitle("Snork");
		
		GLPointer pointer = new GLPointer(buffer);

		while (!Display.isCloseRequested()) {
			if (pointer.isDown(0)) {
				pointer.hide();
				scene.move(pointer.getDX(), pointer.getDY());
			} else {
				pointer.show();
				scene.loop();
				// scene.move(-1, 0);  // animate
			}
			buffer.clear(scene.background);
			scene.world.renderScene(buffer);
			scene.world.draw(buffer);
			buffer.update();
			buffer.displayGLOnly();
			Thread.sleep(10);
		}
		buffer.disableRenderer(IRenderer.RENDERER_OPENGL);
		buffer.dispose();
		System.exit(0);
	}
}
