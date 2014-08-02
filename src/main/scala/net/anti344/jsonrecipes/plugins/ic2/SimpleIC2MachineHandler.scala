/*
 * File CompressorHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.ic2

import net.anti344.jsonrecipes.api.IRecipeHandler
import net.anti344.jsonrecipes.impl.JsonItemStack
import scala.collection.mutable.{Map => MMap}
import ic2.api.recipe._

class SimpleIC2MachineHandler(machine: IMachineRecipeManager)
 extends IRecipeHandler[JsonSimpleIC2MachineRecipe]{

  val recipes: MMap[JsonSimpleIC2MachineRecipe, IRecipeInput] = MMap()

  def addRecipe(recipe: JsonSimpleIC2MachineRecipe): Boolean = {
    val in =
      if(recipe.input.isOredict)
        new RecipeInputOreDict(recipe.input.getName, recipe.input.getCount, recipe.input.getMeta)
      else if(recipe.input.exists)
        new RecipeInputItemStack(recipe.input.getItemStack)
      else
        null
    if(in != null && recipe.output.exists){
      machine.addRecipe(in, null, recipe.output.getItemStack)
      recipes(recipe) = in
      true
    }else
      false
  }

  def removeRecipe(recipe: JsonSimpleIC2MachineRecipe): Boolean =
    recipes.remove(recipe) match{
      case Some(in) =>
        machine.getRecipes.remove(in)
        true
      case _ =>
        false
    }
}

case class JsonSimpleIC2MachineRecipe(input: JsonItemStack, output: JsonItemStack)