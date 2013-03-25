package com.ting.app;

import javax.swing.JFrame;

import com.threed.jpct.Config;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.IRenderer;
import com.ting.scene.Scene;

/**
 * A simple Scene using the Software-renderer and rendering into a frame using
 * active rendering.
 * 
 */
public class MainSoftware {

	private JFrame frame;
	private FrameBuffer buffer;
	private Scene scene;

	public static void main(String[] args) throws Exception {
		new MainSoftware().loop();
	}

	public MainSoftware() throws Exception {
		frame = new JFrame("Snork");
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		scene = new Scene();

		Config.glAvoidTextureCopies = true;
		Config.glColorDepth = 24;
		Config.glFullscreen = false;
		Config.glShadowZBias = 0.8f;
		Config.lightMul = 1;
	}

	private void loop() throws Exception {
		buffer = new FrameBuffer(800, 600, FrameBuffer.SAMPLINGMODE_NORMAL);

		AWTPointer pointer = new AWTPointer(frame);

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
			buffer.display(frame.getGraphics());
			Thread.sleep(10);
		}
		buffer.disableRenderer(IRenderer.RENDERER_OPENGL);
		buffer.dispose();
		frame.dispose();
		System.exit(0);
	}
}
