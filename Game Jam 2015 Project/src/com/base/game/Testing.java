package com.base.game;

import com.base.engine.physics.AABB;
import com.base.engine.physics.BoundingSphere;
import com.base.engine.physics.PhysicsObject;
import com.base.engine.physics.Plane;

public class Testing
{
	public static void testAll()
	{
		BoundingSphere.test();
		AABB.test();
		Plane.Test();
		PhysicsObject.test();
	}
}
