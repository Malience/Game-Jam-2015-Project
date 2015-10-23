package com.base.engine.physics;

import com.base.engine.core.*;

public class Collider extends ReferenceCounter
{
	//**COLLIDER TYPES**//
	public final static int TYPE_SPHERE = 0;
	public final static int TYPE_AABB = 1;
	//////////////////////
	
	
	private int type;

	public Collider(int type) {
		super();
		this.type = type;
	}
	
	public IntersectData intersect(Collider other)
	{
		if(type == TYPE_SPHERE && other.getType() == TYPE_SPHERE)
		{
			BoundingSphere self = (BoundingSphere) this;
			return self.intersectBoundingSphere((BoundingSphere)other);
		}
		
		System.err.println("Error: Collisons not implemented between specified colliders");
		System.exit(1);
		
		return null;
	}
	
	public void transform(Vector3f translation)
	{
		
	}
	
	public Vector3f getCenter()
	{
		return new Vector3f(0,0,0);
	}
	
	public int getType() {
		return type;
	};
	
	
}
