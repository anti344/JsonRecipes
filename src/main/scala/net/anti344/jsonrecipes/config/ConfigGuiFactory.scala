/*
 * File ConfigGuiFactory.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.config


import cpw.mods.fml.client.IModGuiFactory
import cpw.mods.fml.client.IModGuiFactory.{RuntimeOptionGuiHandler, RuntimeOptionCategoryElement}
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import java.util.{Set => JSet}

class ConfigGuiFactory
 extends IModGuiFactory{

  def mainConfigGuiClass(): Class[_ <: GuiScreen] =
    classOf[ConfigGui]

  def initialize(minecraftInstance: Minecraft) = {}

  def runtimeGuiCategories(): JSet[RuntimeOptionCategoryElement] =
    null

  def getHandlerFor(element: RuntimeOptionCategoryElement): RuntimeOptionGuiHandler =
    null
}
