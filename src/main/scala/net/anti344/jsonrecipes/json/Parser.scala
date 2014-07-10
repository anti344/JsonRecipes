/*
 * File Parser.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.json

import cpw.mods.fml.common.FMLCommonHandler
import net.anti344.jsonrecipes.impl.RecipeRegistry
import net.anti344.jsonrecipes.network.PacketManager
import net.anti344.jsonrecipes.network.packet.{PacketRemoveRecipes, PacketRecipe}
import net.minecraft.util.StatCollector.{translateToLocalFormatted => format}
import net.anti344.jsonrecipes.handlers.{RecipeSyncHandler, Log, MessageHandler}
import scala.collection.JavaConversions.iterableAsScalaIterable
import net.anti344.jsonrecipes.api.IRecipeHandler
import scala.collection.mutable.ArrayBuffer
import net.anti344.jsonrecipes.JsonRecipes
import java.io.{FileReader, FileWriter, File}
import com.google.gson.JsonParser

object Parser{

  lazy val recipeDir: File = {
    val temp = new File(JsonRecipes.mcDir, "recipes/")
    if(!temp.exists())
      temp.mkdirs()
    val main = new File(temp, "recipes.json")
    if(!main.exists()){
      val writer = new FileWriter(main)
      writer.append("[\n\n]")
      writer.flush()
      writer.close()
    }
    temp
  }
  lazy val recipeFiles: Seq[File] =
    recipeDir.listFiles().filter(f => f.isFile && f.getName.endsWith(".json") && !"aliases.json".equals(f.getName))

  private val parser = new JsonParser()
  private val loadedRecipes: ArrayBuffer[(String, Any, IRecipeHandler[Any])] = ArrayBuffer()
  private val failedTypes: ArrayBuffer[String] = ArrayBuffer()

  def removeRecipes() = {
    val failedRemoving = ArrayBuffer[String]()
    loadedRecipes.foreach{r =>
      if(!r._3.removeRecipe(r._2) && !failedRemoving.contains(r._1)){
        MessageHandler.error(format("cr.error.remove", r._1))
        failedRemoving += r._1
      }
    }
    loadedRecipes.clear()
    if(FMLCommonHandler.instance.getSide.isServer){
      val pkt = new PacketRemoveRecipes
      PacketManager.sendToAll(pkt)
    }
  }

  def load(): Boolean = {
    removeRecipes()
    try{
      Aliases.load()
      failedTypes.clear()
      !recipeFiles
        .flatMap(f => parser.parse(new FileReader(f)).getAsJsonArray.map(e => loadSingleRecipe(e.toString)))
        .contains(false)
    }catch{
      case e: Exception =>
        MessageHandler.error(e)
        e.printStackTrace()
        false
    }
  }

  def loadSingleRecipe(str: String): Boolean = {
    try{
      val json = parser.parse(str).getAsJsonObject
      var tpe = json.get("type").getAsString
      if(Aliases.types.contains(tpe))
        tpe = Aliases.types(tpe)
      val pair = RecipeRegistry.getRecipe(tpe)
      val success =
        if(pair != null){
          val recipe = Deserializers.main.fromJson(json, pair._1)
          val handler = pair._2.asInstanceOf[IRecipeHandler[Any]]
            if(handler.addRecipe(recipe)){
              loadedRecipes += ((tpe, recipe, handler))
              Log.debug(s"Successfully parsed recipe with type '$tpe'!")
            }else
              MessageHandler.error(format(s"cr.error.load", tpe))
          true
        }else if(!failedTypes.contains(tpe)){
          MessageHandler.error(format("cr.error.type", tpe))
          failedTypes += tpe
          false
        }else
          false
      if(success && FMLCommonHandler.instance.getSide.isServer){
        val pkt = new PacketRecipe(str)
        PacketManager.sendToAll(pkt)
        RecipeSyncHandler.addToQueue(pkt)
      }
      success
    }catch{
      case e: Exception =>
        MessageHandler.error(e)
        e.printStackTrace()
        false
    }
  }
}