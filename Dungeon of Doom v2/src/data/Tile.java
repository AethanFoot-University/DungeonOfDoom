package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class Tile {
	private int x, y, width, height;
	private Texture texture;
	private TileType type;
	
	public Tile(int x, int y, int width, int height, TileType type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.texture = quickLoad(type.textureName);
	}
	
	public void draw() {
		drawQuadTexRot(texture, x, y, width, height, 0);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
	
	
}
