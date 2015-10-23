package com.base.engine.audio;

import static org.lwjgl.openal.AL10.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.lwjgl.BufferUtils;

public class Sound 
{
	private int source;
	
	public Sound(String fileName)
	{
		this.source = compileSource(fileName);
	}
	

	public Sound(File soundFile)
	{
		this.source = compileSource(soundFile);
	}
	
	public Sound(AudioInputStream soundStream)
	{
		this.source = compileSource(soundStream);
	}
	
	private int compileSource(String fileName)
	{
		try
		{
			return compileSource(new File("./res/audio/" + fileName));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return 0;
	}
	
	private int compileSource(File soundFile)
	{
		try
		{
			AudioInputStream soundStream = AudioSystem.getAudioInputStream(soundFile);
			int fin = compileSource(soundStream);
			soundStream.close();
			return fin;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return 0;
	}
	
	private int compileSource(AudioInputStream soundStream)
	{
		try
		{
			int buffer = alGenBuffers();
			
			AudioFormat fmt = soundStream.getFormat(); 

			int sampleRate = (int) fmt.getSampleRate();
			int format = fmt.getChannels();
			int fFormat = 0;
			
			if (format == 1)
	        {
	            if (fmt.getSampleSizeInBits() == 8)
	            	fFormat = AL_FORMAT_MONO8;
	            else if (fmt.getSampleSizeInBits() == 16)
	            	fFormat = AL_FORMAT_MONO16;
	        }
	        else if (format == 2)
	        {
	            if (fmt.getSampleSizeInBits() == 8)
	            	fFormat = AL_FORMAT_STEREO8;
	            else if (fmt.getSampleSizeInBits() == 16)
	            	fFormat = AL_FORMAT_STEREO16;
	        }
			
			// Length of the WAV file
	        int length = 0;
			try {
				length = soundStream.available();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

	        if (length <= 0)
	            length = soundStream.getFormat().getChannels() * (int) soundStream.getFrameLength() * soundStream.getFormat().getSampleSizeInBits() / 8;

	        byte[] samples = new byte[length];
	        DataInputStream dis = new DataInputStream(soundStream);
	        try {
				dis.readFully(samples);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        ByteBuffer src = ByteBuffer.wrap(samples); 
	        src.order(ByteOrder.LITTLE_ENDIAN); 

	        
	        ByteBuffer data = convertAudioBytes(src, fmt.getSampleSizeInBits() == 16);

	        // Close the input streams
	        try
	        {
		        soundStream.close();
		        dis.close();
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }

	        alBufferData(buffer, fFormat, data, sampleRate);
	        
	        
	        int source = alGenSources();
	        alSourcei(source, AL_BUFFER, buffer);
	        
			alDeleteBuffers(buffer);
			
			return source;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return 0;
	}
	
	private static ByteBuffer convertAudioBytes(ByteBuffer samples, boolean stereo)
    {
        ByteBuffer dest = BufferUtils.createByteBuffer(samples.capacity());

        if (stereo)
        {
            ShortBuffer dest_short = dest.asShortBuffer();
            ShortBuffer src_short = samples.asShortBuffer();

            while (src_short.hasRemaining())
                dest_short.put(src_short.get());
        }
        else
        {
            while (samples.hasRemaining())
                dest.put(samples.get());
        }

        dest.rewind();
        return dest;
    }
	
	public int getSource()
	{
		return source;
	}
	
	@Override
	public void finalize()
	{
		alDeleteSources(source);
	}
}
