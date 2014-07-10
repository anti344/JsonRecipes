/*
 * File JsonRecipes.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes

import cpw.mods.fml.common.Mod.EventHandler
import net.anti344.jsonrecipes.config.Config
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent
import net.anti344.jsonrecipes.api.JsonRecipesAPI
import cpw.mods.fml.relauncher.FMLInjectionData
import cpw.mods.fml.common.{Mod, FMLCommonHandler}
import net.anti344.jsonrecipes.network.{PacketManager, ReloadRecipesCommand}
import net.anti344.jsonrecipes.impl.{RecipeRegistry, JsonItemStack, JsonFluidStack}
import net.anti344.jsonrecipes.plugins.minecraft.MinecraftPlugin
import net.anti344.jsonrecipes.plugins.ReflectPluginLoader
import collection.JavaConversions.asScalaBuffer
import net.anti344.jsonrecipes.handlers._
import net.anti344.jsonrecipes.json._
import cpw.mods.fml.common.event._
import java.io.File


@Mod(modid = "JsonRecipes",
     version = "@VERSION@",
     modLanguage = "scala",
     acceptedMinecraftVersions = "[1.7.2,1.7.10]",
     guiFactory = "net.anti344.jsonrecipes.config.ConfigGuiFactory",
     dependencies = "required-after:Forge@[10.12.2.1147,)")
object JsonRecipes{

  val modid: String = "JsonRecipes"
  val modname: String = "Json Recipes"
  val version: String = "@VERSION@"
  lazy val mcVersion: String = FMLInjectionData.data()(4).asInstanceOf[String]

  var mcDir: File = null

  @EventHandler
  def preInit(e: FMLPreInitializationEvent) = {
    mcDir = e.getModConfigurationDirectory.getParentFile
    Config.init(e.getSuggestedConfigurationFile)
    PacketManager.init()
    instantiateAPI()
    VersionChecker.start()
    Log.trace("PreInit state complete.")
  }

  private def instantiateAPI() = {
    JsonRecipesAPI.registry = RecipeRegistry
    JsonRecipesAPI.deserializer = Deserializers.main
    JsonRecipesAPI.jsonItemStack = classOf[JsonItemStack]
    JsonRecipesAPI.jsonFluidStack = classOf[JsonFluidStack]
  }

  @EventHandler
  def init(e: FMLInitializationEvent) = {
    Seq(Config, MessageHandler, RecipeSyncHandler)
      .foreach(FMLCommonHandler.instance.bus.register)
    Log.trace("Init state complete.")
  }

  @EventHandler
  def postInit(e: FMLPostInitializationEvent) = {
    ReflectPluginLoader.loadPlugins()
    MinecraftPlugin.execute()
    Log.trace("PostInit state complete.")
  }

  @EventHandler
  def serverStarting(e: FMLServerStartingEvent) = {
    e.registerServerCommand(ReloadRecipesCommand)
    Parser.load()
  }

  @EventHandler
  def handleIMC(e: IMCEvent) =
    e.getMessages.foreach{msg =>
      IMCHandler.handleMsg(msg)
      Log.trace(s"Received IMC message from ${msg.getSender} with key '${msg.key}.'")
    }
}