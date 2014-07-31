/*
 * File ShapedHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.minecraft

import net.anti344.jsonrecipes.api.IRecipeHandler
import net.anti344.jsonrecipes.impl.JsonItemStack
import net.minecraft.item.crafting.{IRecipe, CraftingManager}
import net.minecraftforge.oredict.ShapedOreRecipe
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.mutable.{Map => MMap}
import java.util.{HashMap => JMap, List => JList}

object ShapedHandler
 extends IRecipeHandler[JsonShapedRecipe]{

  val recipeMap: MMap[JsonShapedRecipe, ShapedOreRecipe] = MMap()

  private val craftingRecipes: JList[IRecipe] =
    CraftingManager.getInstance.getRecipeList.asInstanceOf[JList[IRecipe]]

  def isDynamic: Boolean =
    true

  def addRecipe(recipe: JsonShapedRecipe): Boolean =
    if(recipe.shape != null && recipe.input != null && recipe.output != null
       && recipe.shape.size <= 3 && recipe.shape.size > 0
       && recipe.input.keys.count(_.length != 1) == 0 && recipe.output.exists){
      val in = recipe.shape ++ recipe.input.flatMap(e => Seq(Char.box(e._1(0)), e._2.getComponent))
      val _recipe = new ShapedOreRecipe(recipe.output.getItemStack, in:_*)
      craftingRecipes.add(_recipe)
      recipeMap(recipe) = _recipe
      true
    }else
      false

  def removeRecipe(recipe: JsonShapedRecipe): Boolean =
    recipeMap.remove(recipe) match{
      case Some(r) =>
        craftingRecipes.remove(r)
        true
      case _ =>
        false
    }
}

case class JsonShapedRecipe(shape: JList[String], input: JMap[String, JsonItemStack], output: JsonItemStack)