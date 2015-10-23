package game;

public class TestObject extends GObject implements Renderable
{
	String texture = "test.png";
	String mesh = "monkey3.obj";
	
	public TestObject(int x, int y, int z) {
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTexture() {
		// TODO Auto-generated method stub
		return texture;
	}

	@Override
	public String getMesh() {
		// TODO Auto-generated method stub
		return mesh;
	}
	
	
}
