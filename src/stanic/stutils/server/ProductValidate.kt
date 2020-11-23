package stanic.stutils.server

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import stanic.stutils.bukkit.message.send
import java.net.HttpURLConnection
import java.net.URL

interface ShopResponse {
    fun onAccepted()
    fun onRejected()
    fun onError()
}

fun checkValidate(email: String, license: String, name: String, version: String, shopResponse: ShopResponse) {
    val url = URL("http://st-shop.xyz:8080/validate?email=$email&license=$license&name=$name&version=$version")
    with(url.openConnection() as HttpURLConnection) {
        requestMethod = "POST"
        doOutput = true

        try {
            println("Validating the plugin...")
            println("Email: $email")
            println("License: $license")
            println("")

            when (responseCode) {
                200 -> {
                    Bukkit.getConsoleSender().send("${ChatColor.YELLOW}[ST-Shop] ${ChatColor.GRAY}- ${ChatColor.GREEN}$name")
                    Bukkit.getConsoleSender().send("The credentials are correct")
                    Bukkit.getConsoleSender().send("thanks for the preference!")
                    Bukkit.getConsoleSender().send("")
                    Bukkit.getConsoleSender().send("${ChatColor.RED}WARN:")
                    Bukkit.getConsoleSender().send("")
                    Bukkit.getConsoleSender().send("${ChatColor.GREEN}A new version of the plugin is available!")
                    Bukkit.getConsoleSender().send("For more information visit: https://st-shop.xyz/info?name=$name")
                    Bukkit.getConsoleSender().send("")
                    shopResponse.onAccepted()
                }
                202 -> {
                    Bukkit.getConsoleSender().send("${ChatColor.YELLOW}[ST-Shop] ${ChatColor.GRAY}- ${ChatColor.GREEN}$name")
                    Bukkit.getConsoleSender().send("The credentials are correct")
                    Bukkit.getConsoleSender().send("thanks for the preference!")
                    Bukkit.getConsoleSender().send("")
                    shopResponse.onAccepted()
                }
                401 -> {
                    Bukkit.getConsoleSender().send("${ChatColor.YELLOW}[ST-Shop] ${ChatColor.GRAY}- ${ChatColor.DARK_RED}$name")
                    Bukkit.getConsoleSender().send("The credentials are incorrect")
                    Bukkit.getConsoleSender().send("so the plugin will be disabled.")
                    Bukkit.getConsoleSender().send("")
                    Bukkit.getConsoleSender().send("Check the email and license in the config or authorize this address to try again")
                    Bukkit.getConsoleSender().send("")
                    shopResponse.onRejected()
                }
                else -> {
                    Bukkit.getConsoleSender().send("${ChatColor.YELLOW}[ST-Shop] ${ChatColor.GRAY}- ${ChatColor.DARK_RED}$name")
                    Bukkit.getConsoleSender().send("")
                    Bukkit.getConsoleSender().send("An error occurred while checking the plugin")
                    Bukkit.getConsoleSender().send("so the plugin will be disabled.")
                    Bukkit.getConsoleSender().send("")
                    Bukkit.getConsoleSender().send("Try again later or contact us through our Discord:")
                    Bukkit.getConsoleSender().send("https://discord.com/invite/Y2rCdss")
                    Bukkit.getConsoleSender().send("")
                    shopResponse.onError()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            shopResponse.onError()
        }
    }
}