package stanic.stutils.bukkit.message

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

fun CommandSender.send(msg: String) = sendMessage(msg)
fun CommandSender.send(msg: List<String>) {
    for (line in msg) sendMessage(line)
}
fun String.replaceColor() = ChatColor.translateAlternateColorCodes('&', this)
fun List<String>.replaceColor(): List<String> {
    val list = ArrayList<String>()
    for (line in this) list.add(ChatColor.translateAlternateColorCodes('&', line))

    return list
}

fun List<String>.replace(replacement: Pair<String, String>): List<String> {
    val list = ArrayList<String>()
    for (line in this) list.add(line.replace(replacement.first, replacement.second))

    return list
}