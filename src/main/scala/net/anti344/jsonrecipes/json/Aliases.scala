/*
 * File Aliases.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.json

import net.anti344.jsonrecipes.handlers.MessageHandler
import net.anti344.jsonrecipes.impl.{JsonItemStack, JsonFluidStack}
import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.mutable.{Map => MMap}
import java.io.{FileWriter, File, FileReader}
import java.util.{HashMap => JMap}

object Aliases{

  lazy val file: File = {
    val temp = new File(Parser.recipeDir, "aliases.json")
    if(!temp.exists()){
      val writer = new FileWriter(temp)
      writer.append("{\n  item:{\n    \n  },\n  fluid:{\n    \n  },\n  type:{\n    \n  }\n}")
      writer.flush()
      writer.close()
    }
    temp
  }

  var items: MMap[String, JsonItemStack] = MMap()
  var fluids: MMap[String, JsonFluidStack] = MMap()
  var types: MMap[String, String] = MMap()

  def load() =
    try{
      val aliases = Deserializers.main.fromJson(new FileReader(file), classOf[Holder])
      items = aliases.item
      fluids = aliases.fluid
      types = aliases.`type`
    }catch{
      case e: Exception =>
        MessageHandler.error("cr.error.aliases")
    }

  private case class Holder(item: JMap[String, JsonItemStack], fluid: JMap[String, JsonFluidStack], `type`: JMap[String, String])
}