/*
 * File Test.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins

import net.anti344.jsonrecipes.api.IRecipeHandler
import scala.reflect.{ClassTag, classTag}

abstract class RecipeHandler[T : ClassTag]
 extends IRecipeHandler[T]{

  def getRecipeClass: Class[T] =
    classTag[T].runtimeClass.asInstanceOf[Class[T]]
}