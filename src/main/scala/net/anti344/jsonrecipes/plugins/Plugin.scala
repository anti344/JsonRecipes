/*
 * File Plugin.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins

import javax.script.{Invocable, ScriptEngineManager}
import net.anti344.jsonrecipes.api.IRecipeHandler
import net.anti344.jsonrecipes.impl.RecipeRegistry.doRegisterRecipeType
import scala.reflect.{ClassTag, classTag}
import scala.tools.nsc.interpreter.IMain

trait Plugin
 extends DelayedInit{

  private var func: () => Unit = () => {}

  def delayedInit(body: => Unit) =
    func = () => body

  final def execute() =
    func()

  final def register[T : ClassTag](tpe: String, handler: IRecipeHandler[T]) =
    doRegisterRecipeType(tpe, classTag[T].runtimeClass, handler)
}