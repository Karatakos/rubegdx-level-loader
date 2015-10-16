package com.anogaijin.rubeloaderlite.serializers.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;

/**
 * Created by adunne on 2015/10/12.
 */
public class DistanceJointDefSerializer extends JointDefSerializer<DistanceJointDef> {
    public DistanceJointDefSerializer(RubeScene scene) {
        super(scene);
    }

    @Override
    public DistanceJointDef read(Json json, JsonValue jsonData, Class type) {
        DistanceJointDef concreteJointdef = readGenericJointDef(json, jsonData, type);

        concreteJointdef.localAnchorA.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorA, jsonData));
        concreteJointdef.localAnchorB.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorB, jsonData));

        concreteJointdef.collideConnected = json.readValue("collideConnected", boolean.class, concreteJointdef.collideConnected, jsonData);
        concreteJointdef.dampingRatio = json.readValue("dampingRatio", float.class, concreteJointdef.dampingRatio, jsonData);
        concreteJointdef.frequencyHz = json.readValue("frequency", float.class, concreteJointdef.frequencyHz, jsonData);
        concreteJointdef.length = json.readValue("length", float.class, concreteJointdef.length, jsonData);

        return concreteJointdef;
    }
}
