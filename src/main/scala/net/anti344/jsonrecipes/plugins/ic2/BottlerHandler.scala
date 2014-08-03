/*
 * File BottlerHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.ic2

import net.anti344.jsonrecipes.plugins.RecipeHandler
import net.anti344.jsonrecipes.impl.JsonItemStack
import scala.collection.mutable.{Map => MMap}
import ic2.api.recipe._

object BottlerHandler
 extends RecipeHandler[JsonBottlerRecipe]{

  val recipes: MMap[JsonBottlerRecipe, ICannerBottleRecipeManager.Input] = MMap()

  def addRecipe(recipe: JsonBottlerRecipe): Boolean = {
    val in = new ICannerBottleRecipeManager.Input(recipe.input, recipe.fill)
    if(in.container != null && in.fill != null && recipe.output.exists){
      Recipes.cannerBottle.getRecipes.put(in, new RecipeOutput(null, recipe.output.getItemStack))
      recipes(recipe) = in
      true
    }
    else
      false
  }

  def removeRecipe(recipe: JsonBottlerRecipe): Boolean =
    recipes.remove(recipe) match{
      case Some(in) =>
        Recipes.cannerBottle.getRecipes.remove(in)
        true
      case _ =>
        false
    }
}

case class JsonBottlerRecipe(input: JsonItemStack, fill: JsonItemStack, output: JsonItemStack)