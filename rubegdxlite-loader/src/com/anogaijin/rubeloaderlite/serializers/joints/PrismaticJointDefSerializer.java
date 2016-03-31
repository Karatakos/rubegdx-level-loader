package com.anogaijin.rubeloaderlite.serializers.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;

/**
 * Created by adunne on 2015/10/12.
 */
public class PrismaticJointDefSerializer extends JointDefSerializer<PrismaticJointDef> {
    public PrismaticJointDefSerializer(RubeScene scene) {
        super(scene);
    }

    @Override
    public PrismaticJointDef read(Json json, JsonValue jsonData, Class type) {
        PrismaticJointDef concreteJointdef = readGenericJointDef(json, jsonData, type);

        concreteJointdef.localAnchorA.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorA, jsonData));
        concreteJointdef.localAnchorB.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorB, jsonData));

        Vector2 localAxis = json.readValue("localAxisA", Vector2.class, jsonData);
        if (localAxis == null)
            localAxis = json.readValue("localAxis1", Vector2.class, jsonData);

        if (localAxis != null)
            concreteJointdef.localAxisA.set(localAxis);

        concreteJointdef.collideConnected = json.readValue("collideConnected", boolean.class, concreteJointdef.collideConnected, jsonData);
        concreteJointdef.referenceAngle = json.readValue("refAngle", float.class, concreteJointdef.referenceAngle, jsonData);
        concreteJointdef.enableLimit = json.readValue("enableLimit", boolean.class, concreteJointdef.enableLimit, jsonData);
        concreteJointdef.lowerTranslation = json.readValue("lowerLimit", float.class, concreteJointdef.lowerTranslation, jsonData);
        concreteJointdef.upperTranslation = json.readValue("upperLimit", float.class, concreteJointdef.upperTranslation, jsonData);
        concreteJointdef.enableMotor = json.readValue("enableMotor", boolean.class, concreteJointdef.enableMotor, jsonData);
        concreteJointdef.motorSpeed = json.readValue("motorSpeed", float.class, concreteJointdef.motorSpeed, jsonData);
        concreteJointdef.maxMotorForce = json.readValue("maxMotorForce", float.class, concreteJointdef.maxMotorForce, jsonData);

        return concreteJointdef;
    }
}
