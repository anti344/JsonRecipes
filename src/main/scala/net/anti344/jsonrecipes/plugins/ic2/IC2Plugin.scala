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

  val modid: String = "IC2"

  register("compressor", new SimpleIC2MachineHandler(IC2Recipes.compressor))
  register("macerator", new SimpleIC2MachineHandler(IC2Recipes.macerator))
  register("extractor", new SimpleIC2MachineHandler(IC2Recipes.extractor))
  register("extruder", new SimpleIC2MachineHandler(IC2Recipes.metalformerExtruding))
  register("cutter", new SimpleIC2MachineHandler(IC2Recipes.metalformerCutting))
  register("roller", new SimpleIC2MachineHandler(IC2Recipes.metalformerRolling))
  register("blockcutter", new SimpleIC2MachineHandler(IC2Recipes.blockcutter))

  register("centrifuge", CentrifugeHandler)
  register("washer", WasherHandler)
  register("blastfurnace", new MultiIC2MachineHandler[JsonMultiIC2MachineRecipe](IC2Recipes.blastfurance))

  register("amplifier", MatterAmplifierHandler)
  register("bottler", BottlerHandler)
  register("enricher", EnricherHandler)
}