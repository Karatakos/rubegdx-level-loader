package com.anogaijin.rubeloaderlite.serializers;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.*;
import com.badlogic.gdx.utils.JsonValue;
import com.anogaijin.rubeloaderlite.containers.RubeCustomProperty;

/**
 * Created by adunne on 2015/10/15.
 */
public class RubeCustomPropertySerializer extends ReadOnlySerializer<RubeCustomProperty> {
    @Override
    public RubeCustomProperty read(Json json, JsonValue jsonData, Class type) {
        return new RubeCustomProperty(
                jsonData.child().asString(),
                jsonData.child().next().asString());
    }
}
