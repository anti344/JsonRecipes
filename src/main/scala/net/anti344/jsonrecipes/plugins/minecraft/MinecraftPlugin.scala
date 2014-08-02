/*
 * File MinecraftPlugin.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.minecraft

import net.anti344.jsonrecipes.plugins.Plugin

object MinecraftPlugin
 extends Plugin{

  val modid: String = ""

  register[JsonShapedRecipe]("shaped", ShapedHandler)
  register[JsonShapelessRecipe]("shapeless", ShapelessHandler)
  register[JsonFurnaceRecipe]("furnace", FurnaceHandler)
  register[JsonFuelRecipe]("fuel", FuelHandler)
}