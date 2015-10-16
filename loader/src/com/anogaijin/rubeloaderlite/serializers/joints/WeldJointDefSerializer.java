package com.anogaijin.rubeloaderlite.serializers.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;

/**
 * Created by adunne on 2015/10/12.
 */
public class WeldJointDefSerializer extends JointDefSerializer<WeldJointDef> {
    public WeldJointDefSerializer(RubeScene scene) {
        super(scene);
    }

    @Override
    public WeldJointDef read(Json json, JsonValue jsonData, Class type) {
        WeldJointDef concreteJointdef = readGenericJointDef(json, jsonData, type);

        concreteJointdef.localAnchorA.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorA, jsonData));
        concreteJointdef.localAnchorB.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorB, jsonData));

        concreteJointdef.referenceAngle = json.readValue("refAngle", float.class, concreteJointdef.referenceAngle, jsonData);

        return concreteJointdef;
    }
}
