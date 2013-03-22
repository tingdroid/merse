package com.ting.scene;

import java.net.URL;

import com.threed.jpct.Loader;
import com.threed.jpct.Matrix;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;

public class BaseScene {
	static URL baseUrl;
	static {
		baseUrl = BaseScene.class.getResource("/assets/");
		if (baseUrl == null) baseUrl = BaseScene.class.getResource("/");
	}
	
	public World world = new World();

	public static String addTexture(String name, int width, int height) {
		String shortName = name.contains(".") ? 
				name.substring(0, name.indexOf(".") - 1) : name;

		TextureManager textureManager = TextureManager.getInstance();
		if (!textureManager.containsTexture(shortName)) {
			Texture texture = new Texture(baseUrl, name);
			if (width != 0 && height != 0) {
				// bitmap = BitmapHelper.rescale(bitmap, width, height);
			}
			textureManager.addTexture(shortName, texture);
		}

		return shortName;
	}

	public static String addTexture(String name) {
		return addTexture(name, 0, 0);
	}

	public static Object3D loadOBJ(String name, float scale) {
		Object3D[] arr = Loader.loadOBJ(baseUrl, name + ".obj", name + ".mtl",
				scale);
		Object3D temp = arr[0];
		temp.setCenter(SimpleVector.ORIGIN);
		temp.rotateX((float) (Math.PI));
		temp.rotateMesh();
		temp.setRotationMatrix(new Matrix());
		return temp;
	}

}
