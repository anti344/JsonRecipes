/*
 * File Config.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.config

import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent
import net.minecraftforge.common.config.{Configuration, Property}
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.anti344.jsonrecipes.JsonRecipes
import net.anti344.jsonrecipes.handlers.Log
import net.minecraft.util.StatCollector
import java.io.File

object Config{

  var main: Configuration = null

  var checkVersion: Property = null
  var errorOutput: Property = null

  def init(cfgFile: File) = {
    main = new Configuration(cfgFile, JsonRecipes.version)
    main.load()
    load()
  }

  @SubscribeEvent
  def save(e: OnConfigChangedEvent) =
    if(e.modID == JsonRecipes.modid)
      load()

  private def load() = {
    try{
      checkVersion =
        main.get("RUNTIME", "version_checker", true, StatCollector.translateToLocal("jrecipes.config.checker.tooltip"))
        .setLanguageKey("jrecipes.config.checker")
      errorOutput =
        main.get("RUNTIME", "chat_output", false, StatCollector.translateToLocal("jrecipes.config.output.tooltip"))
        .setLanguageKey("jrecipes.config.output")
    }catch{
      case e: RuntimeException =>
        Log.error(e)
    }finally{
      if(main.hasChanged)
        main.save()
    }
  }
}