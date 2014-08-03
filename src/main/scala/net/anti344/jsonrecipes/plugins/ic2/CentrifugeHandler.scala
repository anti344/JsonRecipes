/*
 * File CentrifugeHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.ic2

import net.anti344.jsonrecipes.impl.JsonItemStack
import net.minecraft.nbt.NBTTagCompound
import java.util.{List => JList}
import ic2.api.recipe.Recipes

object CentrifugeHandler
 extends MultiIC2MachineHandler[JsonCentrifugeRecipe](Recipes.centrifuge){

  override def getMeta(recipe: JsonCentrifugeRecipe): NBTTagCompound =
    "minHeat" -> recipe.minHeat
}

case class JsonCentrifugeRecipe(input: JsonItemStack, output: JList[JsonItemStack], minHeat: Int)
 extends MultiIC2MachineRecipe