package stanic.stutils.server.utils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.net.URI
import java.net.URISyntaxException
import java.util.*

class SkullUtils {

    private val playerSkullItem: ItemStack
        get() = if (verify()) {
            ItemStack(Material.valueOf("PLAYER_HEAD"))
        } else {
            ItemStack(Material.valueOf("SKULL_ITEM"), 1, 3.toByte().toShort())
        }

    fun getSkull(url: String): ItemStack {
        val item = playerSkullItem

        return itemFromURL(item, url)
    }

    fun itemFromURL(item: ItemStack, url: String): ItemStack {
        notNull(item, "item")
        notNull(url, "url")

        return itemWithBase64(item, urlToBase64(url))
    }

    fun itemWithBase64(item: ItemStack, base64: String): ItemStack {
        notNull(item, "item")
        notNull(base64, "base64")

        val hashAsId = UUID(base64.hashCode().toLong(), base64.hashCode().toLong())
        return Bukkit.getUnsafe().modifyItemStack(
            item,
            "{SkullOwner:{Id:\"$hashAsId\",Properties:{textures:[{Value:\"$base64\"}]}}}"
        )
    }

    private fun verify(): Boolean {
        return try {
            Material.valueOf("PLAYER_HEAD")
            true
        } catch (e: IllegalArgumentException) {
            false
        }

    }

    private fun notNull(o: Any?, name: String) {
        if (o == null) {
            throw NullPointerException("$name should not be null!")
        }
    }

    private fun urlToBase64(url: String): String {

        val actualUrl: URI
        try {
            actualUrl = URI(url)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }

        val toEncode = "{\"textures\":{\"SKIN\":{\"url\":\"$actualUrl\"}}}"
        return Base64.getEncoder().encodeToString(toEncode.toByteArray())
    }

}