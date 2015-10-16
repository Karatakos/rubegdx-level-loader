package com.anogaijin.rubeloaderlite.serializers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by adunne on 2015/10/12.
 */
public class Vector2Serializer extends Json.ReadOnlySerializer<Vector2> {
    @Override
    public Vector2 read(Json json, JsonValue jsonData, Class type) {
        Vector2 vector = new Vector2();

        vector.x = json.readValue("x", float.class, 0.0f, jsonData);
        vector.y = json.readValue("y", float.class, 0.0f, jsonData);

        return vector;
    }
}
