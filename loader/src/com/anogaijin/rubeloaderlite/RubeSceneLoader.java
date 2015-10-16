package com.anogaijin.rubeloaderlite;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.anogaijin.rubeloaderlite.serializers.RubeSceneSerializer;

public class RubeSceneLoader extends AsynchronousAssetLoader<RubeScene, RubeSceneLoader.RubeSceneParameters>
{
	private RubeScene scene;
	private Json json;

	static public class RubeSceneParameters extends AssetLoaderParameters<RubeScene> {
		public String markerProperty = "marker";			// Marker identifier (value should represent type of marker, e.g. enemy, lever, etc.)
		public String markerTypeIgnore = "none";			// This should be a default value in RUBE which means "I'm not a marker!"
		public String texturePackFileExtension = "pack";	// By default we look for pack files
		public World physicsWorld;							// If not present we will generate based on the scene data
	}

	public RubeSceneLoader(FileHandleResolver resolver) {
		super(resolver);

		json = new Json();
		json.setTypeName(null);
		json.setUsePrototypes(false);
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, RubeSceneParameters parameters)
	{
		if (parameters == null)
			parameters = new RubeSceneParameters();

		FileHandle[] files = file.parent().list(parameters.texturePackFileExtension);
		if (files == null)
			return null;

		Array<AssetDescriptor> dependencies = new Array<>();
		for (FileHandle packFile : files)
			dependencies.add(new AssetDescriptor<>(packFile.path(), TextureAtlas.class));

		return dependencies;
	}

	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file, RubeSceneParameters parameters) {
		try
		{
			if (parameters == null)
				parameters = new RubeSceneParameters();

			json.setSerializer(RubeScene.class, new RubeSceneSerializer(parameters, manager, json));

			// Go!
			//
			scene = json.fromJson(RubeScene.class, file);
			scene.setWorld(parameters.physicsWorld);
		}
		catch (SerializationException exception)
		{
			throw new SerializationException("Error reading file: " + file, exception);
		}
	}

	@Override
	public RubeScene loadSync(AssetManager manager, String fileName, FileHandle file, RubeSceneParameters parameter)
	{
		return scene;
	}
}
