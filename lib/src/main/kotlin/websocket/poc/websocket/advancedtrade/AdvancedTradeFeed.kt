package websocket.poc.websocket.advancedtrade

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import websocket.poc.CoinbaseException
import websocket.poc.websocket.advancedtrade.model.heartbeat.AdvancedTradeHeartbeatMessage
import websocket.poc.websocket.advancedtrade.model.heartbeat.AdvancedTradeMarketTradeMessage
import websocket.poc.websocket.advancedtrade.model.level2.AdvancedTradeLevel2Message
import websocket.poc.websocket.advancedtrade.model.status.AdvancedTradeStatusMessage
import websocket.poc.websocket.advancedtrade.model.ticker.AdvancedTradeTickerBatchMessage
import websocket.poc.websocket.advancedtrade.model.ticker.AdvancedTradeTickerMessage
import websocket.poc.websocket.advancedtrade.model.user.AdvancedTradeUserMessage

internal class AdvancedTradeFeed(
    private val feedListener: AdvancedTradeFeedListener,
    private val mapper: ObjectMapper = CoinbaseConverterFactory.mapper
) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        feedListener.onOpen()
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        val decoded = mapper.readValue<JsonNode>(text)
        when (val type = decoded.get("type").asText()) {
            "error" -> handleErrorMessage(decoded)
            "heartbeat" -> handleHeartbeatMessage(text)
            "market_trades" -> handleMarketTradeMessage(text)
            "status" -> handleStatusMessage(text)
            "ticker" -> handleTickerMessage(text)
            "ticker_batch" -> handleTickerBatchMessage(text)
            "l2update" -> handleLevel2UpdateMessage(text)
            "user" -> handleUserMessage(text)
            else -> handleUnknownMessage(type, text)
        }
    }


    private fun handleErrorMessage(decoded: JsonNode) {
        val message = decoded.get("message").asText()
        val error = CoinbaseException("Error message received: $message")
        feedListener.onFailure(error)
    }

    private fun handleHeartbeatMessage(text: String) {
        val decoded = mapper.readValue<AdvancedTradeHeartbeatMessage>(text)
        feedListener.onHeartbeatMessage(decoded)
    }

    private fun handleMarketTradeMessage(text: String) {
        val decoded = mapper.readValue<AdvancedTradeMarketTradeMessage>(text)
        feedListener.onMarketTradeMessage(decoded)
    }

    private fun handleStatusMessage(text: String) {
        val decoded = mapper.readValue<AdvancedTradeStatusMessage>(text)
        feedListener.onStatusMessage(decoded)
    }

    private fun handleTickerMessage(text: String) {
        val decoded = mapper.readValue<AdvancedTradeTickerMessage>(text)
        feedListener.onTickerMessage(decoded)
    }

    private fun handleTickerBatchMessage(text: String) {
        val decoded = mapper.readValue<AdvancedTradeTickerBatchMessage>(text)
        feedListener.onTickerBatchMessage(decoded)
    }

    private fun handleLevel2UpdateMessage(text: String) {
        val decoded = mapper.readValue<AdvancedTradeLevel2Message>(text)
        feedListener.onLevel2Message(decoded)
    }

    private fun handleUserMessage(text: String) {
        val decoded = mapper.readValue<AdvancedTradeUserMessage>(text)
        feedListener.onUserMessage(decoded)
    }

    private fun handleUnknownMessage(type: String, text: String) {
        // Fail silently. New message types can be added at any point in time
        // and clients are expected to ignore messages they do not support.
        val error = CoinbaseException("Unsupported message type received ($type): $text")
        feedListener.onFailure(error)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        val error = CoinbaseException("Unsupported binary message received: $bytes")
        feedListener.onFailure(error)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        feedListener.onClosing(code, reason)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        feedListener.onClosed(code, reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        feedListener.onFailure(t)
    }

    companion object {

    }
}
