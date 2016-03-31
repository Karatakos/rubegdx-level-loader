package com.anogaijin.rubeloaderlite.serializers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by adunne on 2015/10/12.
 */
public class Vector2ArraySerializer extends Json.ReadOnlySerializer<Vector2[]> {
    @Override
    public Vector2[] read(Json json, JsonValue jsonData, Class type) {
        float[] x = json.readValue("x", float[].class, jsonData);
        float[] y = json.readValue("y", float[].class, jsonData);

        Vector2[] vertices = new Vector2[x.length];
        for (int i=0; i<vertices.length; i++)
            vertices[i] = new Vector2(x[i], y[i]);

        return vertices;
    }
}
