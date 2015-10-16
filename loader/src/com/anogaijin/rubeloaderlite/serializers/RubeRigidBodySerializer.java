package com.anogaijin.rubeloaderlite.serializers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.ReadOnlySerializer;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.RubeScene;
import com.anogaijin.rubeloaderlite.RubeSceneLoader.*;
import com.anogaijin.rubeloaderlite.containers.RubeCustomProperty;
import com.anogaijin.rubeloaderlite.containers.RubeMarker;
import com.anogaijin.rubeloaderlite.containers.RubeRigidBody;

/**
 * Created by adunne on 2015/10/12.
 */
public class RubeRigidBodySerializer extends ReadOnlySerializer<RubeRigidBody>
{
    private RubeScene scene;
    private RubeSceneParameters parameters;
    private RubeFixtureSerializer fixtureSerializer;

    private int bodyCount;

    public RubeRigidBodySerializer(RubeScene scene, RubeSceneParameters parameters, Json json)
    {
        this.bodyCount = 0;
        this.scene = scene;
        this.parameters = parameters;

        json.setSerializer(Fixture.class, fixtureSerializer = new RubeFixtureSerializer(json));
    }

    @Override
    public RubeRigidBody read(Json json, JsonValue jsonData, Class type) {
        RubeRigidBody rubeRigidBody = new RubeRigidBody();
        rubeRigidBody.setCustomProperties(json.readValue("customProperties", RubeCustomProperty[].class, RubeCustomProperty.class, jsonData));

        // Quick look-up for joints and images.
        // Note: RUBE works on indexes, so this always needs to match our RUBE scene, even if we don't retain this body
        //
        scene.getBodyMap().put(String.valueOf(bodyCount++), rubeRigidBody);

        // Fudging markers in here as RUBE does not yet support them
        //
        if (rubeRigidBody.getCustomProperties() != null && rubeRigidBody.getCustomProperties().containsKey(parameters.markerProperty)) {
            // E.G. if marker type is "none" we know to ignore (markerTypeIgnore)
            //
            if (!rubeRigidBody.getCustomProperties().get(parameters.markerProperty).equals(parameters.markerTypeIgnore)) {
                RubeMarker marker = new RubeMarker(
                        json.readValue("position", Vector2.class, jsonData),
                        rubeRigidBody.getCustomProperties().get(parameters.markerProperty));

                scene.getAllMarkers().add(marker);

                // We don't need to generate a body for markers
                //
                return null;
            }
        }

        BodyDef rigidBodyDef = new BodyDef();
        rigidBodyDef.type = BodyType.values()[json.readValue("type", int.class, rigidBodyDef.type.getValue(), jsonData)];

        rigidBodyDef.position.set(json.readValue("position", Vector2.class, rigidBodyDef.position, jsonData));
        rigidBodyDef.linearVelocity.set(json.readValue("linearVelocity", Vector2.class, rigidBodyDef.linearVelocity, jsonData));

        rigidBodyDef.angle = json.readValue("angle", float.class, rigidBodyDef.angle, jsonData);
        rigidBodyDef.angularVelocity = json.readValue("angularVelocity", float.class, rigidBodyDef.angularVelocity, jsonData);
        rigidBodyDef.linearDamping = json.readValue("linearDamping", float.class, rigidBodyDef.linearDamping, jsonData);
        rigidBodyDef.angularDamping = json.readValue("angularDamping", float.class, rigidBodyDef.angularDamping, jsonData);
        rigidBodyDef.gravityScale = json.readValue("gravityScale", float.class, rigidBodyDef.gravityScale, jsonData);

        rigidBodyDef.allowSleep = json.readValue("allowSleep", boolean.class, rigidBodyDef.allowSleep, jsonData);
        rigidBodyDef.awake = json.readValue("awake", boolean.class, rigidBodyDef.awake, jsonData);
        rigidBodyDef.fixedRotation = json.readValue("fixedRotation", boolean.class, rigidBodyDef.fixedRotation, jsonData);
        rigidBodyDef.bullet = json.readValue("bullet", boolean.class, rigidBodyDef.bullet, jsonData);
        rigidBodyDef.active = json.readValue("active", boolean.class, rigidBodyDef.active, jsonData);

        rubeRigidBody.rigidBody = parameters.physicsWorld.createBody(rigidBodyDef);

        if (rigidBodyDef.type == BodyType.DynamicBody) {
            MassData massData = new MassData();

            massData.center.set(json.readValue("massData-center", Vector2.class, massData.center, jsonData));
            massData.mass = json.readValue("massData-mass", float.class, 0.0f, jsonData);
            massData.I = json.readValue("massData-I", float.class, 0.0f, jsonData);

            if (massData.mass != 0.0f || massData.I != 0.0f || massData.center.x != 0.0f || massData.center.y != 0.0f)
                rubeRigidBody.rigidBody.setMassData(massData);
        }

        fixtureSerializer.setRigidBody(rubeRigidBody);
        json.readValue("fixture", Array.class, Fixture.class, jsonData);

        // Takes care of loading itself into the scene
        //
        scene.getAllRigidBodies().add(rubeRigidBody);

        return rubeRigidBody;
    }
}