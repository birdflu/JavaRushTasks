package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.List;

public class PlayerShip extends Ship {
    private Direction direction = Direction.UP;

    public void setDirection(Direction newDirection) {
        if (newDirection != Direction.DOWN) this.direction = newDirection;
    }

    public PlayerShip() {
        super(SpaceInvadersGame.WIDTH / 2.0, SpaceInvadersGame.HEIGHT - ShapeMatrix.PLAYER.length - 1);
        setStaticView(ShapeMatrix.PLAYER);
    }

    public Direction getDirection() {
        return direction;
    }

    public  void verifyHit(List<Bullet> bullets) {
    if (!bullets.isEmpty())
        if (this.isAlive)
            for (Bullet bullet: bullets)
                if ((bullet.isAlive) && (this.isCollision(bullet)))
                    {
                        this.kill();
                        bullet.kill();
                    }
    }

    @Override
    public void kill() {
        if (isAlive) {
            isAlive = false;
            super.setAnimatedView(false, ShapeMatrix.KILL_PLAYER_ANIMATION_FIRST, ShapeMatrix.KILL_PLAYER_ANIMATION_SECOND, ShapeMatrix.KILL_PLAYER_ANIMATION_THIRD, ShapeMatrix.DEAD_PLAYER);
        }
    }

    public void move(){
        if (isAlive) {
            if (direction == Direction.LEFT) x = x - 1;
            else if (direction == Direction.RIGHT) x = x + 1;
            if (x < 0) x = 0;
            if (x + width > SpaceInvadersGame.WIDTH) x = SpaceInvadersGame.WIDTH - width;
        }
    }

    public Bullet fire() {
        if (!isAlive) return null;
        else return new Bullet( x + 2, y - ShapeMatrix.BULLET.length, Direction.UP);
    }

    public void win() {
        setStaticView(ShapeMatrix.WIN_PLAYER);
    }
}
