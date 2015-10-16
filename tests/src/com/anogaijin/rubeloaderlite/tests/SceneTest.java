package com.anogaijin.rubeloaderlite.tests;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.anogaijin.rubeloaderlite.RubeScene;
import com.anogaijin.rubeloaderlite.RubeSceneLoader;
import com.anogaijin.rubeloaderlite.containers.RubeTexture;

/**
 * Created by adunne on 2015/10/12.
 */
public class SceneTest extends ApplicationAdapter {
    SpriteBatch batch;
    AssetManager assetManager;
    Box2DDebugRenderer b2dr;

    public World physicsWorld;
    public OrthographicCamera mainCam;
    public Viewport viewPort;
    public RubeScene scene;

    public SceneTest() {
    }

    @Override
    public void create() {
        b2dr = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        mainCam = new OrthographicCamera();
        viewPort = new FitViewport(10f, 5.6f, mainCam);
        viewPort.apply(true);

        assetManager = new AssetManager();
        assetManager.setLoader(RubeScene.class, new RubeSceneLoader(new InternalFileHandleResolver()));
        assetManager.load("scenes/test/untitled1.json", RubeScene.class);
        assetManager.finishLoading();

        scene = assetManager.get("scenes/test/untitled1.json");
        physicsWorld = scene.getWorld();
    }

    @Override
    public void resize(int width, int height) {
        viewPort.setScreenSize(width, height);
        viewPort.apply();
    }

    @Override
    public void render() {
        physicsWorld.step(1/60f, 8, 3);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(mainCam.combined);
        batch.begin();

        for (RubeTexture rubeTexture : scene.getAllTextures()) {
            if (rubeTexture.texture != null && rubeTexture.rubeRigidBody != null)
                batch.draw(
                        rubeTexture.texture,
                        rubeTexture.rubeRigidBody.rigidBody.getPosition().x - rubeTexture.width/2
                                + rubeTexture.center.x,
                        rubeTexture.rubeRigidBody.rigidBody.getPosition().y - rubeTexture.height/2
                                + rubeTexture.center.y,
                        rubeTexture.width/2,
                        rubeTexture.height/2,
                        rubeTexture.width,
                        rubeTexture.height,
                        1,
                        1,
                        rubeTexture.rubeRigidBody.rigidBody.getAngle() * MathUtils.radDeg);
        }

        batch.end();

        //b2dr.render(physicsWorld, mainCam.combined);
    }
}

