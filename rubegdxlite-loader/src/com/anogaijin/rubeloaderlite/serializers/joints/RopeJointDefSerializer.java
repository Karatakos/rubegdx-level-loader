package com.anogaijin.rubeloaderlite.serializers.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;

/**
 * Created by adunne on 2015/10/12.
 */
public class RopeJointDefSerializer extends JointDefSerializer<RopeJointDef> {
    public RopeJointDefSerializer(RubeScene scene) {
        super(scene);
    }

    @Override
    public RopeJointDef read(Json json, JsonValue jsonData, Class type) {
        RopeJointDef concreteJointdef = readGenericJointDef(json, jsonData, type);

        concreteJointdef.localAnchorA.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorA, jsonData));
        concreteJointdef.localAnchorB.set(json.readValue("bodyAnchorA", Vector2.class, concreteJointdef.localAnchorB, jsonData));

        concreteJointdef.maxLength = json.readValue("maxLength", float.class, concreteJointdef.maxLength, jsonData);

        return concreteJointdef;
    }
}
