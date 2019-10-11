package stanic.stutils.server.utils

import net.md_5.bungee.api.chat.TextComponent
import net.minecraft.server.v1_8_R3.ChatComponentText
import net.minecraft.server.v1_8_R3.IChatBaseComponent
import net.minecraft.server.v1_8_R3.PacketPlayOutChat
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player

fun sendTitle(p: Player, title: String, subTitle: String) {
    val connection = (p as CraftPlayer).handle.playerConnection
    val titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '$title'}")
    val subtitleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '$subTitle'}")
    val titlePacket = PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON, 1, 25, 25)
    val subtitlePacket = PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleJSON)
    connection.sendPacket(titlePacket)
    connection.sendPacket(subtitlePacket)
}

fun sendActionBar(p: Player, send: String) {
    val packet = PacketPlayOutChat(ChatComponentText(send) as IChatBaseComponent, 2)
    (p as CraftPlayer).handle.playerConnection.sendPacket(packet)
}

fun sendChatObject(p: Player, obj: List<ChatObject>) {
    val list = java.util.ArrayList<TextComponent>()
    for (co in obj) {
        val c = TextComponent(co.message)
        c.hoverEvent = co.hoverEvent
        c.clickEvent = co.clickEvent
        list.add(c)
    }
    list.forEach { a -> p.spigot().sendMessage(a) }
}