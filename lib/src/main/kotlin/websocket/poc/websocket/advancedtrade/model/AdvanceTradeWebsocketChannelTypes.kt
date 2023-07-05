package websocket.poc.websocket.advancedtrade.model

enum class AdvanceTradeWebsocketChannelTypes(val channel: String) {
    /** The status channel sends all products and currencies on a preset interval */
    STATUS("status"),
    /** The ticker channel provides real-time price updates every time a match happens */
    TICKER("ticker"),
    /** The ticker_batch channel provides real-time price updates every 5000 milliseconds */
    TICKER_BATCH("ticker_batch"),
    /** The level2 channel provides all updates and easiest way to keep order book snapshot*/
    LEVEL2("level2"),
    /** The user channel only sends messages that include the authenticated user */
    USER("user"),
    /** The market_trades channel provides real-time updates every time a market trade happens */
    MARKET_TRADES("user"),
    /** The heart beat channel provides real-time pings from server to keep connections open */
    HEARTBEAT("heartbeats"),
    /** Placeholder for the inevitability of coinbase change the api out from under us */
    UNKNOWN("unknown"),
    ;

    companion object {
        fun fromString(type: String): AdvanceTradeWebsocketChannelTypes {
            return values().firstOrNull { it.channel == type } ?: UNKNOWN
        }
    }
}