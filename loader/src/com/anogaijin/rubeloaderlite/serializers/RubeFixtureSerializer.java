package com.anogaijin.rubeloaderlite.serializers;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.ReadOnlySerializer;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.anogaijin.rubeloaderlite.containers.RubeRigidBody;
import com.anogaijin.rubeloaderlite.serializers.shapes.CircleShapeSerializer;
import com.anogaijin.rubeloaderlite.serializers.shapes.EdgeShapeSerializer;
import com.anogaijin.rubeloaderlite.serializers.shapes.PolygonShapeSerializer;

/**
 * Created by adunne on 2015/10/12.
 */
public class RubeFixtureSerializer extends ReadOnlySerializer<Fixture>
{
    public RubeRigidBody rubeRigidBody;
    private ObjectMap<String, String> customPropertyMap;

    public RubeFixtureSerializer(Json json)
    {
        json.setSerializer(PolygonShape.class, new PolygonShapeSerializer());
        json.setSerializer(EdgeShape.class, new EdgeShapeSerializer());
        json.setSerializer(CircleShape.class, new CircleShapeSerializer());
    }

    @Override
    public Fixture read(Json json, JsonValue jsonData, Class type)
    {
        if(rubeRigidBody.rigidBody == null)
            return null;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = json.readValue("friction", float.class, fixtureDef.friction, jsonData);
        fixtureDef.density = json.readValue("density", float.class, fixtureDef.density, jsonData);
        fixtureDef.restitution = json.readValue("restitution", float.class, fixtureDef.restitution, jsonData);
        fixtureDef.isSensor = json.readValue("sensor", boolean.class, fixtureDef.isSensor, jsonData);
        fixtureDef.filter.maskBits = json.readValue("filter-maskBits", short.class, fixtureDef.filter.maskBits, jsonData);
        fixtureDef.filter.categoryBits = json.readValue("filter-categoryBits", short.class, fixtureDef.filter.categoryBits, jsonData);
        fixtureDef.filter.groupIndex = json.readValue("filter-groupIndex", short.class, fixtureDef.filter.groupIndex, jsonData);

        fixtureDef.shape = json.readValue("circle", CircleShape.class, jsonData);
        if (fixtureDef.shape == null)
            fixtureDef.shape = json.readValue("edge", EdgeShape.class, jsonData);

        if (fixtureDef.shape == null)
            fixtureDef.shape = json.readValue("polygon", PolygonShape.class, jsonData);

        if (fixtureDef.shape == null)
            return null;

        Fixture fixture = rubeRigidBody.rigidBody.createFixture(fixtureDef);
        fixtureDef.shape.dispose();

        return fixture;
    }

    public void setRigidBody(RubeRigidBody rigidBody) {
        rubeRigidBody = rigidBody;
    }
}

