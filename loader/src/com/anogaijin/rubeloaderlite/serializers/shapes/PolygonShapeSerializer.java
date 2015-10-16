package com.anogaijin.rubeloaderlite.serializers.shapes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.ReadOnlySerializer;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by adunne on 2015/10/12.
 */
public class PolygonShapeSerializer extends ReadOnlySerializer<PolygonShape>
{
    @Override
    public PolygonShape read(Json json, JsonValue jsonData, Class type)
    {
        Vector2[] vertices = json.readValue("vertices", Vector2[].class, jsonData);

        // Limited Box2d support for polygon # vertices
        //
        if(vertices.length <= 2 || vertices.length > 8)
            return null;

        PolygonShape shape = new PolygonShape();
        shape.set(vertices);

        return shape;
    }
}
