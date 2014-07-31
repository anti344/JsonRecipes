/*
 * File IRecipeHandler.java is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.api;

public interface IRecipeHandler<T>{

    /**
      * Executed for every recipe with type registered with this handler.
      *
      * @param recipe Instance of your class-holder, parsed from Json using Google Gson library.
      * @return Success of adding recipe. If <code>false</code> returned chat error will occur(if enabled).
      *
      * @see IRecipeRegistry
      */
    boolean addRecipe(T recipe);

    /**
      * Instance of your class-holder is the same as in {@link #addRecipe}, so you can map it with actual recipe to remove it from recipe-list.
      * See my minecraft implementation here:
      * https://github.com/anti344/JsonRecipes/tree/master/src/main/scala/net/anti344/jsonrecipes/plugins/minecraft/ShapelessHandler.scala#L37
      *
      * @param recipe Instance of your class-holder parsed from Json using Google Gson library.
      * @return Success of removing recipe. If <code>false</code> returned chat error will occur(if enabled).
      */
    boolean removeRecipe(T recipe);
}