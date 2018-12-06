package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sounds.SoundFile;

public class Portal extends Tile {
	protected Board _board;

	public Portal(int x, int y, Board board, Sprite sprite) {
		super(x, y, sprite);
		_board = board;
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý khi Bomber đi vào
		if(e instanceof Bomber){
//			if(e.getXTile() == _x && e.getYTile() == _y) {
//				if (_board.detectNoEnemies()) {
//					if(_board.getLevel().getLevel() == Game.LASTLEVEL){
//						//TODO: Game Finished. Phao hoa tum lum te le.
//					}
//					else {
//						_board.nextLevel();
//					}
//				}
//			}
			SoundFile.next_level.play();
			_board.nextLevel();
			return true;
		}
		return true;
	}

}
