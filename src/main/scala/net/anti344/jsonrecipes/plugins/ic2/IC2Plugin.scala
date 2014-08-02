/*
 * File IC2Plugin.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.plugins.ic2

import net.anti344.jsonrecipes.plugins.Plugin
import ic2.api.recipe.{Recipes => IC2Recipes}

object IC2Plugin
 extends Plugin{
  register[JsonSimpleIC2MachineRecipe]("IC2-compressor", new SimpleIC2MachineHandler(IC2Recipes.compressor))
  register[JsonSimpleIC2MachineRecipe]("IC2-macerator", new SimpleIC2MachineHandler(IC2Recipes.macerator))
  register[JsonSimpleIC2MachineRecipe]("IC2-extractor", new SimpleIC2MachineHandler(IC2Recipes.extractor))

  register[JsonSimpleIC2MachineRecipe]("IC2-extruder", new SimpleIC2MachineHandler(IC2Recipes.metalformerExtruding))
  register[JsonSimpleIC2MachineRecipe]("IC2-cutter", new SimpleIC2MachineHandler(IC2Recipes.metalformerCutting))
  register[JsonSimpleIC2MachineRecipe]("IC2-roller", new SimpleIC2MachineHandler(IC2Recipes.metalformerRolling))
}