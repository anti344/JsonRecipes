/*
 * File JsonItemStack.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.impl

import cpw.mods.fml.common.registry.GameData
import net.anti344.jsonrecipes.api.IJsonItemStack
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.oredict.OreDictionary

class JsonItemStack(item: String, oredict: Boolean, count: Int, meta: Int, nbt: NBTTagCompound)
 extends IJsonItemStack{

  def getName: String =
    item

  def isOredict: Boolean =
    oredict

  def getMeta: Int =
    if(meta < 0)
      OreDictionary.WILDCARD_VALUE
    else
      meta

  def getCount: Int =
    if(count < 1)
      1
    else if(count > 64)
      64
    else
      count

  def getNBT: NBTTagCompound =
    nbt

  def exists: Boolean =
    !oredict && GameData.getItemRegistry.containsKey(item)

  def getComponent: AnyRef =
    if(exists){
      getItemStack
    }else
      item

  def getItemStack: ItemStack =
    if(exists){
      val is = new ItemStack(GameData.getItemRegistry.getObject(item), getCount, getMeta)
      is.setTagCompound(nbt)
      is
    }else
      null
}