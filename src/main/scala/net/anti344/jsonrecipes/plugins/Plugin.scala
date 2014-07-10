/*
 * File Plugin.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins

trait Plugin
 extends DelayedInit{

  private var func: () => Unit = () => {}

  def delayedInit(body: => Unit) =
    func = () => body

  def execute() =
    func()
}