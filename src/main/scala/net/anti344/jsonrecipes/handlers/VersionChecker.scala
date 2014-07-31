/*
 * File VersionChecker.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.handlers

import com.google.gson.Gson
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.event.FMLInterModComms
import net.anti344.jsonrecipes.JsonRecipes._
import net.anti344.jsonrecipes.config.Config
import net.minecraft.util.ChatComponentTranslation
import collection.JavaConversions.asScalaBuffer
import concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}
import java.util.{List => JList}
import scala.io.Source

object VersionChecker{

  val link: String = "https://raw.githubusercontent.com/anti344/JsonRecipes/master/version.json"

  def start() = {
    FMLInterModComms.sendMessage("VersionChecker", "addVersionCheck", link)
    if(FMLCommonHandler.instance.getSide.isClient)
      concurrent.Future{
        val raw = Source.fromURL(link).mkString
        if(raw != null){
          new Gson().fromJson(raw, classOf[VersionList]).versionList
           .find(_.mcVersion == "Minecraft " + mcVersion)match{
            case Some(VersionEntry(_, v)) if v != version =>
              v
            case _ =>
              null
          }
        }else null
      }onComplete{
        case Success(latest) =>
          if(latest != null && Config.checkVersion.getBoolean)
            MessageHandler.msg(new ChatComponentTranslation("jrecipes.checker.msg", latest, mcVersion))
          Log.debug(s"Current mod version $version, latest for MC $mcVersion - $latest, new version avaible.")
        case Failure(exc) =>
          Log.info("Failed to check version, probably you don't have an internet connection.")
          Log.error(exc)
      }
  }

  case class VersionList(versionList: JList[VersionEntry])
  case class VersionEntry(mcVersion: String, modVersion: String)
}