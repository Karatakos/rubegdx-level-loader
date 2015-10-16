package com.anogaijin.rubeloaderlite.serializers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.ReadOnlySerializer;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeSceneLoader.RubeSceneParameters;
import com.anogaijin.rubeloaderlite.Util.TextureCache;
import com.anogaijin.rubeloaderlite.containers.*;
import com.anogaijin.rubeloaderlite.RubeScene;

/**
 * Created by adunne on 2015/10/12.
 */
public class RubeSceneSerializer extends ReadOnlySerializer<RubeScene>
{
    RubeScene scene;
    RubeSceneParameters parameters;

    public RubeSceneSerializer(RubeSceneParameters parameters, AssetManager manager, Json json)
    {
        this.parameters = parameters;

        // Give this to the serializers - they will do the rest.
        //
        scene = new RubeScene();

        // Set any other serializers that we need for the rest of the document
        //
        json.setSerializer(RubeTexture.class, new RubeTextureSerializer(scene, parameters, new TextureCache(manager), json));
        json.setSerializer(RubeRigidBody.class, new RubeRigidBodySerializer(scene, parameters, json));
        json.setSerializer(RubeJoint.class, new RubeJointSerializer(scene, parameters, json));
        json.setSerializer(RubeCustomProperty.class, new RubeCustomPropertySerializer());
        json.setSerializer(World.class, new RubePhysicsWorldSerializer(scene, parameters, json));
        json.setSerializer(Vector2.class, new Vector2Serializer());
        json.setSerializer(Vector2[].class, new Vector2ArraySerializer());
    }

    @Override
    public RubeScene read(Json json, JsonValue jsonData, Class type)
    {
        if (parameters.physicsWorld == null) {
            parameters.physicsWorld = json.readValue(World.class, jsonData);
        }

        // We don't need to handle these as the serializers build the scene on the fly
        //
        json.readValue("body", Array.class, RubeRigidBody.class, jsonData);
        json.readValue("joint", Array.class, RubeJoint.class, jsonData);
        json.readValue("image", Array.class, RubeTexture.class, jsonData);

        return scene;
    }
}