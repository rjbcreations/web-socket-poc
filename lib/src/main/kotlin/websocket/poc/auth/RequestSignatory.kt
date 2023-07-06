package websocket.poc.auth

import java.time.Instant
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import org.apache.commons.codec.binary.Base64

class RequestSignatory(val apiSecret: String) {
    private val secretDecoded = Base64.decodeBase64(apiSecret)

    private val timestamp: String
        get() = Instant.now().epochSecond.toString()

    fun signForAdvancedTradeWebSocketRequest(channelName: String, productIds: List<String>): Pair<String, String> {
        return signForAdvancedTradeWebSocketRequest(timestamp, channelName, productIds)
    }

    private fun signForAdvancedTradeWebSocketRequest(
        timestamp: String,
        channelName: String,
        productIds: List<String>,
    ): Pair<String, String> {
        // Decode secret and init the MAC algorithm
        val keySpec = SecretKeySpec(apiSecret.toByteArray(), ALGORITHM)
//        val keySpec = SecretKeySpec(secretDecoded, ALGORITHM)
        val mac = Mac.getInstance(ALGORITHM)
        mac.init(keySpec)

        return productIds.joinToString(",") { it }.let {
            println("RequestSignatory - productIds: $it")
            println("RequestSignatory - timestamp: $timestamp")
            println("RequestSignatory - channelName: $channelName")

//            firstWay(timestamp, channelName, it, mac)
            //secondWayWithBase64EncodedStringSignature(timestamp, channelName, it, mac)
            Pair(timestamp, Hmac().digestHmacSHA256("$timestamp$channelName${productIds.joinToString(",")}", apiSecret))
        }
    }

    private fun firstWay(timestamp: String, channelName: String, it: String, mac: Mac): Pair<String, String> {
        // Create the signature for this request
        val what = (timestamp + channelName + it).toByteArray(charset = Charsets.UTF_8)
        val signature = mac.doFinal(what).toHexString()
        return Pair(timestamp, signature)
    }

    private fun secondWayWithBase64EncodedStringSignature(
        timestamp: String,
        channelName: String,
        it: String,
        mac: Mac
    ): Pair<String, String> {
        // Create the signature for this request
        val what = (timestamp + channelName + it).toByteArray(charset = Charsets.UTF_8)
        val signature = mac.doFinal(what)
        return Pair(timestamp, Base64.encodeBase64String(signature))
    }

    companion object {
        // https://docs.oracle.com/javase/8/docs/api/javax/crypto/Mac.html
        const val ALGORITHM = "HmacSHA256"
        private fun ByteArray.toHexString() = joinToString("") { String.format("%02x", it) }
    }
}
