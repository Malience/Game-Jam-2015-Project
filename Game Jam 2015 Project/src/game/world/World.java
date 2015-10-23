package game.world;

import com.base.engine.core.Game;

import game.GObject;

public class World 
{
	public static Game game;
	
	public static void New(GObject object)
	{
		game.addObject(object.convert());
	}
}
