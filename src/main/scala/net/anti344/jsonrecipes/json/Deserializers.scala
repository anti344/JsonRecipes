/*
 * File Deserializers.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.json

import com.google.gson.reflect.TypeToken
import net.anti344.jsonrecipes.api.{IJsonItemStack, IJsonFluidStack}
import net.anti344.jsonrecipes.impl.{JsonItemStack, JsonFluidStack}
import net.minecraft.nbt.{NBTBase, JsonToNBT}
import java.lang.reflect.Type
import com.google.gson._

object Deserializers{

  val default: Gson = new Gson

  val main: Gson = new GsonBuilder()
    .registerTypeHierarchyAdapter(classOf[IJsonItemStack], JsonItemStackDeserilizer)
    .registerTypeHierarchyAdapter(classOf[IJsonFluidStack], JsonFluidStackDeserilizer)
    .registerTypeHierarchyAdapter(classOf[NBTBase], NBTJsonDeserializer)
    .create()

  private val withNBT: Gson = new GsonBuilder()
    .registerTypeHierarchyAdapter(classOf[NBTBase], NBTJsonDeserializer)
    .create()

  private object NBTJsonDeserializer
   extends JsonDeserializer[NBTBase]{

    @throws[JsonParseException]
    def deserialize(json: JsonElement, tpe: Type, ctx: JsonDeserializationContext): NBTBase =
      JsonToNBT.func_150315_a(json.toString.replaceAll("\"", ""))
  }

  private object JsonItemStackDeserilizer
   extends JsonDeserializer[IJsonItemStack]{

    @throws[JsonParseException]
    def deserialize(json: JsonElement, tpe: Type, ctx: JsonDeserializationContext): IJsonItemStack =
      json match{
        case prim: JsonPrimitive =>
          val str = prim.getAsString
          if(Aliases.items.contains(str))
            Aliases.items(str)
          else
            new JsonItemStack(str, false, 1, 0, null)
        case _ =>
          withNBT.fromJson(json, tpe)
      }
  }

  private object JsonFluidStackDeserilizer
   extends JsonDeserializer[IJsonFluidStack]{

    @throws[JsonParseException]
    def deserialize(json: JsonElement, tpe: Type, ctx: JsonDeserializationContext): IJsonFluidStack =
      json match{
        case prim: JsonPrimitive =>
          val str = prim.getAsString
          if(Aliases.fluids.contains(str))
            Aliases.fluids(str)
          else
            new JsonFluidStack(str, 0, null)
        case _ =>
          withNBT.fromJson(json, tpe)
      }
  }
}