package com.anogaijin.rubeloaderlite.serializers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.*;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;
import com.anogaijin.rubeloaderlite.RubeSceneLoader.*;

/**
 * Created by adunne on 2015/10/14.
 */
public class RubePhysicsWorldSerializer extends ReadOnlySerializer<World> {
    public RubePhysicsWorldSerializer(RubeScene scene, RubeSceneParameters parameters, Json json) {
    }

    @Override
    public World read(Json json, JsonValue jsonData, Class type) {
        World world = new World(
                json.readValue("gravity", Vector2.class, new Vector2(0.0f, -9.81f), jsonData),
                json.readValue("allowSleep", boolean.class, true, jsonData));

        world.setWarmStarting(json.readValue("warmStarting", boolean.class, true, jsonData));
        world.setContinuousPhysics(json.readValue("continuousPhysics", boolean.class, true, jsonData));
        world.setAutoClearForces(json.readValue("autoClearForces", boolean.class, world.getAutoClearForces(), jsonData));

        return world;
    }
}
