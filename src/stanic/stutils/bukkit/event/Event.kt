package stanic.stutils.bukkit.event

import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

inline fun <reified T: Event> JavaPlugin.event(
    priority: EventPriority = EventPriority.NORMAL,
    cancelEvent: Boolean = false,
    crossinline callBack: (T) -> Unit) = Bukkit.getServer().pluginManager.registerEvent(
    T::class.java,
    object : Listener {},
    priority,
    {_, it -> if (it is T) callBack(it)},
    this,
    cancelEvent
)
