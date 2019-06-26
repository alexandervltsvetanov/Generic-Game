package com.alexander.tsvetanov.generic.game.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public AssetManager manager;

    public static String player = "redball.png";
    public static String tile = "tile.jpg";
    public static String background = "background.png";


    public Assets(){
        manager = new AssetManager();
    }

    public void load(){
        manager.load(player, Texture.class);
        manager.load(tile, Texture.class);
        manager.load(background, Texture.class);
    }

    public void dispose(){
        manager.dispose();
    }
}
