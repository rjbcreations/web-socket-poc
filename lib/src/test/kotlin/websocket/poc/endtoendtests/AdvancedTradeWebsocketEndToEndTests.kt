package websocket.poc.endtoendtests

import java.time.Instant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import websocket.poc.auth.RequestSignatory
import websocket.poc.websocket.advancedtrade.AdvancedTradeFeedListener
import websocket.poc.websocket.advancedtrade.AdvancedTradeWebSocketClient
import websocket.poc.websocket.advancedtrade.model.AdvanceTradeWebsocketChannelTypes
import websocket.poc.websocket.advancedtrade.model.heartbeat.AdvancedTradeHeartbeatMessage
import websocket.poc.websocket.advancedtrade.model.subscription.AdvancedTradeSubscribeRequest

class AdvancedTradeWebsocketEndToEndTests: EndToEndTestBase() {

    @Nested
    @DisplayName("Heartbeat Websocket Tests")
    inner class HeartbeatE2ETest : AdvancedTradeFeedListener() {
        private lateinit var heartbeatMessage: AdvancedTradeHeartbeatMessage
        private var totalMessages = 0
        private var numHeartBeats = 3
        override fun onOpen() {
            val productIds = listOf("BTC-USD", "ETH-USD")
            val (timestamp, signature) = requestSignatory.signForAdvancedTradeWebSocketRequest(
                channelName =AdvanceTradeWebsocketChannelTypes.HEARTBEAT.channel,
                productIds = productIds,
            )
            val request = AdvancedTradeSubscribeRequest(
                type = "subscribe",
                productIds = productIds,
                channel = AdvanceTradeWebsocketChannelTypes.HEARTBEAT.channel,
                key = COINBASE_API_ADVANCED_TRADE_KEY,
                signature = signature,
                timestamp = timestamp,
            )
            println("timestamp: ${Instant.ofEpochSecond(timestamp.toLong())}")
            println("request: $request")
            websocketClient.subscribeAuthenticated(request)
        }

        @Test
        fun `test AdvancedTradeWebsocketHeartbeatMessage`() {
            System.setProperty("user.timezone", "UTC");
            websocketClient.openFeed(this)
            waitUntil { totalMessages >= numHeartBeats}
            assertThat(heartbeatMessage).isNotNull
        }

        override fun onHeartbeatMessage(message: AdvancedTradeHeartbeatMessage) {

            println("Heartbeat message received: $message")
            totalMessages += 1
            if (totalMessages >= numHeartBeats) {
                println("Disconnecting after $numHeartBeats heartbeats.")
                websocketClient.closeFeed()
                websocketClient.close()
            }
        }

        override fun onClosing(code: Int, reason: String) {
            val message = reason.ifBlank { "No reason was given." }
            println("On closing ($code): $message")
        }

        override fun onClosed(code: Int, reason: String) {
            val message = reason.ifBlank { "No reason was given." }
            println("On closed ($code): $message")
        }

        override fun onFailure(throwable: Throwable) {
            println("On failure: ${throwable.message}")
            throwable.printStackTrace()
        }
    }

    companion object {
        val websocketClient = AdvancedTradeWebSocketClient(
            feedEndpoint = WEBSOCKET_ENDPOINT_PRODUCTION_ADVANCE_TRADE,
            okHttpClient = okHttpClient,
        )
        val requestSignatory = RequestSignatory(
            apiSecret = COINBASE_API_ADVANCED_TRADE_SECRET,
        )
    }
}