/*
 * File Plugin.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins

import net.anti344.jsonrecipes.impl.RecipeRegistry.doRegisterRecipeType
import net.anti344.jsonrecipes.api.IRecipeHandler
import scala.reflect.{ClassTag, classTag}

trait Plugin
 extends DelayedInit{

  val modid: String => String

  private var func: () => Unit = () => {}

  def delayedInit(body: => Unit) =
    func = () => body

  final def execute() =
    func()

  final def register[T : ClassTag](tpe: String, handler: IRecipeHandler[T]) =
    doRegisterRecipeType(modid + tpe, classTag[T].runtimeClass, handler)
}