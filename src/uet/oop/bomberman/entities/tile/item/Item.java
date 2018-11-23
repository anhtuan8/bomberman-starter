package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

public abstract class Item extends Tile {
	public boolean _used = false;

	public Item(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

}
