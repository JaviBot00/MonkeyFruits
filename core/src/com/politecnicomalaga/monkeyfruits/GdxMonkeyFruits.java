package com.politecnicomalaga.monkeyfruits;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.monkeyfruits.models.Fruits;
import com.politecnicomalaga.monkeyfruits.models.Monkey;
import com.politecnicomalaga.monkeyfruits.view.PanelNumeros;

import java.util.ArrayList;

public class GdxMonkeyFruits extends ApplicationAdapter {

    SpriteBatch myBatch;
    Monkey DonkeyKong;
    Fruits GomuGomu;
    PanelNumeros numberPanel;
    public static final ArrayList<Fruits> FallingFruits = new ArrayList<>();
    public static final ArrayList<Fruits> ReserveFruits = new ArrayList<>();

    @Override
    public void create() {
        myBatch = new SpriteBatch();
        DonkeyKong = new Monkey();
        GomuGomu = new Fruits();
        FallingFruits.add(GomuGomu);
        numberPanel = new PanelNumeros(0, 0, 50);
        numberPanel.setData(0);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        myBatch.begin();
        numberPanel.pintarse(myBatch);
        myBatch.end();
        pantallaJuego();
    }


    public void pantallaJuego() {

        //movimiento gorila
        if (Gdx.input.justTouched()) {
            int pixelX = Gdx.input.getX();
            float pantalladivididaEntre3 = Gdx.graphics.getWidth() / 3f;
            if (pixelX > (pantalladivididaEntre3 * 2)) {
                DonkeyKong.setVelX(2);
            } else if (pixelX < (pantalladivididaEntre3)) {
                DonkeyKong.setVelX(-2);
            } else {
                DonkeyKong.setVelX(0);
            }
        }

        //colisiones
        if (GomuGomu.fColisiona(DonkeyKong)) {
            numberPanel.incrementa(1);
        }

        //Spawn frutas
        if (Math.random() > 0.97f) {
            GomuGomu.Falling();
        }

        //monico
        DonkeyKong.moverse();
        DonkeyKong.mPintarse(myBatch);

        GomuGomu.fMove();
        GomuGomu.fDraw(myBatch);

    }

    public void dispose() {
        myBatch.dispose();
        DonkeyKong.dispose();
        GomuGomu.fDispose();
    }
}