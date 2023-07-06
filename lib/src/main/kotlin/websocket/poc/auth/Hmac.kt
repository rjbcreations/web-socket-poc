package websocket.poc.auth

import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class Hmac {
    fun digestHmacSHA256(
        msg: String,
        key: String
    ): String {
        val alg = "HmacSHA256"
        val signingKey = SecretKeySpec(key.toByteArray(), alg)
        val mac = Mac.getInstance(alg)
        mac.init(signingKey)

        val bytes = mac.doFinal(msg.toByteArray())
        return format(bytes)
    }

    private fun format(bytes: ByteArray): String {
        val formatter = Formatter()
        bytes.forEach { formatter.format("%02x", it) }
        return formatter.toString()
    }
}