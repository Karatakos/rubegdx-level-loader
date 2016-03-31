package com.anogaijin.rubeloaderlite.serializers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.ReadOnlySerializer;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;
import com.anogaijin.rubeloaderlite.RubeSceneLoader.*;
import com.anogaijin.rubeloaderlite.Util.TextureCache;
import com.anogaijin.rubeloaderlite.containers.RubeCustomProperty;
import com.anogaijin.rubeloaderlite.containers.RubeTexture;

/**
 * Created by adunne on 2015/10/12.
 */
public class RubeTextureSerializer extends ReadOnlySerializer<RubeTexture>
{
    private RubeScene scene;
    private RubeSceneParameters parameters;
    private TextureCache textureCache;

    public RubeTextureSerializer(RubeScene scene, RubeSceneParameters parameters, TextureCache textureCache, Json json) {
        this.scene = scene;
        this.parameters = parameters;
        this.textureCache = textureCache;
    }

    @Override
    public RubeTexture read(Json json, JsonValue jsonData, Class type)
    {
        RubeTexture texture = new RubeTexture();
        texture.setCustomProperties(json.readValue("customProperties", RubeCustomProperty[].class, RubeCustomProperty.class, jsonData));

        int bodyIndex = json.readValue("body", int.class, jsonData);
        if(bodyIndex >= 0) {
            texture.rubeRigidBody = scene.getBodyMap().get(String.valueOf(bodyIndex));

            // This is just a marker, so don't bother generating a texture
            //
            if (texture.rubeRigidBody.rigidBody == null)
                return null;
        }

        texture.texture = textureCache.getTexture(json.readValue("file", String.class, jsonData));
        texture.center.set(json.readValue("center", Vector2.class, texture.center, jsonData));
        texture.scale = json.readValue("scale", float.class, texture.scale, jsonData);
        texture.rotation = json.readValue("angle", float.class,texture.rotation, jsonData);

        Vector2[] vertices = json.readValue("corners", Vector2[].class, jsonData);
        texture.width = vertices[0].sub(vertices[1]).len();
        texture.height = vertices[1].sub(vertices[2]).len();

        texture.filter = json.readValue("filter", int.class, texture.filter, jsonData);
        texture.flip = json.readValue("flip", boolean.class, texture.flip, jsonData);
        texture.opacity = json.readValue("opacity", float.class, texture.opacity, jsonData);
        texture.renderOrder = json.readValue("renderOrder", int.class, texture.renderOrder, jsonData);

        int[] colorArray = json.readValue("colorTint", int[].class, new int[]{255,255,255,255}, jsonData);
        texture.color.set((float)colorArray[0]/255, (float)colorArray[1]/255, (float)colorArray[2]/255, (float)colorArray[3]/255);

        // Register itself in the scene
        //
        scene.getAllTextures().add(texture);

        return texture;
    }
}