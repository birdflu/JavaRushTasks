package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5;
    private static final int PLAYER_BULLETS_MAX = 1;

    private List<Star> stars;
    private EnemyFleet enemyFleet;
    private List<Bullet> enemyBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private List<Bullet> playerBullets;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<Bullet>();
        playerShip = new PlayerShip();
        isGameStopped = false;
        animationsCount = 0;
        playerBullets = new ArrayList<Bullet>();
        score = 0;
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        playerShip.draw(this);
        for (Bullet bullet : playerBullets) bullet.draw(this);
        for (Bullet bullet : enemyBullets) bullet.draw(this);
        enemyFleet.draw(this);
    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellValueEx(x, y, Color.BLACK, "");
            }
        }
        for (Star s: stars){
            s.draw(this);
        }
    }

    private void createStars(){
        stars = new ArrayList<Star>();
        for (int i = 0; i < 8; i++) {
            stars.add(new Star(i*4+(i*i)/2,(i>4)?i+3*i:i));
        }
    }

    @Override
    public void onTurn(int step) {
        //super.onTurn(step);
        moveSpaceObjects();
        check();
        Bullet bullet = enemyFleet.fire(this); if (bullet != null) enemyBullets.add(bullet);
        setScore(score);
        drawScene();
    }

    private void moveSpaceObjects(){
        enemyFleet.move();
        for (Bullet bullet : enemyBullets) bullet.move();
        for (Bullet bullet : playerBullets) bullet.move();
        playerShip.move();
    }

    private void removeDeadBullets(){
        // необходимо из списка enemyBullets удалить все "неживые" пули
        // и те, которые вылетели за пределы экрана (координата y пули больше либо равна HEIGHT - 1).
        enemyBullets.removeIf(bullet -> !bullet.isAlive || ((bullet.y >= (HEIGHT - 1))));

        // необходимо удалить все пули из списка playerBullets,
        // если пуля "неживая" ИЛИ сумма координаты y и поля height пули меньше нуля.
        playerBullets.removeIf(bullet -> !bullet.isAlive || ((bullet.y + bullet.height) < 0));
    }

    private void check(){
        playerShip.verifyHit(enemyBullets);
        score = score + enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        if (enemyFleet.getBottomBorder() >= playerShip.y) playerShip.kill();
        if (enemyFleet.getShipsCount() == 0) {playerShip.win(); stopGameWithDelay();}
        removeDeadBullets();
        if (!playerShip.isAlive) stopGameWithDelay();
    }

    private  void stopGame(boolean isWin){
        isGameStopped = true;
        stopTurnTimer();
        if (isWin) showMessageDialog(Color.BLACK, "WIN!", Color.GREEN, 20);
        else showMessageDialog(Color.BLACK, "YOU LOOSE!", Color.RED, 20);
    }

    private void stopGameWithDelay(){
        animationsCount =animationsCount + 1;
        if (animationsCount  >= 10) stopGame(playerShip.isAlive);
    }

    @Override
    public void onKeyPress(Key key) {
        if ((key == Key.SPACE) & (isGameStopped)) createGame();
        else if (key == Key.LEFT) playerShip.setDirection(Direction.LEFT);
        else if (key == Key.RIGHT) playerShip.setDirection(Direction.RIGHT);
        else if (key == Key.SPACE) {
            Bullet bullet = playerShip.fire();
            if ((bullet != null) & (playerBullets.size() < PLAYER_BULLETS_MAX)) playerBullets.add(bullet);
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if ((key == Key.LEFT) & (playerShip.getDirection() == Direction.LEFT)) playerShip.setDirection(Direction.UP);
        if ((key == Key.RIGHT) & (playerShip.getDirection() == Direction.RIGHT)) playerShip.setDirection(Direction.UP);
    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if ((x < WIDTH) & (x >= 0) & (y < HEIGHT)& (y >= 0) ) super.setCellValueEx(x, y, cellColor, value);
    }
}