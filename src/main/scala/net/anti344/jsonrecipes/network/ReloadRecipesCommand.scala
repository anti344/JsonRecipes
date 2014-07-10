/*
 * File ReloadRecipesCommand.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.network

import net.anti344.jsonrecipes.json.Parser
import net.minecraft.command.{CommandBase, ICommandSender}
import net.minecraft.util.ChatComponentTranslation

object ReloadRecipesCommand
  extends CommandBase{

  def processCommand(ics: ICommandSender, args: Array[String]) =
    if(Parser.load())
      ics.addChatMessage(new ChatComponentTranslation("cr.reload.success"))
    else
      ics.addChatMessage(new ChatComponentTranslation("cr.reload.failture"))

  def getCommandName: String =
    "reloadRecipes"

  def getCommandUsage(ics: ICommandSender): String =
    "cr.reload.usage"

  override def getRequiredPermissionLevel: Int =
    2
}
