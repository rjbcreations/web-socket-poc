package websocket.poc.websocket.advancedtrade

import java.io.Closeable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import websocket.poc.websocket.advancedtrade.model.subscription.AdvancedTradeSubscribeRequest
import websocket.poc.websocket.advancedtrade.model.subscription.AdvancedTradeUnsubscribeRequest

class AdvancedTradeWebSocketClient(
    private val feedEndpoint: String = WEBSOCKET_PRODUCTION,
    private val okHttpClient: OkHttpClient = OkHttpClient(),
) : Closeable {

    private lateinit var feed: AdvancedTradeFeed
    private lateinit var webSocket: WebSocket

    init {
        check(feedEndpoint.isNotBlank()) { "Websocket endpoint cannot be empty." }
    }

    override fun close() {
        // https://github.com/square/okhttp/issues/4029
        // https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html
        okHttpClient.dispatcher.executorService.shutdown()
        okHttpClient.connectionPool.evictAll()
        okHttpClient.cache?.close()
    }

    /*
     * === Websocket logic ===
     */

    fun openFeed(feedListener: AdvancedTradeFeedListener) {
        feed = AdvancedTradeFeed(feedListener)
        val request = Request.Builder().url(feedEndpoint).build()
        webSocket = okHttpClient.newWebSocket(request, feed)
    }

    /**
     * This function attempts to initiate a graceful shutdown of the web socket, it doesn't close the
     * underlying OkHttp client. If you need to do so, which is optional, you need to additionally
     * call {@link #close()}.
     */
    fun closeFeed(code: Int = 1000, reason: String = ""): Boolean = webSocket.close(code, reason)

    fun subscribeAuthenticated(request: AdvancedTradeSubscribeRequest): Boolean = subscribe(
        encoded = CoinbaseConverterFactory.mapper.writeValueAsString(request).also {
            println("subscribeAuthenticated: $it")
        }
    )

    private fun subscribe(encoded: String): Boolean {
        return webSocket.send(encoded)
    }

    fun unsubscribe(request: AdvancedTradeUnsubscribeRequest): Boolean {
        val encoded = CoinbaseConverterFactory.mapper.writeValueAsString(request)
        return webSocket.send(encoded)
    }

    companion object {
        const val WEBSOCKET_PRODUCTION = "wss://advanced-trade-ws.coinbase.com"

        const val CHANNEL_STATUS = "status"
        const val CHANNEL_TICKER = "ticker"
        const val CHANNEL_TICKER_BATCH = "ticker_batch"
        const val CHANNEL_LEVEL2 = "level2"
        const val CHANNEL_USER = "user"
        const val CHANNEL_HEARTBEAT = "heartbeat"
        const val CHANNEL_MARKET_TRADES = "market_trades"
    }
}
