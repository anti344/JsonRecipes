/*
 * File MessageHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.handlers

import net.anti344.jsonrecipes.config.Config
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.TickEvent
import collection.mutable.ArrayBuffer
import net.minecraft.client.Minecraft.{getMinecraft => mc}
import net.minecraft.util._

object MessageHandler {

  private var initialized: Boolean = false
  private val messages: ArrayBuffer[IChatComponent] = ArrayBuffer()

  @SubscribeEvent
  def onTick(e: TickEvent.ClientTickEvent) =
    if (e.phase == TickEvent.Phase.END && mc.inGameHasFocus && !initialized) {
      initialized = true
      messages.foreach(mc.thePlayer.addChatMessage)
      messages.clear()
    }

  def error(obj: AnyRef) = {
    Log.debug("[CHAT_ERR]" + String.valueOf(obj))
    if (Config.errorOutput.getBoolean)
      msg("[ERROR]" + String.valueOf(obj), '4')
  }

  def msg(msg: IChatComponent, color: Char = '7') = {
    val style = new ChatStyle().setColor(EnumChatFormatting.values()("0123456789abcdef".indexOf(color)))
    val _msg = msg.createCopy().setChatStyle(style)
    if(initialized && mc.thePlayer != null)
      mc.thePlayer.addChatMessage(_msg)
    else
      messages += _msg
  }

  implicit def str2chat(str: String): IChatComponent =
    new ChatComponentTranslation(str)
}