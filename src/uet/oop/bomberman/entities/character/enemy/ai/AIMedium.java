package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

import java.util.ArrayList;

public class AIMedium extends AI {
	Bomber _bomber;
	Enemy _e;
	
	public AIMedium(Bomber bomber, Enemy e) {
		_bomber = bomber;
		_e = e;
	}

	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
		if(_bomber == null){
			return random.nextInt(4);
		}
		ArrayList<Integer> possible_direction = new ArrayList<>();
		double dx = _bomber.getX() - _e.getX() ;
		double dy = _bomber.getY() - _e.getY() ;

		if (dx > 0) {
			possible_direction.add(1);
		} else if (dx < 0) {
			possible_direction.add(3);
		}

		if (dy > 0) {
			possible_direction.add(2);
		} else if (dy < 0) {
			possible_direction.add(0);
		}

		return possible_direction.get(random.nextInt(possible_direction.size()));
	}

}
