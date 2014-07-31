/*
 * File ReflectPluginLoader.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins

import cpw.mods.fml.common.Loader
import net.anti344.jsonrecipes.handlers.Log

object ReflectPluginLoader{

  val plugins: Map[String, String] = Map(
    "BuildCraft|Core" -> "buildcraft.BuildCraftPlugin",
    "IC2" -> "ic2.IC2Plugin"
  )

  def loadPlugins() = {
    plugins.foreach{e =>
      if(Loader.isModLoaded(e._1)){
        try{
          Class.forName("net.anti344.customrecipes.plugins." + e._2 + "$", true, Loader.instance.getModClassLoader)
            .getField("MODULE$").get(null) match{
            case plugin: Plugin =>
              plugin.execute()
            case _ =>
          }
        }catch{
          case exc: Exception =>
            Log.warn(s"Failed to load plugin for ${e._1}.")
            exc.printStackTrace()
        }
      }
    }
  }
}