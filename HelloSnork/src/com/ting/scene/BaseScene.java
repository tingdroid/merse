package com.ting.scene;

import java.net.URL;

import com.threed.jpct.Loader;
import com.threed.jpct.Matrix;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureInfo;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;

public class BaseScene {
	static URL baseUrl = BaseScene.class.getResource("/assets/");
	
	public World world = new World();

	public static String addTexture(String name, int width, int height) {
		String shortName = name.contains(".") ? 
				name.substring(0, name.indexOf(".") - 1) : name;

		TextureManager tm = TextureManager.getInstance();
		if (!tm.containsTexture(shortName)) {
			Texture texture = new Texture(baseUrl, name);
			if (width != 0 && height != 0) {
				// bitmap = BitmapHelper.rescale(bitmap, width, height);
			}
			tm.addTexture(shortName, texture);
		}

		return shortName;
	}

	public static String addTexture(String name) {
		return addTexture(name, 0, 0);
	}

	public static TextureInfo addTexture(String name, String mod, int mode) {
		TextureManager tm = TextureManager.getInstance();
		TextureInfo ti = new TextureInfo(tm.getTextureID(addTexture(name)));
		ti.add(tm.getTextureID(addTexture(mod)), mode);
		return ti;
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

	public static Object3D loadMD2(String name, float scale) {
		Object3D temp = Loader.loadMD2(baseUrl, name + ".md2",
				scale);
		return temp;
	}

	public static Object3D[] load3DS(String name, float scale) {
		Object3D[] arr = Loader.load3DS(baseUrl, name + ".3ds",
				scale);
		return arr;
	}
}
