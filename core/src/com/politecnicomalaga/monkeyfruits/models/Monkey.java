package com.politecnicomalaga.monkeyfruits.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.politecnicomalaga.monkeyfruits.managers.AssetsManager;
import com.politecnicomalaga.monkeyfruits.managers.SettingsManager;


public class Monkey extends ObjetoVolador {

    public Monkey() {
        this.posX = SettingsManager.SCREEN_WIDTH / 2f - SettingsManager.MONKEY_MIDDLE;
        this.posY = 30;
        this.velX = 0;
        this.velY = 0;
        this.img = new Texture(AssetsManager.IMG_MONKEY);
        this.anchoDiv2 = SettingsManager.MONKEY_WIDTH / 2.0f;
        this.altoDiv2 = SettingsManager.MONKEY_HEIGHT / 2.0f;
    }


    public void mPintarse(SpriteBatch miSB) {
        miSB.begin();
        miSB.draw(img, posX, posY, SettingsManager.MONKEY_WIDTH, SettingsManager.MONKEY_HEIGHT);
        miSB.end();
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }
}
