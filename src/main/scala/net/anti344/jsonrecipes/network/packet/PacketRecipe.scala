/*
 * File PacketRecipe.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.network.packet

import net.anti344.jsonrecipes.json.Parser
import cpw.mods.fml.relauncher.Side
import io.netty.buffer.ByteBuf

case class PacketRecipe(private var json: String)
 extends Packet{

  def this() =
    this("")

  def read(buf: ByteBuf) =
    json = buf

  def write(buf: ByteBuf) =
    buf <<< json

  def execute(side: Side) =
    Parser.loadSingleRecipe(json)
}