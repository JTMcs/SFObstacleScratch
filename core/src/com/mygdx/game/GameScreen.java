//https://www.youtube.com/watch?v=ygpglRYcNS4&list=PLvnEciYMRSk-l5oKsIsbrBbP2ZJFSZLns
package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen extends ApplicationAdapter {

    OrthographicCamera ocCam;
    public Rectangle recBDown, recBUp, recBLeft, recBRight;
    SpriteBatch bChar;
    private Character character;
    private Obstacle obstacle;
    int picID = 1, gdxW, gdxH;
    BitmapFont bmFont;

    @Override
    public void create() {
        gdxW = Gdx.graphics.getWidth();
        gdxH = Gdx.graphics.getHeight();
        ocCam = new OrthographicCamera();
        ocCam.setToOrtho(false);
        bChar = new SpriteBatch();
        character = new Character();
        character.setPosition(200, 100);
        obstacle = new Obstacle();
        System.out.println(gdxW + "" + gdxH);
        bmFont = new BitmapFont();
        bmFont.setColor(Color.WHITE);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        bChar.setProjectionMatrix(ocCam.combined);
        bChar.begin();
        if (picID == 1) {
            character.draw1(bChar);
        } else {
            character.draw2(bChar);
        }
        obstacle.draw(bChar);
        bmFont.draw(bChar, "Lives: " + Integer.toString(obstacle.nLives), gdxW - 100, gdxH - 50);
        bChar.end();

        recBDown = new Rectangle(0, 0, gdxW, 10);
        recBUp = new Rectangle(0, gdxH - 10, gdxW, 10);
        recBLeft = new Rectangle(0, 0, 10, gdxH);
        recBRight = new Rectangle(gdxW - 10, 0, 10, gdxH);

        //Updates
        character.update(Gdx.graphics.getDeltaTime());

        //Boundaries
        if (character.bounds(recBDown) == 1) {
            character.action(1, 0, 10);
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                character.jump();
            }
        }

        else if (character.bounds(recBUp) == 1) {
            character.action(4, 0, gdxH - 10);
        }

        if (character.bounds(recBLeft) == 1) {
            character.action(2, 10, 0);
        }

        if (character.bounds(recBRight) == 1) {
            character.action(3, gdxW - 10, 0);
        }

        //Controls
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            character.moveLeft(Gdx.graphics.getDeltaTime());
            picID = 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            character.moveRight(Gdx.graphics.getDeltaTime());
            picID = 1;
        }

        //Obstacle
        obstacle.bounds(character.recHB);
    }

    @Override
    public void dispose() {
        bChar.dispose();
        bmFont.dispose();
    }
}
