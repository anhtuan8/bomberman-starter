package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

import java.util.ArrayList;

public class AvoidBombAI extends AI {
    Bomb _bomb;
    Enemy _e;

    public AvoidBombAI(Bomb bomb, Enemy e) {
        _bomb = bomb;
        _e = e;
    }

    @Override
    public int calculateDirection() {
        // TODO: cài đặt thuật toán tìm đường đi
        if(_bomb!=null ){
            return random.nextInt(4);
        }
        ArrayList<Integer> possible_direction = new ArrayList<>();
        double dx = 16*_bomb.getX() - _e.getX() ;
        double dy = 16*_bomb.getY() - _e.getY() ;

        if (dx < 0) {
            possible_direction.add(1);
        } else if (dx > 0) {
            possible_direction.add(3);
        }

        if (dy < 0) {
            possible_direction.add(2);
        } else if (dy > 0) {
            possible_direction.add(0);
        }

        return possible_direction.get(random.nextInt(possible_direction.size()));
    }

}
