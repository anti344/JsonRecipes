/*
 * File package.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins

import net.anti344.jsonrecipes.impl.JsonItemStack
import net.minecraft.nbt.NBTTagCompound
import _root_.ic2.api.recipe._

package object ic2{

  trait Thing2NBT[A]{
    def writeToNBT(key: String, value: A, nbt: NBTTagCompound)
  }

  implicit val int2nbt = new Thing2NBT[Int]{
    def writeToNBT(key: String, value: Int, nbt: NBTTagCompound) = nbt.setInteger(key, value)
  }

  implicit val str2nbt = new Thing2NBT[String]{
    def writeToNBT(key: String, value: String, nbt: NBTTagCompound) = nbt.setString(key, value)
  }

  implicit def meta2nbt[T : Thing2NBT](meta: (String, T)): NBTTagCompound = {
    val nbt = new NBTTagCompound
    implicitly[Thing2NBT[T]].writeToNBT(meta._1, meta._2, nbt)
    nbt
  }

  implicit def convert(stack: JsonItemStack): IRecipeInput =
    if(stack.isOredict)
      new RecipeInputOreDict(stack.getName, stack.getCount, stack.getMeta)
    else if(stack.exists)
      new RecipeInputItemStack(stack.getItemStack)
    else
      null
}