package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static  final int ROWS_COUNT = 3;
    private static  final int COLUMNS_COUNT = 10;
    private static  final int  STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet() {
        createShips();
    }

    private void createShips(){
        ships = new ArrayList<EnemyShip>();
        for (int x = 0; x < COLUMNS_COUNT; x++)
            for (int y = 0; y < ROWS_COUNT; y++)
                ships.add(new EnemyShip(x*STEP, y*STEP+12));
        ships.add(new Boss(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5));
    }

    public void draw(Game game)
    {
        for (EnemyShip ship : ships) {
            ship.draw(game);
        }
    }

    private double getLeftBorder() {
        double leftBorder = SpaceInvadersGame.WIDTH;

        for (EnemyShip ship : ships) {
            if (ship.x < leftBorder) leftBorder = ship.x;
        }
        return leftBorder;
    }

    private double getRightBorder(){
        double rightBorder = 0.0d;

        for (EnemyShip ship : ships) {
            if ((ship.x + ship.width) > rightBorder) rightBorder = ship.x + ship.width;
        }
        return rightBorder;
    }

    private double getSpeed() {
        return Math.min(2.0, 3.0/ships.size());
    }

    public void move(){
        Direction directionBefore = direction;
        if (ships.size() > 0) {
         if (direction == Direction.LEFT & getLeftBorder() < 0) direction = Direction.RIGHT;
        else if (direction == Direction.RIGHT & getRightBorder() > SpaceInvadersGame.WIDTH) direction = Direction.LEFT;
        getSpeed();
        if (directionBefore != direction) for (EnemyShip ship: ships) ship.move(Direction.DOWN,getSpeed());
        else for (EnemyShip ship: ships) ship.move(direction,getSpeed());}
    }

    public Bullet fire(Game game){
        if (ships.size() == 0) return null;
        else if (game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY) > 0) return null;
        else return ships.get(game.getRandomNumber(ships.size())).fire();
    }

    public void deleteHiddenShips(){
        ships.removeIf(ship -> (ship.isVisible() == false));
    }

    public double getBottomBorder(){
        double bottomBorder = 0;
        for (EnemyShip ship : ships) if ((ship.y + ship.height) > bottomBorder) bottomBorder = (ship.y + ship.height);
        return bottomBorder;
    }

    public int getShipsCount() {
        return ships.size();
    }

    public int verifyHit(List<Bullet> bullets){
        int score = 0;
        if (bullets.isEmpty()) return 0;
        else {
            for (EnemyShip ship : ships
            )
                for (Bullet bullet : bullets
                )
                    if ((bullet.isAlive) && (ship.isAlive) && (ship.isCollision(bullet))) {
                        bullet.kill();
                        score = score + ship.score;
                        ship.kill();
                    }
            return score;
        }
    }
}   

