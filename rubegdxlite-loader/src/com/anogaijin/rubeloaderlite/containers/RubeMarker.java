package com.anogaijin.rubeloaderlite.containers;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by adunne on 2015/10/12.
 */
public class RubeMarker extends RubeObject {
    public RubeMarker(Vector2 position, String type) {
        this.position = position;
        this.type = type;
    }

    public Vector2 position;
    public String type;
}
