/*
 * File JsonRecipesAPI.java is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.api;

import com.google.gson.Gson;

/**
  * Warning - the only interface you're allowed to implement - {@link IRecipeHandler}
  */
public class JsonRecipesAPI {

    /**
      * Main recipe registry.
      * Gets it instance during pre-initilization of Json Recipes.
      */
    public static IRecipeRegistry registry;

    /**
      * Main JSON deserializer.
      * Knows how to deserialize {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound},
      * {@link IJsonItemStack} and {@link IJsonFluidStack}.
      * Also instantiates them.
	  * Example: <code>
	  *
	  *     JsonRecipesAPI.deserializer.fromJson("...", MyJsonRecipe.class)
	  *
	  *     public static class MyJsonRecipe{
	  *
	  *     	public IJsonItemStack input;
	  *     	public IJsonFluidStack output;
	  *
	  *         public MyJsonRecipe(IJsonItemStacl input, IJsonFluidStack output){
	  *             this.in = input;
	  *             this.out = output;
	  *         }
	  *     }
	  * </code>
	  * The {@link IJsonItemStack} and {@link IJsonFluidStack} are interfaces, but this deserealizer
	  * returns instances of Json Recipes internal implementations of them.
	  * Another example: <code>
	  *     JsonRecipesAPI.deserializer.fromJson("{\"item\":\"axe_diamond\", \"count\":26}", JsonRecipesAPI.jsonItemStack)
	  * </code>
	  *
      * Gets it instance during pre-initilization of Json Recipes.
      */
    public static Gson deserializer;

    /**
      * A class of implementation of {@link IJsonItemStack} to get instance of it from deserialization.
      * Gets it instance during pre-initilization of Json Recipes.
      */
    public static Class<? extends IJsonItemStack> jsonItemStack;

    /**
      * A class of implementation of {@link IJsonFluidStack} to get instance of it from deserialization.
      * Gets it instance during pre-initilization of Json Recipes.
      */
    public static Class<? extends IJsonFluidStack> jsonFluidStack;
}