package com.anogaijin.rubeloaderlite.Util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import java.io.File;

/**
 * Created by adunne on 2015/10/15.
 */
public class TextureCache {
    private AssetManager assetManager;
    private boolean checkAtlasIsLoaded;

    private ObjectMap<String, TextureRegionWrapper> textureRegionMap = new ObjectMap<>();
    private ObjectMap<String, TextureRegionWrapper[]> textureRegionCollectionMap = new ObjectMap<>();

    public TextureCache(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public TextureRegion getTexture(String filename) {
        String file = filename;

        // Maybe we have a fully fledged texture already loaded
        //
        if (assetManager.isLoaded(file))
            return new TextureRegion(assetManager.get(file, Texture.class));

        // Take the filename (-extension) as it would be recorded in the pack
        //
        file = new File(filename).getName();
        file = file.substring(0, file.lastIndexOf('.'));

        // We shouldn't resolve a texture if it's atlas has been unloaded
        //
        checkAtlasIsLoaded = true;

        // If we don't have it cached we may just need to load any missing pack files
        //
        if (!textureRegionMap.containsKey(file)) {
            Array<TextureAtlas> packs = new Array<>();
            assetManager.getAll(TextureAtlas.class, packs);

            for (TextureAtlas atlas : packs) {
                String atlasFileName = assetManager.getAssetFileName(atlas);

                // If this is new then cache it along with all it's textures
                //
                if (!textureRegionCollectionMap.containsKey(atlasFileName)) {
                   Array<TextureRegionWrapper> texturesRegionWrappers = new Array<>();
                   for (AtlasRegion atlasRegion : atlas.getRegions()) {
                       TextureRegionWrapper textureRegionWrapper = new TextureRegionWrapper(atlasRegion, atlasFileName);

                       // Required for easy cache invalidation in the event that the atlas is unloaded
                       texturesRegionWrappers.add(textureRegionWrapper);

                       // Actually cache the new texture
                       // Note: If two regions of different sheets have the same name only one can exist
                       //
                       textureRegionMap.put(atlasRegion.name,textureRegionWrapper);
                   }

                   // Finally cache the atlas and texture collection
                   textureRegionCollectionMap.put(atlasFileName, texturesRegionWrappers.toArray());
                }
            }

            // Maybe we have it now!
            //
            if (!textureRegionMap.containsKey(file))
                return null;

            // A little optimization to prevent further texture atlas checks later (since we just checked)
            //
            checkAtlasIsLoaded = false;
        }

        TextureRegionWrapper textureDetails = textureRegionMap.get(file);

        if (checkAtlasIsLoaded) {
            // If this atlas is no longer loaded we will remove it from the cache
            //
            if (!assetManager.isLoaded(textureDetails.atlasFile)) {

                // Remove any texture regions using this atlas
                //
                for (TextureRegionWrapper textureRegion : textureRegionCollectionMap.get(textureDetails.atlasFile))
                    textureRegionMap.remove(textureRegion.atlasRegion.name);

                // Remove the atlas
                //
                textureRegionCollectionMap.remove(textureDetails.atlasFile);

                // Nothing to see here
                return null;
            }
        }

        return textureDetails.atlasRegion;
    }

    private class TextureRegionWrapper {
        public AtlasRegion atlasRegion;
        public String atlasFile;

        public TextureRegionWrapper(AtlasRegion atlasRegion, String atlasFile) {
            this.atlasRegion = atlasRegion;
            this.atlasFile = atlasFile;
        }
    }
}
