/*
 * File RecipeRegistry.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.impl

import cpw.mods.fml.common.Loader.{instance => Loader}
import cpw.mods.fml.common.LoaderState
import net.anti344.jsonrecipes.api.{IRecipeHandler, IRecipeRegistry}
import net.anti344.jsonrecipes.handlers.Log

import scala.collection.mutable.{Map => MMap}

object RecipeRegistry
 extends IRecipeRegistry{

  val registry: MMap[String, (Class[_], IRecipeHandler[_])] = MMap().withDefaultValue(null)

  def getRecipe(tpe: String): (Class[_], IRecipeHandler[_]) =
    registry(tpe)

  def registerRecipeType[T](tpe: String, recipeCls: Class[T], handler: IRecipeHandler[T]) =
    registerRecipeType(tpe, recipeCls, handler, null)

  def registerRecipeType[T](tpe: String, recipeCls: Class[T], handler: IRecipeHandler[T], modid: String) = {
    val _modid =
      if(modid == null && Loader.activeModContainer != null)
        Loader.activeModContainer.getModId
      else
        modid
    if(Loader.hasReachedState(LoaderState.POSTINITIALIZATION))
      throw new IllegalStateException("You must register recipe classes during mod initialization! " +
        s"Tryed to register type '$tpe' with $recipeCls")
    doRegisterRecipeType(_modid + "-" + tpe, recipeCls, handler)
  }

  private[jsonrecipes] def doRegisterRecipeType(tpe: String, recipeCls: Class[_], handler: IRecipeHandler[_]) = {
    Log.debug(s"Registered new recipe type - '$tpe'.")
    registry(tpe) = (recipeCls, handler)
  }
}