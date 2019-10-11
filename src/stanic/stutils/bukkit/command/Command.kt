package stanic.stutils.bukkit.command

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.plugin.java.JavaPlugin

fun JavaPlugin.command(
    name: String,
    perm: String? = null,
    executor: PluginCommand.(CommandSender, Array<String>) -> Unit) = getCommand(name)!!.run {
    setExecutor {sender, _, _, args ->
        executor(sender, args)
        true
    }
    permission = perm
}

fun JavaPlugin.command(
    name: String,
    perm: String? = null,
    executor: CommandSender.(Array<String>) -> Unit) = command(name, perm) {sender, args ->  sender.executor(args)}

fun CommandSender.command(cmd: String) = Bukkit.dispatchCommand(this, cmd)