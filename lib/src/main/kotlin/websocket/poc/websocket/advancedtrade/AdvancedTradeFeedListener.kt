package websocket.poc.websocket.advancedtrade

import websocket.poc.websocket.advancedtrade.model.heartbeat.AdvancedTradeHeartbeatMessage
import websocket.poc.websocket.advancedtrade.model.heartbeat.AdvancedTradeMarketTradeMessage
import websocket.poc.websocket.advancedtrade.model.level2.AdvancedTradeLevel2Message
import websocket.poc.websocket.advancedtrade.model.status.AdvancedTradeStatusMessage
import websocket.poc.websocket.advancedtrade.model.ticker.AdvancedTradeTickerBatchMessage
import websocket.poc.websocket.advancedtrade.model.ticker.AdvancedTradeTickerMessage
import websocket.poc.websocket.advancedtrade.model.user.AdvancedTradeUserMessage

abstract class AdvancedTradeFeedListener {
    abstract fun onOpen()
    open fun onClosing(code: Int, reason: String) {}
    open fun onClosed(code: Int, reason: String) {}
    open fun onFailure(throwable: Throwable) {}

    open fun onHeartbeatMessage(message: AdvancedTradeHeartbeatMessage) {}
    open fun onMarketTradeMessage(message: AdvancedTradeMarketTradeMessage) {}
    open fun onStatusMessage(message: AdvancedTradeStatusMessage) {}
    open fun onTickerMessage(message: AdvancedTradeTickerMessage) {}
    open fun onTickerBatchMessage(message: AdvancedTradeTickerBatchMessage) {}
    open fun onLevel2Message(message: AdvancedTradeLevel2Message) {}
    open fun onUserMessage(message: AdvancedTradeUserMessage) {}
}
