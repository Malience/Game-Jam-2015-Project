package com.base.engine.audio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

import javax.sound.sampled.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.*;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.AL.*;

public class AudioEngine 
{
	ALContext context;
	ALDevice device;
	
	public void start()
	{
		context = ALContext.create();
		device = context.getDevice();
		
		context.makeCurrent();
	}
	
	public void stop()
	{
		cleanUp();
	}
	
	public void run()
	{
		
		
	}
	
	public void cleanUp()
	{
		context.destroy();
		device.destroy();
	}
	
}
