/*
 * File EnricherHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.ic2

import net.anti344.jsonrecipes.impl.{JsonFluidStack, JsonItemStack}
import net.anti344.jsonrecipes.plugins.RecipeHandler
import scala.collection.mutable.{Map => MMap}
import ic2.api.recipe._

object EnricherHandler
 extends RecipeHandler[JsonEnricherRecipe]{

  val recipes: MMap[JsonEnricherRecipe, ICannerEnrichRecipeManager.Input] = MMap()

  def addRecipe(recipe: JsonEnricherRecipe): Boolean = {
    val in = new ICannerEnrichRecipeManager.Input(recipe.fluid.getFluidStack, recipe.add)
    if(in.fluid != null && in.additive != null && recipe.output.exists){
      Recipes.cannerEnrich.getRecipes.put(in, recipe.output.getFluidStack)
      recipes(recipe) = in
      true
    }
    else
      false
  }

  def removeRecipe(recipe: JsonEnricherRecipe): Boolean =
    recipes.remove(recipe) match{
      case Some(in) =>
        Recipes.cannerEnrich.getRecipes.remove(in)
        true
      case _ =>
        false
    }
}

case class JsonEnricherRecipe(fluid: JsonFluidStack, add: JsonItemStack, output: JsonFluidStack)