/*
 * File ConfigGui.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.config

import net.anti344.jsonrecipes.JsonRecipes
import net.minecraft.client.resources.I18n
import cpw.mods.fml.client.config.GuiConfig
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.common.config.ConfigElement
import scala.collection.JavaConversions.seqAsJavaList

class ConfigGui(parent: GuiScreen)
 extends GuiConfig(parent,
   Seq(Config.checkVersion, Config.errorOutput).map(new ConfigElement(_)),
   JsonRecipes.modid, false, false, I18n.format("cr.config"))