package com.base.engine.physics;

import java.util.HashMap;

import com.base.engine.core.Vector3f;

import static com.base.engine.physics.Collider.*;

public class PhysicsObject 
{	
	private Vector3f position;
	private Vector3f oldPosition;
	private Vector3f velocity;
	private Collider collider;
	
	public PhysicsObject(Collider collider, Vector3f velocity) {
		super();
		this.collider = collider;
		this.position = this.collider.getCenter();
		oldPosition = position;
		this.velocity = velocity;
	}
	
	public PhysicsObject(PhysicsObject copy)
	{		
		this.position = new Vector3f(copy.position);
		oldPosition = new Vector3f(copy.oldPosition);
		this.velocity = new Vector3f(copy.velocity);
		this.collider = copy.collider;
		
		collider.addReference();
	}
	
	
	
	public void integrate(float delta)
	{
		position = position.add(velocity.mul(delta));
	}
	
	public static void test()
	{
		PhysicsObject test = new PhysicsObject(new BoundingSphere(new Vector3f(0.0f, 1.0f, 0.0f), 1.0f), new Vector3f(1.0f, 2.0f, 3.0f));
		
		test.integrate(20.0f);
		
		Vector3f testPos = test.getPosition();
		Vector3f testVel = test.getVelocity();
		
		assert(testPos.getX() == 20.0f);
		assert(testPos.getY() == 41.0f);
		assert(testPos.getZ() == 60.0f);
		
		assert(testVel.getX() == 1.0f);
		assert(testVel.getY() == 2.0f);
		assert(testVel.getZ() == 3.0f);
	}
	
	public Vector3f getPosition() {
		return position;
	}
	public Vector3f getVelocity() {
		return velocity;
	}
	
	public void setVelocity(Vector3f velocity) {
		this.velocity= velocity;
	}
	public Collider getCollider()
	{
		Vector3f translation = position.sub(oldPosition);
		oldPosition = position;
		collider.transform(translation);
		return collider;
	}
	
}
