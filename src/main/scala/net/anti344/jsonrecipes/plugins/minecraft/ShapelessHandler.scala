/*
 * File ShapelessHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.minecraft

import net.anti344.jsonrecipes.api.IRecipeHandler
import net.anti344.jsonrecipes.impl.JsonItemStack
import net.minecraft.item.crafting.{CraftingManager, IRecipe}
import net.minecraftforge.oredict.ShapelessOreRecipe
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.{Map => MMap}
import java.util.{List => JList}

object ShapelessHandler
 extends IRecipeHandler[JsonShapelessRecipe]{

  val recipeMap: MMap[JsonShapelessRecipe, ShapelessOreRecipe] = MMap()

  private val craftingRecipes: JList[IRecipe] =
    CraftingManager.getInstance.getRecipeList.asInstanceOf[JList[IRecipe]]

  def addRecipe(recipe: JsonShapelessRecipe): Boolean =
    if(recipe.output != null && recipe.input != null && recipe.output.exists){
      val in = recipe.input.flatMap(i => Seq.fill(i.getCount)(i.copy(count = 1).getComponent))
      val _recipe = new ShapelessOreRecipe(recipe.output.getItemStack, in:_*)
      craftingRecipes.add(_recipe)
      recipeMap(recipe) = _recipe
      true
    }else
      false

  def removeRecipe(recipe: JsonShapelessRecipe): Boolean =
    recipeMap.remove(recipe) match{
      case Some(e) =>
        craftingRecipes.remove(e)
        true
      case _ =>
        false
    }
}

case class JsonShapelessRecipe(input: JList[JsonItemStack], output: JsonItemStack)