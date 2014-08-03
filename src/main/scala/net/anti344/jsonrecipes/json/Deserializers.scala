/*
 * File Deserializers.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.json

import net.anti344.jsonrecipes.api.{IJsonItemStack, IJsonFluidStack}
import net.anti344.jsonrecipes.impl.{JsonItemStack, JsonFluidStack}
import net.minecraft.nbt.{NBTBase, JsonToNBT}
import java.lang.reflect.Type
import com.google.gson._

object Deserializers{

  val main: Gson = new GsonBuilder()
    .registerTypeHierarchyAdapter(classOf[IJsonItemStack], JsonItemStackDeserializer)
    .registerTypeHierarchyAdapter(classOf[IJsonFluidStack], JsonFluidStackDeserializer)
    .registerTypeHierarchyAdapter(classOf[NBTBase], NBTJsonDeserializer)
    .create()

  private val withoutShortcuts: Gson = new GsonBuilder()
    .registerTypeHierarchyAdapter(classOf[NBTBase], NBTJsonDeserializer)
    .registerTypeAdapter(classOf[IJsonItemStack], JsonItemStackCreator)
    .registerTypeAdapter(classOf[IJsonFluidStack], JsonFluidStackCreator)
    .create()

  object NBTJsonDeserializer
   extends JsonDeserializer[NBTBase]{

    def deserialize(json: JsonElement, tpe: Type, ctx: JsonDeserializationContext): NBTBase =
      JsonToNBT.func_150315_a(json.toString.replaceAll("\"", ""))
  }

  object JsonItemStackCreator
   extends InstanceCreator[IJsonItemStack]{

    def createInstance(tpe: Type): IJsonItemStack =
      new JsonItemStack(null, false, 0, 0, null)
  }

  object JsonFluidStackCreator
    extends InstanceCreator[IJsonFluidStack]{

    def createInstance(tpe: Type): IJsonFluidStack =
      new JsonFluidStack(null, 0, null)
  }

  object JsonItemStackDeserializer
   extends JsonDeserializer[IJsonItemStack]{

    def deserialize(json: JsonElement, tpe: Type, ctx: JsonDeserializationContext): IJsonItemStack =
      json match{
        case prim: JsonPrimitive =>
          val str = prim.getAsString
          if(Aliases.items.contains(str))
            Aliases.items(str)
          else
            new JsonItemStack(str, false, 1, 0, null)
        case _ =>
          withoutShortcuts.fromJson(json, tpe)
      }
  }

  object JsonFluidStackDeserializer
   extends JsonDeserializer[IJsonFluidStack]{

    def deserialize(json: JsonElement, tpe: Type, ctx: JsonDeserializationContext): IJsonFluidStack =
      json match{
        case prim: JsonPrimitive =>
          val str = prim.getAsString
          if(Aliases.fluids.contains(str))
            Aliases.fluids(str)
          else
            new JsonFluidStack(str, 0, null)
        case _ =>
          withoutShortcuts.fromJson(json, tpe)
      }
  }
}