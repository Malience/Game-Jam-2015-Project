package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class AABB 
{
	private Vector3f minExtents;
	private Vector3f maxExtents;
	
	public AABB(Vector3f minExtents, Vector3f maxExtents) {
		super();
		this.minExtents = minExtents;
		this.maxExtents = maxExtents;
	}
	
	public IntersectData intersectAABB(AABB other)
	{
		Vector3f distances1 = other.getMinExtents().sub(getMaxExtents());
		Vector3f distances2 = getMinExtents().sub(other.getMaxExtents());
		Vector3f distances = distances1.max(distances2);
		
		float maxDistance = distances.max();
		
		return new IntersectData(maxDistance < 0, maxDistance);
	}
	
	public static void test()
	{
		AABB aabb1 = new AABB(new Vector3f(0.0f,0.0f,0.0f), new Vector3f(1.0f,1.0f,1.0f));
		AABB aabb2 = new AABB(new Vector3f(1.0f,1.0f,1.0f), new Vector3f(2.0f,2.0f,2.0f));
		AABB aabb3 = new AABB(new Vector3f(1.0f,0.0f,0.0f), new Vector3f(2.0f,1.0f,1.0f));
		AABB aabb4 = new AABB(new Vector3f(0.0f,0.0f,-2.0f), new Vector3f(1.0f,1.0f,-1.0f));
		AABB aabb5 = new AABB(new Vector3f(0.0f,0.5f,0.0f), new Vector3f(1.0f,1.5f,1.0f));
		AABB aabb6 = new AABB(new Vector3f(0.3f,0.5f,0.7f), new Vector3f(1.3f,1.5f,1.7f));
		
		IntersectData aabb1Intersectaabb2 = aabb1.intersectAABB(aabb2);
		IntersectData aabb1Intersectaabb3 = aabb1.intersectAABB(aabb3);
		IntersectData aabb1Intersectaabb4 = aabb1.intersectAABB(aabb4);
		IntersectData aabb1Intersectaabb5 = aabb1.intersectAABB(aabb5);
		IntersectData aabb1Intersectaabb6 = aabb1.intersectAABB(aabb6);
		
		assert(aabb1Intersectaabb2.getDoesIntersect() 	== false);
		assert(aabb1Intersectaabb2.getDistance()		== 0.0f);
		
		assert(aabb1Intersectaabb3.getDoesIntersect() 	== false);
		assert(aabb1Intersectaabb3.getDistance()		== 0.0f);
		
		assert(aabb1Intersectaabb4.getDoesIntersect() 	== false);
		assert(aabb1Intersectaabb4.getDistance()		== 1.0f);
		
		assert(aabb1Intersectaabb5.getDoesIntersect() 	== true);
		assert(aabb1Intersectaabb5.getDistance()		== -0.5f);
		
		assert(aabb1Intersectaabb6.getDoesIntersect() 	== true);
		assert(aabb1Intersectaabb6.getDistance()		== -0.3f);
		
//		System.out.println("A1 - A3: " + aabb1Intersectaabb3.getDoesIntersect() + " , Distance: " + aabb1Intersectaabb3.getDistance());
//		System.out.println("A1 - A4: " + aabb1Intersectaabb4.getDoesIntersect() + " , Distance: " + aabb1Intersectaabb4.getDistance());
//		System.out.println("A1 - A5: " + aabb1Intersectaabb5.getDoesIntersect() + " , Distance: " + aabb1Intersectaabb5.getDistance());
//		System.out.println();
	}
	
	public Vector3f getMinExtents() {
		return minExtents;
	}
	public Vector3f getMaxExtents() {
		return maxExtents;
	}
	
	
}
