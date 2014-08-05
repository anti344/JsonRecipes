/*
 * File Plugin.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins

import net.anti344.jsonrecipes.impl.RecipeRegistry.doRegisterRecipeType

trait Plugin
 extends DelayedInit{

  var modid: String = null

  private var func: () => Unit = () => {}

  def delayedInit(body: => Unit) =
    func = () => body

  final def execute(modid: String) = {
    this.modid = modid
    func()
  }

  final def register(tpe: String, handler: RecipeHandler[_]) =
    doRegisterRecipeType(if(modid != null)s"$modid-$tpe" else tpe, handler.getRecipeClass, handler)
}