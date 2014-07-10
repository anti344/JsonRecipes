/*
 * File FuelHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.minecraft

import cpw.mods.fml.common.IFuelHandler
import cpw.mods.fml.common.registry.GameRegistry
import net.anti344.jsonrecipes.api.IRecipeHandler
import net.anti344.jsonrecipes.impl.JsonItemStack
import net.minecraft.item.ItemStack

import scala.collection.mutable.ArrayBuffer

object FuelHandler
 extends IRecipeHandler[JsonFuelRecipe]
 with IFuelHandler{

  GameRegistry.registerFuelHandler(this)

  val recipes: ArrayBuffer[JsonFuelRecipe] = ArrayBuffer()

  def getBurnTime(fuel: ItemStack): Int =
    recipes.find(e => fuel.isItemEqual(e.fuel.getItemStack)) match{
      case Some(e) => e.duration
      case _ => 0
    }

  def addRecipe(recipe: JsonFuelRecipe): Boolean =
    if(recipe.fuel != null && recipe.fuel.exists){
      recipes += recipe
      true
    }else
      false

  def removeRecipe(recipe: JsonFuelRecipe): Boolean =
    if(recipes.contains(recipe)){
      recipes -= recipe
      true
    }else
      false
}

case class JsonFuelRecipe(fuel: JsonItemStack, duration: Int = 1600)