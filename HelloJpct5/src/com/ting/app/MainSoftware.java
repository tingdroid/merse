package com.ting.app;

import javax.swing.JFrame;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.IRenderer;
import com.ting.scene.Scene;

/**
 * A simple HelloWorld using the Software-renderer and rendering into a frame
 * using active rendering.
 * @author EgonOlsen
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
		frame=new JFrame("Hello world");
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		scene = new Scene();
	}

	private void loop() throws Exception {
		buffer = new FrameBuffer(800, 600, FrameBuffer.SAMPLINGMODE_NORMAL);

		while (frame.isShowing()) {
			scene.loop();
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

	