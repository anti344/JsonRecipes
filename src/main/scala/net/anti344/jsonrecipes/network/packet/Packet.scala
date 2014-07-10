/*
 * File Packet.scala is part of JsonRecipes.
 * JsonRecipes is opensource Minecraft mod(released under LGPL), created by anti344.
 * Full licence information can be found in LICENCE and LICENCE.LESSER files in jar-file of the mod.
 * Copyright © 2014, anti344
 */

package net.anti344.jsonrecipes.network.packet

import cpw.mods.fml.common.network.ByteBufUtils
import cpw.mods.fml.relauncher.Side
import io.netty.buffer.ByteBuf
import net.anti344.jsonrecipes.handlers.Log
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

trait Packet{

  def read(buf: ByteBuf)

  def write(buf: ByteBuf)

  def execute(side: Side)

  final def exec(side: Side) = {
    execute(side)
    Log.debug(s"Received ${getClass.getSimpleName} on side $side.")
  }

  implicit final protected def readBoolean(buf: ByteBuf): Boolean =
    buf.readBoolean()

  implicit final protected def readByte(buf: ByteBuf): Short =
    buf.readByte()

  implicit final protected def readShort(buf: ByteBuf): Short =
    buf.readShort()

  implicit final protected def readInt(buf: ByteBuf): Int =
    buf.readInt()

  implicit final protected def readLong(buf: ByteBuf): Long =
    buf.readLong()

  implicit final protected def readFloat(buf: ByteBuf): Float =
    buf.readFloat()

  implicit final protected def readDouble(buf: ByteBuf): Double =
    buf.readDouble()

  implicit final protected def readTag(buf: ByteBuf): NBTTagCompound =
    ByteBufUtils.readTag(buf)

  implicit final protected def readStack(buf: ByteBuf): ItemStack =
    ByteBufUtils.readItemStack(buf)

  implicit final protected def readString(buf: ByteBuf): String =
    ByteBufUtils.readUTF8String(buf)

  implicit final protected class ByteBufWrapper(buf: ByteBuf){

    def <<< (boolean: Boolean): ByteBuf = {
      buf.writeBoolean(boolean)
      buf
    }

    def <<< (byte: Byte): ByteBuf = {
      buf.writeByte(byte)
      buf
    }

    def <<< (short: Short): ByteBuf = {
      buf.writeShort(short)
      buf
    }

    def <<< (int: Int): ByteBuf = {
      buf.writeInt(int)
      buf
    }

    def <<< (long: Long): ByteBuf = {
      buf.writeLong(long)
      buf
    }

    def <<< (float: Float): ByteBuf = {
      buf.writeFloat(float)
      buf
    }

    def <<< (double: Double): ByteBuf = {
      buf.writeDouble(double)
      buf
    }

    def <<< (nbt: NBTTagCompound): ByteBuf = {
      ByteBufUtils.writeTag(buf, nbt)
      buf
    }

    def <<< (stack: ItemStack): ByteBuf = {
      ByteBufUtils.writeItemStack(buf, stack)
      buf
    }

    def <<< (str: String): ByteBuf = {
      ByteBufUtils.writeUTF8String(buf, str)
      buf
    }
  }
}