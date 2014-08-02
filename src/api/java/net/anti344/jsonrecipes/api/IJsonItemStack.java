/*
 * File IJsonItemStack.java is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
  * A class to decode {@link ItemStack}'s from JSON.
  */
public interface IJsonItemStack{

    /**
      * @return A string from 'item' JSON field.
      */
    String getName();

    /**
      * @return A boolean from 'oredict' JSON field.
      */
    boolean isOredict();

    /**
      * If number was less than zero, then {@link net.minecraftforge.oredict.OreDictionary#WILDCARD_VALUE WILDCARD_VALUE} is returned.
      * @return Metadata value of the stack.
      */
    int getMeta();

    /**
      * Bounded to 1-64 range.
      * @return Size of the stack.
      */
    int getCount();

    /**
      * If empty - <code>null</code>'ll be returned.
      * @return Stack NBT tag.
      */
    NBTTagCompound getNBT();

    /**
      * @return <code>true</code> if item with given {@link #getName ID} actually exist.
      */
    boolean exists();

    /**
      * If oredict or does not exist - returns {@link #getName}, {@link #getItemStack} otherwise.
      * @return Raw "component" of the stack
      */
    Object getComponent();

    /**
      * @return if item with given name actually exists returns new {@link ItemStack} with
	  * all info from here assigned, <code>null</code> otherwise.
      */
    ItemStack getItemStack();
}