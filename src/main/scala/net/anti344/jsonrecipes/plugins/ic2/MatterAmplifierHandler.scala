/*
 * File MatterAmplifierHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.ic2

import net.anti344.jsonrecipes.api.IRecipeHandler
import net.anti344.jsonrecipes.impl.JsonItemStack
import scala.collection.mutable.{Map => MMap}
import ic2.api.recipe.{Recipes, IRecipeInput}

object MatterAmplifierHandler
 extends IRecipeHandler[JsonMatterAmplifierRecipe]{

  val recipes: MMap[JsonMatterAmplifierRecipe, IRecipeInput] = MMap()

  def addRecipe(recipe: JsonMatterAmplifierRecipe): Boolean = {
    val in = convert(recipe.item)
    if(in != null && recipe.amplifier > 0){
      Recipes.matterAmplifier.addRecipe(in, "amplification" -> recipe.amplifier)
      recipes(recipe) = in
      true
    }else
      false
  }

  def removeRecipe(recipe: JsonMatterAmplifierRecipe): Boolean = {
    false
  }
}

case class JsonMatterAmplifierRecipe(item: JsonItemStack, amplifier: Int)