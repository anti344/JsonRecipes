/*
 * File Log.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.handlers

import cpw.mods.fml.common.FMLLog
import net.anti344.jsonrecipes.JsonRecipes
import org.apache.logging.log4j.Level

object Log{

  def log(lvl: Level, obj: AnyRef) =
    FMLLog.log(JsonRecipes.modname, lvl, String.valueOf(obj))

  def fatal(obj: AnyRef) =
    log(Level.FATAL, obj)

  def error(obj: AnyRef) =
    log(Level.ERROR, obj)

  def warn(obj: AnyRef) =
    log(Level.WARN, obj)

  def info(obj: AnyRef) =
    log(Level.INFO, obj)

  def debug(obj: AnyRef) =
//    log(Level.DEBUG, obj)
    println(String.valueOf(obj))

  def trace(obj: AnyRef) =
    log(Level.TRACE, obj)
 }
