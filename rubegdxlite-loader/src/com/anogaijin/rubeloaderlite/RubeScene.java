package com.anogaijin.rubeloaderlite;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.anogaijin.rubeloaderlite.containers.RubeJoint;
import com.anogaijin.rubeloaderlite.containers.RubeRigidBody;
import com.anogaijin.rubeloaderlite.containers.RubeTexture;
import com.anogaijin.rubeloaderlite.containers.RubeMarker;

/**
 * Created by adunne on 2015/10/12.
 */
public class RubeScene {
    private ObjectMap<String, RubeRigidBody> rigidBodyMap = new ObjectMap<>();

    private Array<RubeMarker> allMarkers;
    private Array<RubeRigidBody> allRigidBodies;
    private Array<RubeJoint> allJoints;
    private Array<RubeTexture> allTextures;

    private World world;

    public RubeScene() {
        // Required since in RUBE, bodies are referenced by index, and our array indexes
        // will be wrong since we're using bodies to represent markers, and we don't retain
        // those bodies once we generate the marker.
        //
        rigidBodyMap = new ObjectMap<>();

        allMarkers = new Array<>();
        allRigidBodies = new Array<>();
        allJoints = new Array<>();
        allTextures = new Array<>();
    }

    public ObjectMap<String, RubeRigidBody> getBodyMap() {
        return rigidBodyMap;
    }

    public Array<RubeRigidBody> getAllRigidBodies() {
        return allRigidBodies;
    }

    public Array<RubeJoint> getAllJoints() {
        return allJoints;
    }

    public Array<RubeTexture> getAllTextures() {
        return allTextures;
    }

    public Array<RubeMarker> getAllMarkers() {
        return allMarkers;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}
