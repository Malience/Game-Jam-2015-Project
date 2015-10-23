package com.base.engine.components;

import com.base.engine.core.*;
import com.base.engine.rendering.Window;

public class Camera extends GameComponent
{
	private Matrix projection;

	public Camera(Window window)
	{
		this.projection = new Matrix().setPerspective((float)Math.toRadians(70.0f), (float)window.getWidth()/(float)window.getHeight(), 0.01f, 1000.0f);
	}
	
	public Camera(float fov, float aspect, float zNear, float zFar)
	{
		this.projection = new Matrix().setPerspective(fov, aspect, zNear, zFar);
		//this.projection = new Matrix4f().initOrthographic(400,400,300,300, zNear, zFar);
	}

	public Matrix getViewProjection()
	{
		Matrix cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
		Vector cameraPos = getTransform().getTransformedPos().mul(-1);

		Matrix cameraTranslation = new Matrix().setTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());

		return projection.mul(cameraRotation.mul(cameraTranslation));
	}

	@Override
	public void addToEngine(CoreEngine engine)
	{
		engine.getRenderingEngine().setCamera(this);
	}
}
