/*
 * File MinecraftPlugin.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.minecraft

import net.anti344.jsonrecipes.impl.RecipeRegistry.{doRegisterRecipeType => register}
import net.anti344.jsonrecipes.plugins.Plugin

object MinecraftPlugin
 extends Plugin{
  register("shaped", classOf[JsonShapedRecipe], ShapedHandler)
  register("shapeless", classOf[JsonShapelessRecipe], ShapelessHandler)
  register("furnace", classOf[JsonFurnaceRecipe], FurnaceHandler)
  register("fuel", classOf[JsonFuelRecipe], FuelHandler)
}