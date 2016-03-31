package com.anogaijin.rubeloaderlite.containers;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by adunne on 2015/10/12.
 */
public abstract class RubeObject {
    private ObjectMap<String, String> customProperties = new ObjectMap<>();

    public void setCustomProperties(RubeCustomProperty[] customProperties) {
        if (customProperties == null)
            return;

        for (RubeCustomProperty property : customProperties)
            this.customProperties.put(property.key, property.value);
    }

    public ObjectMap<String, String> getCustomProperties() {
        return customProperties;
    }
}
