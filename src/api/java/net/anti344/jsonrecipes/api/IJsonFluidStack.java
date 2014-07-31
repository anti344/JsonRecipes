/*
 * File IJsonFluidStack.java is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.api;

import net.minecraftforge.fluids.FluidStack;
import net.minecraft.nbt.NBTTagCompound;

/**
  * A class to decode {@link FluidStack}'s from JSON.
  */
public interface IJsonFluidStack{

    /**
      * @return A string from 'fluid' JSON field.
      */
    String getName();

    /**
      * @return Amount of the stack in millibuckets.
      */
    int getAmount();

    /**
      * If empty - <code>null</code>'ll be returned.
      * @return Stack NBT tag.
      */
    NBTTagCompound getNBT();

    /**
      * @return <code>true</code> if fluid with given {@link #getName ID} actually exist.
      */
    boolean exists();

    /**
      * Tries to find a fluid with given {@link #getName ID} and if it exist - return {@link FluidStack}
      * will all info from here assigned, <code>null</code> otherwise.
      * @return {@link FluidStack} with all info from here assigned or <code>null</code>.
      */
    FluidStack getFluidStack();
}