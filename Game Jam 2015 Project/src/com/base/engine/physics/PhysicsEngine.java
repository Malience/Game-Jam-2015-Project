package com.base.engine.physics;

import java.util.ArrayList;

public class PhysicsEngine 
{
	private ArrayList<PhysicsObject> objects;
	
	public PhysicsEngine()
	{
		objects = new ArrayList<PhysicsObject>();
	}
	
	public void addObject(PhysicsObject object)
	{
		objects.add(object);
	}
	
	public void simulate(float delta)
	{
		for(PhysicsObject object : objects)
		{
			object.integrate(delta);
		}
	}
	
	public void handleCollisions()
	{
		for(int i = 0; i < objects.size(); i++)
		{
			for(int j = i + 1; j < objects.size(); j++)
			{
				IntersectData intersectData  = objects.get(i).getCollider().intersect(objects.get(j).getCollider());
				if(intersectData.getDoesIntersect())
				{
					objects.get(i).setVelocity(objects.get(i).getVelocity().mul(-1));
					objects.get(j).setVelocity(objects.get(j).getVelocity().mul(-1));
				}
			}
		}
	}
	
	//Temporary//
	public PhysicsObject getObject(int i)
	{
		return objects.get(i);
	}
	
	public int getNumObjects()
	{
		return objects.size();
	}
	
	
}
