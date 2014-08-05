/*
 * File PacketManager.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPLv3), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.network

import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget._
import net.anti344.jsonrecipes.network.packet._
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.anti344.jsonrecipes.JsonRecipes
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.network.{Packet => MCPacket}
import scala.reflect.{ClassTag, classTag}
import cpw.mods.fml.common.network._
import io.netty.buffer.ByteBuf
import io.netty.channel._

object PacketManager{
  {
    registerPacket[PacketRecipe]
    registerPacket[PacketRemoveRecipes]
  }

  private val target = FMLOutboundHandler.FML_MESSAGETARGET
  private val args = FMLOutboundHandler.FML_MESSAGETARGETARGS
  private val fireExc = ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE

  private var packetID: Int = 0

  var server: FMLEmbeddedChannel = null
  var client: FMLEmbeddedChannel = null

  def init() = {
    val channels = NetworkRegistry.INSTANCE.newChannel(JsonRecipes.modid, PacketCodec)
    server = channels.get(Side.SERVER)
    client = channels.get(Side.CLIENT)
    val tpe = classOf[FMLIndexedMessageToMessageCodec[Packet]]
    client.pipeline.addAfter(client.findChannelHandlerNameForType(tpe), "executor", new PacketHandler(Side.CLIENT))
    server.pipeline.addAfter(server.findChannelHandlerNameForType(tpe), "executor", new PacketHandler(Side.SERVER))
  }

  def registerPacket[T <: Packet : ClassTag] = {
    PacketCodec.addDiscriminator(packetID, classTag[T].runtimeClass.asInstanceOf[Class[_ <: Packet]])
    packetID += 1
  }

  def getPacketFrom(packet: Packet): MCPacket =
    server.generatePacketFrom(packet)

  @SideOnly(Side.CLIENT)
  def sendToServer(packet: Packet) = {
    client.attr(target).set(TOSERVER)
    client.writeAndFlush(packet).addListener(fireExc)
  }

  def sendToAll(packet: Packet) = {
    server.attr(target).set(ALL)
    server.writeAndFlush(packet).addListener(fireExc)
  }

  def sendTo(packet: Packet, player: EntityPlayerMP) = {
    server.attr(target).set(PLAYER)
    server.attr(args).set(player)
    server.writeAndFlush(packet).addListener(fireExc)
  }

  def sendToAllAround(packet: Packet, x: Double, y: Double, z: Double, range: Double, dim: Int) = {
    server.attr(target).set(ALLAROUNDPOINT)
    server.attr(args).set(new NetworkRegistry.TargetPoint(dim, x, y, z, range))
    server.writeAndFlush(packet).addListener(fireExc)
  }

  def sendToDimension(packet: Packet, dimensionId: Int) = {
    server.attr(target).set(DIMENSION)
    server.attr(args).set(Int.box(dimensionId))
    server.writeAndFlush(packet).addListener(fireExc)
  }

  private object PacketCodec
   extends FMLIndexedMessageToMessageCodec[Packet]{

    def encodeInto(ctx: ChannelHandlerContext, msg: Packet, pkt: ByteBuf) =
      msg.write(pkt)

    def decodeInto(ctx: ChannelHandlerContext, buf: ByteBuf, pkt: Packet) =
      pkt.read(buf)
  }

  private class PacketHandler(side: Side)
   extends SimpleChannelInboundHandler[Packet]{

    def channelRead0(ctx: ChannelHandlerContext, pkt: Packet) =
      pkt.exec(side)
  }
}