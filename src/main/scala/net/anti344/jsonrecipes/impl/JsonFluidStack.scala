/*
 * File JsonFluidStack.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.impl

import net.anti344.jsonrecipes.api.IJsonFluidStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.{FluidContainerRegistry, FluidRegistry, FluidStack}

case class JsonFluidStack(fluid: String, amount: Int = FluidContainerRegistry.BUCKET_VOLUME, nbt: NBTTagCompound = null)
 extends IJsonFluidStack{

  def getName: String =
    fluid

  def getAmount: Int =
    amount

  def getNBT: NBTTagCompound =
    nbt

  def exists: Boolean =
    FluidRegistry.getFluid(fluid) != null

  def getFluidStack: FluidStack =
    if(exists)
      new FluidStack(FluidRegistry.getFluidID(fluid), amount, nbt)
    else
      null
}