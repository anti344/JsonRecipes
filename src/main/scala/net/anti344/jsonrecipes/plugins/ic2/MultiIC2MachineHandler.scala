/*
 * File MultiIC2MachineHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.ic2

import net.minecraft.nbt.NBTTagCompound
import scala.collection.JavaConversions.asScalaBuffer
import net.anti344.jsonrecipes.plugins.RecipeHandler
import net.anti344.jsonrecipes.impl.JsonItemStack
import scala.collection.mutable.{Map => MMap}
import java.util.{List => JList}
import ic2.api.recipe._

class MultiIC2MachineHandler[T <: MultiIC2MachineRecipe](machine: IMachineRecipeManager)
 extends RecipeHandler[T]{

  val recipes: MMap[T, IRecipeInput] = MMap()

  def getMeta(recipe: T): NBTTagCompound =
   null

  def addRecipe(recipe: T): Boolean = {
    val in = convert(recipe.input)
    if(in != null && recipe.output.count(o => !o.exists) == 0){
      machine.addRecipe(in, getMeta(recipe), recipe.output.map(_.getItemStack):_*)
      recipes(recipe) = in
      true
    }else
      false
  }

  def removeRecipe(recipe: T): Boolean =
    recipes.remove(recipe) match{
      case Some(in) =>
        machine.getRecipes.remove(in)
        true
      case _ =>
        false
    }
}

trait MultiIC2MachineRecipe{

  def input: JsonItemStack

  def output: JList[JsonItemStack]
}

case class JsonMultiIC2MachineRecipe(input: JsonItemStack, output: JList[JsonItemStack])
 extends MultiIC2MachineRecipe