package com.anogaijin.rubeloaderlite;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by adunne on 2015/10/15.
 */
public class TextureFriendlyAssetManager extends AssetManager{
    ObjectMap<String, TextureRegion> textureMap = new ObjectMap<>();

    @Override
    protected <T> void addAsset(String fileName, Class<T> type, T asset) {
        super.addAsset(fileName, type, asset);
    }

    @Override
    public synchronized <T> T get(String fileName) {
        if (textureMap.containsKey(fileName))
            return textureMap.get(fileName);

        return super.get(fileName);
    }

    @Override
    public synchronized <T> T get(String fileName, Class<T> type) {
        return super.get(fileName, type);
    }

    @Override
    public synchronized <T> Array<T> getAll(Class<T> type, Array<T> out) {
        return super.getAll(type, out);
    }

    @Override
    public synchronized <T> T get(AssetDescriptor<T> assetDescriptor) {
        return super.get(assetDescriptor);
    }
}
