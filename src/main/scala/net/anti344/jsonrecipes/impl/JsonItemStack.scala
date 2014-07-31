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

case class JsonItemStack(item: String, oredict: Boolean = false, count: Int = 1, meta: Int = 0, nbt: NBTTagCompound = null)
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
    if(!oredict){
      val itm = GameData.getItemRegistry.getObject(item)
      if(itm != null){
        val is = new ItemStack(itm, getCount, getMeta)
        is.setTagCompound(nbt)
        is
      }else
        null
    }else
      item

  def getItemStack: ItemStack =
    if(exists){
      getComponent.asInstanceOf[ItemStack]
    }else
      null
}