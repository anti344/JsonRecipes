/*
 * File JsonRecipesCommand.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.network

import net.anti344.jsonrecipes.handlers.MessageHandler.str2chat
import net.anti344.jsonrecipes.config.Config
import net.anti344.jsonrecipes.json.Parser
import net.minecraft.command._

object JsonRecipesCommand
 extends CommandBase{

  def processCommand(ics: ICommandSender, args: Array[String]) =
    args match{
      case Array("reload") =>
        if(Parser.load())
          ics.addChatMessage("jrecipes.command.reload.success")
        else{
          ics.addChatMessage("jrecipes.command.reload.failture")
          if(!Config.errorOutput.getBoolean)
            ics.addChatMessage("jrecipes.command.reload.failture.full")
        }
      case _ =>
        throw new WrongUsageException(getCommandUsage(ics))
    }

  def getCommandName: String =
    "jrecipes"

  def getCommandUsage(ics: ICommandSender): String =
    "jrecipes.command.reload.usage"

  override def getRequiredPermissionLevel: Int =
    2
}