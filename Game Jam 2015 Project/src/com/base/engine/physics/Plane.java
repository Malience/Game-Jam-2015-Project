package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class Plane
{
	private Vector3f normal;
	private float distance;
	
	public Plane(Vector3f normal, float distance) {
		super();
		this.normal = normal;
		this.distance = distance;
	}
	
	public Plane normalize()
	{
		float magnitude = normal.length();
		
		return new Plane(normal.div(magnitude), distance/magnitude);
	}
	
	public IntersectData IntersectSphere(BoundingSphere other)
	{
		float distanceFromSphereCenter = Math.abs(normal.dot(other.getCenter()) + distance);
		float distanceFromSphere = distanceFromSphereCenter - other.getRadius();
		
		return new IntersectData(distanceFromSphere < 0, distanceFromSphere);
	}
	
	public static void Test()
	{
		BoundingSphere sphere1 = new BoundingSphere(new Vector3f(0.0f,0.0f,0.0f), 1.0f);
		BoundingSphere sphere2 = new BoundingSphere(new Vector3f(0.0f,3.0f,0.0f), 1.0f);
		BoundingSphere sphere3 = new BoundingSphere(new Vector3f(0.0f,0.0f,2.0f), 1.0f);
		BoundingSphere sphere4 = new BoundingSphere(new Vector3f(1.0f,0.0f,0.0f), 1.0f);
		
		Plane plane1 = new Plane(new Vector3f(0.0f, 1.0f, 0.0f), 0.0f);
		
		IntersectData plane1IntersectSphere1 = plane1.IntersectSphere(sphere1);
		IntersectData plane1IntersectSphere2 = plane1.IntersectSphere(sphere2);
		IntersectData plane1IntersectSphere3 = plane1.IntersectSphere(sphere3);
		IntersectData plane1IntersectSphere4 = plane1.IntersectSphere(sphere4);
		
		assert(plane1IntersectSphere1.getDoesIntersect() 	== true);
		assert(plane1IntersectSphere1.getDistance()			== -1.0f);
		
		assert(plane1IntersectSphere2.getDoesIntersect() 	== false);
		assert(plane1IntersectSphere2.getDistance()			== 2.0f);
		
		assert(plane1IntersectSphere3.getDoesIntersect() 	== true);
		assert(plane1IntersectSphere3.getDistance()			== -1.0f);
		
		assert(plane1IntersectSphere4.getDoesIntersect() 	== true);
		assert(plane1IntersectSphere4.getDistance()			== -1.0f);
		
//		System.out.println("P1 - S1: " + plane1IntersectSphere1.getDoesIntersect() + " , Distance: " + plane1IntersectSphere1.getDistance());
//		System.out.println("P1 - S1: " + plane1IntersectSphere2.getDoesIntersect() + " , Distance: " + plane1IntersectSphere2.getDistance());
//		System.out.println("P1 - S1: " + plane1IntersectSphere3.getDoesIntersect() + " , Distance: " + plane1IntersectSphere3.getDistance());
//		System.out.println("P1 - S1: " + plane1IntersectSphere4.getDoesIntersect() + " , Distance: " + plane1IntersectSphere4.getDistance());
	}
	
	public Vector3f getNormal() {
		return normal;
	}
	public float getDistance() {
		return distance;
	}
	
	
}
