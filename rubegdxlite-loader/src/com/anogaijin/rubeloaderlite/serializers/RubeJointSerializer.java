package com.anogaijin.rubeloaderlite.serializers;

import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.*;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.ReadOnlySerializer;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;
import com.anogaijin.rubeloaderlite.RubeSceneLoader.*;
import com.anogaijin.rubeloaderlite.containers.RubeCustomProperty;
import com.anogaijin.rubeloaderlite.containers.RubeJoint;
import com.anogaijin.rubeloaderlite.serializers.joints.*;


/**
 * Created by adunne on 2015/10/12.
 */
public class RubeJointSerializer extends ReadOnlySerializer<RubeJoint> {
    private RubeScene scene;
    private RubeSceneParameters parameters;

    public RubeJointSerializer(RubeScene scene, RubeSceneParameters parameters, Json json) {
        this.scene = scene;
        this.parameters = parameters;

        json.setSerializer(RevoluteJointDef.class, new RevoluteJointDefSerializer(scene));
        json.setSerializer(DistanceJointDef.class, new DistanceJointDefSerializer(scene));
        json.setSerializer(PrismaticJointDef.class, new PrismaticJointDefSerializer(scene));
        json.setSerializer(WheelJointDef.class, new WheelJointDefSerializer(scene));
        json.setSerializer(RopeJointDef.class, new RopeJointDefSerializer(scene));
        json.setSerializer(WeldJointDef.class, new WeldJointDefSerializer(scene));
        json.setSerializer(FrictionJointDef.class, new FrictionJointDefSerializer(scene));
    }

    @Override
    public RubeJoint read(Json json, JsonValue jsonData, Class type) {
        JointDef jointDef;
        switch (json.readValue("type", String.class, jsonData)) {
            case "revolute":
                jointDef = json.readValue(RevoluteJointDef.class, jsonData);
                break;
            case "distance":
                jointDef = json.readValue(DistanceJointDef.class, jsonData);
                break;
            case "prismatic":
                jointDef = json.readValue(PrismaticJointDef.class, jsonData);
                break;
            case "wheel":
                jointDef = json.readValue(WheelJointDef.class, jsonData);
                break;
            case "rope":
                jointDef = json.readValue(RopeJointDef.class, jsonData);
                break;
            case "weld":
                jointDef = json.readValue(WeldJointDef.class, jsonData);
                break;
            case "friction":
                jointDef = json.readValue(FrictionJointDef.class, jsonData);
                break;

            default:
                return null;
        }

        RubeJoint rubeJoint = new RubeJoint();
        rubeJoint.joint = parameters.physicsWorld.createJoint(jointDef);
        rubeJoint.setCustomProperties(json.readValue("customProperties", RubeCustomProperty[].class, RubeCustomProperty.class, jsonData));

        // Register itself in the scene
        //
        scene.getAllJoints().add(rubeJoint);

        return rubeJoint;
    }
}

