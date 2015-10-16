package com.anogaijin.rubeloaderlite.serializers.joints;

import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;

/**
 * Created by adunne on 2015/10/12.
 */
public abstract class JointDefSerializer<T> extends Json.ReadOnlySerializer<T> {
    RubeScene scene;

    public JointDefSerializer(RubeScene scene) {
        this.scene = scene;
    }

    public T readGenericJointDef(Json json, JsonValue jsonData, Class type) {
        JointDef jointDef = new JointDef();

        jointDef.bodyA = scene.getBodyMap().get(json.readValue("bodyA", String.class, jsonData)).rigidBody;
        jointDef.bodyB = scene.getBodyMap().get(json.readValue("bodyB", String.class, jsonData)).rigidBody;

        return (T)jointDef;
    }
}
