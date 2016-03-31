package com.anogaijin.rubeloaderlite.containers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by adunne on 2015/10/12.
 */
public class RubeTexture extends RubeObject {
    public TextureRegion texture;
    public RubeRigidBody rubeRigidBody;

    public Color color = new Color();
    public int filter = 0;
    public float opacity = 1.0f;
    public int renderOrder = 0;

    public Vector2 center = new Vector2();
    public float width = 0.0f;
    public float height = 0.0f;
    public float scale = 1.0f;
    public float rotation = 0.0f; // Radians

    public boolean flip = false;
}
