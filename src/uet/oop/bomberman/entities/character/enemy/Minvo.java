package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Minvo move fast, trying to chase Bomber.
 */

public class Minvo extends Enemy {
    private int condition ;//-1: normal, -2: can't move, 0,2: follow this direction until found possible horizontal direction, 1,3: similar
    private double stand;

    public Minvo(int x, int y, Board board) {
        super(x, y, board, Sprite.minvo_dead, Game.getBomberSpeed() , 2000);

        _sprite = Sprite.minvo_left1;

        _ai = new AIMedium(board.getBomber(),this);
        _direction = _ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch(_direction) {
            case 0:
            case 1:
                _sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, _animate, 60);
                break;
            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, _animate, 60);
                break;
        }
    }


    @Override
    public void calculateMove() {
        double xa=0,ya=0;
        Random random = new Random();
        if(stand < -5) {
            condition = -2;
            stand = 0;
        }
        if(_steps <=0){
            ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(0,1,2,3));
            switch (condition){
                case -1:{
                    _direction = _ai.calculateDirection();
                    break;
                }

                case -2:{
                    _direction = random.nextInt(4);
                    condition = _direction;
                    break;
                }
                case 0:{
                    choices.remove(Integer.valueOf(2));
                    if(canMove(1,0) | canMove(-1,0)) {
                        choices.remove(Integer.valueOf(0));
                        _direction = choices.get(random.nextInt(choices.size()));
                        condition = -1;
                    }
                    else _direction = condition;
                    break;
                }
                case 2:{
                    choices.remove(Integer.valueOf(0));
                    if(canMove(1,0) | canMove(-1,0)) {
                        choices.remove(Integer.valueOf(2));
                        _direction = choices.get(random.nextInt(choices.size()));
                        condition = -1;
                    }
                    else _direction = condition;
                    break;
                }

                case 1:{
                    choices.remove(Integer.valueOf(3));
                    if(canMove(0,1) | canMove(0,-1)) {
                        choices.remove(Integer.valueOf(1));
                        _direction = choices.get(random.nextInt(choices.size()));
                        condition = -1;
                    }
                    else _direction = condition;
                    break;

                }
                case 3:{
                    choices.remove(Integer.valueOf(1));
                    if(canMove(0,1) | canMove(0,-1)) {
                        choices.remove(Integer.valueOf(3));
                        _direction = choices.get(random.nextInt(choices.size()));
                        condition = -1;
                    }
                    else _direction = condition;
                    break;

                }
            }
            _steps = MAX_STEPS;
        }
        if(_direction == 0) ya = - 1;
        if(_direction == 1) xa = + 1;
        if(_direction == 2) ya = + 1;
        if(_direction == 3) xa = - 1;

        if(canMove(xa,ya)){
            _steps = _steps - 1 -rest;
            move(xa*_speed,ya*_speed);
            _moving = true;
            stand = stand + 0.2;
        }else{
            _steps = 0;
            stand--;
            _moving = false;
        }
    }
}
