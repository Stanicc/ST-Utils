package stanic.stutils.bukkit.message

import org.bukkit.command.CommandSender

fun CommandSender.send(msg: String) = sendMessage(msg)