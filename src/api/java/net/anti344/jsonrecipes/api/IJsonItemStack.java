/*
 * File IJsonItemStack.java is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
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
      * @return A boolean fron 'oredict' JSON field.
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
      * If {@link #isOredict} returns <code>true</code> then {@link #getName name}'ll be returned.
      * Else it'll try to find Item for {@link #getName name} and if it exist - return {@link ItemStack}
      * with all info from here assigned.
      * @return Raw "component" of the stack
      */
    Object getComponent();

    /**
      * @return if can - {@link #getComponent} casted to {@link ItemStack}, <code>null</code> otherwise.
      */
    ItemStack getItemStack();
}