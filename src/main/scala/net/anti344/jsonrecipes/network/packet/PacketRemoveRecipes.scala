/*
 * File PacketRemoveRecipes.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.network.packet

import io.netty.buffer.ByteBuf
import net.anti344.jsonrecipes.json.Parser
import cpw.mods.fml.relauncher.Side

class PacketRemoveRecipes
 extends Packet{

  def read(buf: ByteBuf) = {}

  def write(buf: ByteBuf) = {}

  def execute(side: Side) =
    Parser.removeRecipes()
}