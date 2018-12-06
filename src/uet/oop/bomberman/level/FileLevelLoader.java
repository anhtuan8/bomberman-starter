package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.*;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;

	public FileLevelLoader(Board board, int level) throws LoadLevelException, IOException {
		super(board, level);
	}

	@Override
	public void loadLevel(int level) throws IOException {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		File file = null;
		FileReader in = null;
		BufferedReader br = null;
		try {
			file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("levels/Level" + level + ".txt")).getFile());
			in = new FileReader(file);
			br = new BufferedReader(in);
			String[] arr = br.readLine().split(" ");
			_level = Integer.parseInt(arr[0]);
			_width = Integer.parseInt(arr[2]);
			_height = Integer.parseInt(arr[1]);
			_map = new char[100][100];

			for(int x =0;x<_height;x++ ){
				String s = br.readLine();
				for(int y = 0; y < _width ; y ++){
					_map[x][y] = (s.toCharArray())[y];
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			if(in!=null){ in.close();}
			if(br!=null){ br.close();}
		}
	}

		@Override
		public void createEntities () {
			// TODO: tạo các Entity của màn chơi
			// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

			// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
			// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình

			for(int y = 0;y<_height;y++){
				for(int x=0;x<_width;x++){
					switch (_map[y][x]){
						//Wall
						case '#':{
							//them Wall
							int pos = x + y * _width;
							Sprite sprite = Sprite.wall;
							_board.addEntity(pos, new Wall(x, y, sprite));
							break;
						}

						//Brick
						case '*':{
							//them Brick
							_board.addEntity(x + y * _width,
									new LayeredEntity(x, y,
										new Grass(x, y, Sprite.grass),
										new Brick(x, y, Sprite.brick)
									)
							);
							break;
						}

						//bomber
						case 'p':{
							_board.addCharacter( new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board) );
							Screen.setOffset(0, 0);
							_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
							break;
						}

						//portal
						case 'x':{
							int pos = x + y * _width;
							Sprite sprite = Sprite.portal;
							_board.addEntity(pos,
									new LayeredEntity(x, y,
											new Grass(x, y, Sprite.grass),
											new Portal(x,y,_board,Sprite.portal),
											new Brick(x,y, Sprite.brick)
									)
							);
							break;
						}

						//enemy
						case '1':{
							//them ballooom
							_board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
							_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
							break;
						}
						case '2':{
							//them oneal
							int xE = x, yE = y;
							_board.addCharacter(new Oneal(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
							_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
							break;
						}
						case '3':{
							//them doll
							int xE = x, yE = y;
							_board.addCharacter(new Dahl(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
							_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
							break;
						}
						case '4':{
							//them kondoria
							int xE = x, yE = y;
							_board.addCharacter(new Kondoria(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
							_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
							break;
						}
						case '5':{
							//them Minvo
							int xE = x, yE = y;
							_board.addCharacter(new Minvo(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
							_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
							break;

						}
						case '6':{
							//them Ovape
							int xE = x, yE = y;
							_board.addCharacter(new Ovape(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
							_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
							break;

						}
						case '7':{
							//them Pontan
							int xE = x, yE = y;
							_board.addCharacter(new Pontan(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
							_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
							break;

						}
						case '8':{
							//them Pass
							int xE = x, yE = y;
							_board.addCharacter(new Pass(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
							_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
							break;
						}


						//item
						case 'b':{
							//them bomb item
							int xI = x, yI = y;
							_board.addEntity(xI + yI * _width,
									new LayeredEntity(xI, yI,
											new Grass(xI, yI, Sprite.grass),
											new BombItem(xI, yI, Sprite.powerup_bombs),
											new Brick(xI, yI, Sprite.brick)
									)
							);
							break;
						}
						case 'f':{
							//them flame item
							int xI = x, yI = y;
							_board.addEntity(xI + yI * _width,
									new LayeredEntity(xI, yI,
											new Grass(xI, yI, Sprite.grass),
											new FlameItem(xI, yI, Sprite.powerup_flames),
											new Brick(xI, yI, Sprite.brick)
									)
							);
							break;
						}
						case 's':{
							//them speed item
							int xI = x, yI = y;
							_board.addEntity(xI + yI * _width,
									new LayeredEntity(xI, yI,
											new Grass(xI, yI, Sprite.grass),
											new SpeedItem(xI, yI, Sprite.powerup_speed),
											new Brick(xI, yI, Sprite.brick)
									)
							);
							break;
						}

						//grass
						default:{
							//them grass
							int pos = x + y * _width;
							Sprite sprite = Sprite.grass;
							_board.addEntity(pos, new Grass(x, y, sprite));
							break;
						}
					}

				}
			}
		}

}
