package com.anogaijin.rubeloaderlite.serializers.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;

/**
 * Created by adunne on 2015/10/12.
 */
public class FrictionJointDefSerializer extends JointDefSerializer<FrictionJointDef> {
    public FrictionJointDefSerializer(RubeScene scene) {
        super(scene);
    }

    @Override
    public FrictionJointDef read(Json json, JsonValue jsonData, Class type) {
        FrictionJointDef concreteJointdef = readGenericJointDef(json, jsonData, type);

        concreteJointdef.localAnchorA.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorA, jsonData));
        concreteJointdef.localAnchorB.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorB, jsonData));

        concreteJointdef.maxForce = json.readValue("maxForce", float.class, concreteJointdef.maxForce, jsonData);
        concreteJointdef.maxTorque = json.readValue("maxTorque", float.class, concreteJointdef.maxTorque, jsonData);

        return concreteJointdef;
    }
}
