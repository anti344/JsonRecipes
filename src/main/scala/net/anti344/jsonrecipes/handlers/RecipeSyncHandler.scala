/*
 * File RecipeSyncHandler.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.handlers

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent
import net.anti344.jsonrecipes.network.PacketManager
import net.anti344.jsonrecipes.network.packet.{Packet, PacketRemoveRecipes}
import net.minecraft.entity.player.EntityPlayerMP
import scala.collection.mutable.ArrayBuffer
import cpw.mods.fml.common.FMLCommonHandler

object RecipeSyncHandler{

  private val queue: ArrayBuffer[Packet] = ArrayBuffer(new PacketRemoveRecipes)

  def clearQueue() = {
    queue.clear()
    queue += new PacketRemoveRecipes
    Log.debug("Cleared login packet queue.")
  }

  def addToQueue(pkt: Packet) = {
    queue += pkt
    Log.debug(s"Added ${pkt.getClass.getSimpleName} to login packet queue.")
  }

  @SubscribeEvent
  def onPlayerLogin(e: PlayerLoggedInEvent) =
    e.player match{
      case player: EntityPlayerMP if FMLCommonHandler.instance.getSide.isServer =>
        queue.foreach(PacketManager.sendTo(_, player))
        Log.debug(s"Sending queued packets to ${player.getCommandSenderName}. They are {${queue.map(_.getClass.getSimpleName).mkString(", ")}}")
      case _ =>
    }
}