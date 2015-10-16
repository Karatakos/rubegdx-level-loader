package com.anogaijin.rubeloaderlite;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by adunne on 2015/10/15.
 */
public class TextureCache {
    private AssetManager assetManager;
    private ObjectMap<String, TextureRegion> textureMap = new ObjectMap<>();

    public TextureCache(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public TextureRegion getTexture(String filename) {
        if (assetManager.isLoaded(filename))
            return new TextureRegion(assetManager.get(filename));

        if (!textureMap.containsKey(filename))
            return null;

        return textureMap.get(filename);
    }
}
