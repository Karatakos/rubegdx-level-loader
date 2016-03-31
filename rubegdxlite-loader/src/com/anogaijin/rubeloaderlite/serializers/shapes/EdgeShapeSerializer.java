package com.anogaijin.rubeloaderlite.serializers.shapes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by adunne on 2015/10/12.
 */
public class EdgeShapeSerializer extends Json.ReadOnlySerializer<EdgeShape>
{
    @Override
    public EdgeShape read(Json json, JsonValue jsonData, Class type)
    {
        EdgeShape shape;

        Vector2 vertex1 = json.readValue("vertex1", Vector2.class, jsonData);
        Vector2 vertex2 = json.readValue("vertex2", Vector2.class, jsonData);

        shape = new EdgeShape();
        shape.set(vertex1, vertex2);

        return shape;
    }
}
