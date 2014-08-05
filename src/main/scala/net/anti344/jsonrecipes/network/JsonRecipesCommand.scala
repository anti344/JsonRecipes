/*
 * File JsonRecipesCommand.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.network

import net.anti344.jsonrecipes.json.Parser
import net.minecraft.command._
import net.minecraft.util.ChatComponentTranslation

object JsonRecipesCommand
 extends CommandBase{

  def processCommand(ics: ICommandSender, args: Array[String]) =
    args match{
      case Array("reload") =>
        if(Parser.load())
          ics.addChatMessage(new ChatComponentTranslation("jrecipes.command.reload.success"))
        else
          ics.addChatMessage(new ChatComponentTranslation("jrecipes.command.reload.failture"))
      case _ =>
        throw new WrongUsageException("jrecipes.command.reload.usage")
    }

  def getCommandName: String =
    "jrecipes"

  def getCommandUsage(ics: ICommandSender): String =
    "jrecipes.reload.usage"

  override def getRequiredPermissionLevel: Int =
    2
}