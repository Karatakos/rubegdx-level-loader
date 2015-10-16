package com.anogaijin.rubeloaderlite.serializers.shapes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by adunne on 2015/10/12.
 */
public class CircleShapeSerializer extends Json.ReadOnlySerializer<CircleShape>
{
    @Override
    public CircleShape read(Json json, JsonValue jsonData, Class type)
    {
        CircleShape shape;

        Vector2 position = json.readValue("center", Vector2.class, jsonData);
        float	radius	 = json.readValue("radius", float.class, jsonData);

        shape = new CircleShape();
        shape.setRadius(radius);
        shape.setPosition(position);

        return shape;
    }
}
