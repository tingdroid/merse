package com.threed.jpct;

import com.threed.jpct.World;

public class Light extends com.threed.jpct.util.Light {

	private static final long serialVersionUID = 1L;

	public Light(World arg0) {
		super(arg0);
		setAttenuation(-1);
	}
	
	public SimpleVector getPosition(SimpleVector store) {
		store.set(super.getPosition());
		return store;
	}
	
}
