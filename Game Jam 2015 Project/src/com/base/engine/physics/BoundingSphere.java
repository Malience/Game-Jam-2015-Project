package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class BoundingSphere extends Collider
{
	private Vector3f center;
	private float radius;
	
	public BoundingSphere(Vector3f center, float radius)
	{
		super(TYPE_SPHERE);
		this.center = center;
		this.radius = radius;
	}
	
	public IntersectData intersectBoundingSphere(BoundingSphere other)
	{
		float radiusDistance = radius + other.radius;
		float centerDistance = (other.getCenter().sub(center)).length();
		
		return new IntersectData(centerDistance < radiusDistance, centerDistance - radiusDistance);
	}
	
	public static void test()
	{
		BoundingSphere sphere1 = new BoundingSphere(new Vector3f(0.0f,0.0f,0.0f), 1.0f);
		BoundingSphere sphere2 = new BoundingSphere(new Vector3f(0.0f,3.0f,0.0f), 1.0f);
		BoundingSphere sphere3 = new BoundingSphere(new Vector3f(0.0f,0.0f,2.0f), 1.0f);
		BoundingSphere sphere4 = new BoundingSphere(new Vector3f(1.0f,0.0f,0.0f), 1.0f);
		
		IntersectData sphere1IntersectSphere2 = sphere1.intersectBoundingSphere(sphere2);
		IntersectData sphere1IntersectSphere3 = sphere1.intersectBoundingSphere(sphere3);
		IntersectData sphere1IntersectSphere4 = sphere1.intersectBoundingSphere(sphere4);
		
		assert(sphere1IntersectSphere2.getDoesIntersect() 	== false);
		assert(sphere1IntersectSphere2.getDistance() 		== 1.0f);
		
		assert(sphere1IntersectSphere3.getDoesIntersect() 	== false);
		assert(sphere1IntersectSphere3.getDistance() 		== 0.0f);
		
		assert(sphere1IntersectSphere4.getDoesIntersect() 	== true);
		assert(sphere1IntersectSphere4.getDistance() 		== -1.0f);
		
//		System.out.println("S1 - S2: " + sphere1IntersectSphere2.getDoesIntersect() + " , Distance: " + sphere1IntersectSphere2.getDistance());
//		System.out.println("S1 - S3: " + sphere1IntersectSphere3.getDoesIntersect() + " , Distance: " + sphere1IntersectSphere3.getDistance());
//		System.out.println("S1 - S4: " + sphere1IntersectSphere4.getDoesIntersect() + " , Distance: " + sphere1IntersectSphere4.getDistance());
//		System.out.println();
	}
	
	@Override
	public void transform(Vector3f translation)
	{
		center = center.add(translation);
	}
	
	@Override
	public Vector3f getCenter() {
		return center;
	}
	public float getRadius() {
		return radius;
	}
}
