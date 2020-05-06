package stanic.stutils.server

import java.net.HttpURLConnection
import java.net.URL

interface ShopResponse {
    fun onAccepted()
    fun onRejected()
    fun onError()
}

fun checkValidate(email: String, license: String, shopResponse: ShopResponse) {
    val url = URL("http://st-shop.xyz:8080/validate?email=$email&license=$license")
    with(url.openConnection() as HttpURLConnection) {
        requestMethod = "POST"
        doOutput = true

        try {
            println("Validating the product...")
            println("User email: $email")
            println("License: $license")
            println("")

            when (responseCode) {
                202 -> shopResponse.onAccepted()
                401 -> shopResponse.onRejected()
                else -> shopResponse.onError()
            }
        } catch (e: Exception) {
            shopResponse.onError()
        }
    }
}