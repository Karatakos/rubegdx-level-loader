package com.anogaijin.rubeloaderlite.serializers.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;

/**
 * Created by adunne on 2015/10/12.
 */
public class WheelJointDefSerializer extends JointDefSerializer<WheelJointDef> {
    public WheelJointDefSerializer(RubeScene scene) {
        super(scene);
    }

    @Override
    public WheelJointDef read(Json json, JsonValue jsonData, Class type) {
        WheelJointDef concreteJointdef = readGenericJointDef(json, jsonData, type);

        concreteJointdef.localAnchorA.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorA, jsonData));
        concreteJointdef.localAnchorB.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorB, jsonData));

        concreteJointdef.localAxisA.set(json.readValue("localAxisA", Vector2.class, concreteJointdef.localAxisA, jsonData));
        concreteJointdef.enableMotor = json.readValue("enableMotor", boolean.class, concreteJointdef.enableMotor, jsonData);
        concreteJointdef.motorSpeed = json.readValue("motorSpeed", float.class, concreteJointdef.motorSpeed, jsonData);
        concreteJointdef.maxMotorTorque = json.readValue("maxMotorTorque", float.class, concreteJointdef.maxMotorTorque, jsonData);
        concreteJointdef.frequencyHz = json.readValue("springFrequency", float.class, concreteJointdef.frequencyHz, jsonData);
        concreteJointdef.dampingRatio = json.readValue("springDampingRatio", float.class, concreteJointdef.dampingRatio, jsonData);

        return concreteJointdef;
    }
}
