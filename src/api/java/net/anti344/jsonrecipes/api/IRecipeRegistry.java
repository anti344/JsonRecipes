/*
 * File IRecipeRegistry.java is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.api;

public interface IRecipeRegistry{

    /**
      * Registers a recipe handler to Custom Recipes parser(using ID of currently active mod).
      *
      * @see #registerRecipeType(String, Class, IRecipeHandler, String)
      */
    <T> void registerRecipeType(String type, Class<T> recipeCls, IRecipeHandler<T> handler);

    /**
      * Registers a recipe handler to CustomRecipes parser.
      *
      * @param type An identifier of your recipe. Syntax is <code>modid-type</code>.
      * @param recipeCls Class-holder of your recipe. Deserialized from JSON using Google Gson library.
      * @param handler Handler for your recipe.
      * @param modid ID of your mod. If <code>null</code> passed then ID of currently active mod'll be used.
      * @param <T> Type of class-holder and handler should be the same.
      */
    <T> void registerRecipeType(String type, Class<T> recipeCls, IRecipeHandler<T> handler, String modid);
}