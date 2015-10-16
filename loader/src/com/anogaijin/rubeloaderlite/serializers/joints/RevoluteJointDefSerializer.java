package com.anogaijin.rubeloaderlite.serializers.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;

/**
 * Created by adunne on 2015/10/12.
 */
public class RevoluteJointDefSerializer extends JointDefSerializer<RevoluteJointDef> {
    public RevoluteJointDefSerializer(RubeScene scene) {
        super(scene);
    }

    @Override
    public RevoluteJointDef read(Json json, JsonValue jsonData, Class type) {
        RevoluteJointDef concreteJointdef = readGenericJointDef(json, jsonData, type);

        concreteJointdef.localAnchorA.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorA, jsonData));
        concreteJointdef.localAnchorB.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorB, jsonData));

        concreteJointdef.collideConnected = json.readValue("collideConnected", boolean.class, concreteJointdef.collideConnected, jsonData);
        concreteJointdef.enableLimit = json.readValue("enableLimit", boolean.class, concreteJointdef.enableLimit, jsonData);
        concreteJointdef.enableMotor = json.readValue("enableMotor", boolean.class, concreteJointdef.enableMotor, jsonData);
        concreteJointdef.motorSpeed = json.readValue("motorSpeed", float.class, concreteJointdef.motorSpeed, jsonData);
        concreteJointdef.maxMotorTorque = json.readValue("maxMotorTorque", float.class, concreteJointdef.maxMotorTorque, jsonData);
        concreteJointdef.referenceAngle = json.readValue("refAngle", float.class, concreteJointdef.referenceAngle, jsonData);
        concreteJointdef.lowerAngle = json.readValue("lowerLimit", float.class, concreteJointdef.lowerAngle, jsonData);
        concreteJointdef.upperAngle = json.readValue("upperLimit", float.class, concreteJointdef.upperAngle, jsonData);

        return concreteJointdef;
    }
}
