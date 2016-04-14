package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Obstacle {
    Rectangle recSpikeBox, recHeartBox;
    Sprite sprSpike, sprHeart;
    Texture txrSpike, txrHeart;
    int nSpikeStatus, nHeartStatus, nRan, nLives = 10;

    public Obstacle() {
        txrSpike = new Texture("spikeball.png");
        txrHeart = new Texture("heart.png");
        sprSpike = new Sprite(txrSpike, 0, 0, 128, 128);
        sprSpike.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getWidth() / 12);
        sprHeart = new Sprite(txrHeart, 0, 0, 128, 128);
        sprHeart.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getWidth() / 12);
        recSpikeBox = new Rectangle(0f, 0f, sprSpike.getWidth(), sprSpike.getHeight());
        recHeartBox = new Rectangle(0f, 0f, sprHeart.getWidth(), sprHeart.getHeight());
        recSpikeBox.x = (int) Math.floor(Math.random() * (Gdx.graphics.getWidth() - sprSpike.getWidth() + 1));
        recSpikeBox.y = Gdx.graphics.getHeight() / 3;
        sprSpike.setPosition(recSpikeBox.x, recSpikeBox.y);
        recHeartBox.x = (int) Math.floor(Math.random() * (Gdx.graphics.getWidth() - sprSpike.getWidth() + 1));
        recHeartBox.y = recSpikeBox.y;
        sprHeart.setPosition(recHeartBox.x, recSpikeBox.y);
    }

    public void draw(SpriteBatch batch) {
        sprSpike.draw(batch);
        sprHeart.draw(batch);
    }

    public void bounds(Rectangle r) {
        if (nSpikeStatus == 0 && recSpikeBox.overlaps(r)) {
            //0 = not touching, -1 = touching
            System.out.println("collision - 1");
            nSpikeStatus = -1;
            nRan = (int) Math.floor(Math.random() * (Gdx.graphics.getWidth() - sprSpike.getWidth() + 1));
            sprSpike.setX(nRan);
            recSpikeBox.setX(nRan);
            nLives--;
        }
        else if (!recSpikeBox.overlaps(r)) {
            nSpikeStatus = 0;
        }
        if (nHeartStatus == 0 && recHeartBox.overlaps(r)) {
            //0 = not touching, -1 = touching
            System.out.println("collision + 1");
            nHeartStatus = -1;
            nRan = (int) Math.floor(Math.random() * (Gdx.graphics.getWidth() - sprSpike.getWidth() + 1));
            sprHeart.setX(nRan);
            recHeartBox.setX(nRan);
            nLives++;
        }
        else if (!recHeartBox.overlaps(r)) {
            nHeartStatus = 0;
        }
    }
}
