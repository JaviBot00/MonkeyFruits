package com.politecnicomalaga.monkeyfruits.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.politecnicomalaga.monkeyfruits.GdxMonkeyFruits;
import com.politecnicomalaga.monkeyfruits.managers.AssetsManager;
import com.politecnicomalaga.monkeyfruits.managers.SettingsManager;

public class Fruits extends ObjetoVolador {

    protected final String[] type = AssetsManager.IMGS_FRUITS;

    public Fruits() {
        this.posX = (float) (Math.random() * (Gdx.graphics.getWidth() - SettingsManager.FRUITS_WIDTH)) + SettingsManager.FRUITS_WIDTH;
        this.posY = Gdx.graphics.getHeight();
        this.velX = 0;
        this.velY = -4;
        this.img = new Texture(type[(int) (Math.random() * 4)]);
        this.anchoDiv2 = SettingsManager.FRUITS_WIDTH / 2.0f;
        this.altoDiv2 = SettingsManager.FRUITS_HEIGHT / 2.0f;
    }

    public void Falling() {
        if (GdxMonkeyFruits.ReserveFruits.isEmpty()) {
            GdxMonkeyFruits.FallingFruits.add(new Fruits());
        } else {
            Fruits aux = GdxMonkeyFruits.ReserveFruits.get(0);
            aux.setPosX();
            aux.setPosY();
            GdxMonkeyFruits.FallingFruits.add(aux);
            GdxMonkeyFruits.ReserveFruits.remove(0);
        }
        if (GdxMonkeyFruits.FallingFruits.get(0).getPosY() <= 0) {
            GdxMonkeyFruits.ReserveFruits.add(GdxMonkeyFruits.FallingFruits.get(0));
            GdxMonkeyFruits.FallingFruits.remove(0);
        }
    }

    public void fMove() {
        for (Fruits ff : GdxMonkeyFruits.FallingFruits) {
            ff.moverse();
        }
    }

    public boolean fColisiona(Monkey aux) {
        boolean resultado = false;
        int posicion = 0;
        while (!resultado && posicion < GdxMonkeyFruits.FallingFruits.size()) {
            resultado = GdxMonkeyFruits.FallingFruits.get(posicion).colisiona(aux);
            if (resultado) {
                Fruits fruitCrashed = GdxMonkeyFruits.FallingFruits.remove(posicion);
                fruitCrashed.dispose();
            }
            posicion++;
        }
        return resultado;
    }

    public void fDraw(SpriteBatch miSB) {
        for (Fruits ff : GdxMonkeyFruits.FallingFruits) {
            ff.pintarse(miSB, SettingsManager.FRUITS_WIDTH, SettingsManager.FRUITS_HEIGHT);
        }
    }

    public void fDispose() {
        for (Fruits ff : GdxMonkeyFruits.FallingFruits) {
            ff.dispose();
        }
        for (Fruits rf : GdxMonkeyFruits.ReserveFruits) {
            rf.dispose();
        }
    }

    public void setPosX() {
        this.posX = (float) (Math.random() * (Gdx.graphics.getWidth() - SettingsManager.FRUITS_WIDTH)) + SettingsManager.FRUITS_WIDTH;
    }

    public void setPosY() {
        this.posY = Gdx.graphics.getHeight();
    }

}
