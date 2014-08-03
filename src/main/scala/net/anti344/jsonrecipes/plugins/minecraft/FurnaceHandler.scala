/*
 * File FurnaceHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.minecraft

import net.anti344.jsonrecipes.plugins.RecipeHandler
import cpw.mods.fml.relauncher.ReflectionHelper
import net.anti344.jsonrecipes.handlers.Log
import net.anti344.jsonrecipes.impl.JsonItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import scala.collection.JavaConversions.asScalaBuffer
import net.minecraftforge.oredict.OreDictionary
import java.util.{Map => JMap, HashMap => JHashMap}
import scala.collection.mutable.{Map => MMap}
import net.minecraft.item.ItemStack

object FurnaceHandler
 extends RecipeHandler[JsonFurnaceRecipe]{

  val recipes: MMap[JsonFurnaceRecipe, (Seq[ItemStack], ItemStack)] = MMap()

  private val smeltingMap: JMap[ItemStack, ItemStack] =
    FurnaceRecipes.smelting.getSmeltingList.asInstanceOf[JMap[ItemStack, ItemStack]]

  private lazy val expMap: JMap[ItemStack, Float] =
    try{
      ReflectionHelper.findField(classOf[FurnaceRecipes], "field_77605_c", "experienceList")
        .get(FurnaceRecipes.smelting).asInstanceOf[JMap[ItemStack, Float]]
    }catch{
      case e: Exception =>
        Log.info("Catched exception durind accessing FurnaceRecipes.experienceList. Can cause furnace recipes glitches.")
        Log.error(e)
        new JHashMap()
    }

  def addRecipe(recipe: JsonFurnaceRecipe): Boolean =
    if(recipe.output.exists){
      val out = recipe.output.getItemStack
      var in: Seq[ItemStack] = Seq()
      if(recipe.input.exists)
        in = Seq(recipe.input.getItemStack)
      else if(recipe.input.isOredict)
        in = OreDictionary.getOres(recipe.input.getName)
      else
        return false
      recipes(recipe) = (in, out)
      in.foreach(smeltingMap.put(_, out))
      true
    }else
      false

  def removeRecipe(recipe: JsonFurnaceRecipe): Boolean =
    recipes.remove(recipe) match{
      case Some((in, out)) =>
        in.foreach(smeltingMap.remove(_))
        expMap.remove(out)
        true
      case _ =>
        false
    }
}

case class JsonFurnaceRecipe(input: JsonItemStack, output: JsonItemStack, xp: Float = 0.0F)