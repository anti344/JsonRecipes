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

  val modid: String = "IC2-"

  register[JsonSimpleIC2MachineRecipe]("compressor", new SimpleIC2MachineHandler(IC2Recipes.compressor))
  register[JsonSimpleIC2MachineRecipe]("macerator", new SimpleIC2MachineHandler(IC2Recipes.macerator))
  register[JsonSimpleIC2MachineRecipe]("extractor", new SimpleIC2MachineHandler(IC2Recipes.extractor))
  register[JsonSimpleIC2MachineRecipe]("extruder", new SimpleIC2MachineHandler(IC2Recipes.metalformerExtruding))
  register[JsonSimpleIC2MachineRecipe]("cutter", new SimpleIC2MachineHandler(IC2Recipes.metalformerCutting))
  register[JsonSimpleIC2MachineRecipe]("roller", new SimpleIC2MachineHandler(IC2Recipes.metalformerRolling))

//  register[JsonSimpleIC2MachineRecipe]("centrifuge", null)
//  register[JsonMatterAmplifierRecipe]("washer", null)
//  register[JsonSimpleIC2MachineRecipe]("blastfurance", null)

  register[JsonMatterAmplifierRecipe]("amplifier", MatterAmplifierHandler)
  register[JsonBottlerRecipe]("bottler", BottlerHandler)
  register[JsonEnricherRecipe]("enricher", EnricherHandler)
}