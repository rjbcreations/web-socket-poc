package websocket.poc.websocket.advancedtrade.model

enum class AdvanceTradeWebsocketOrderStatusTypes {
    /** Order is not yet open */
    PENDING,
    /** Order is waiting to be fully filled */
    OPEN,
    /** Order is 100% filled */
    FILLED,
    /** Order was cancelled by user or system */
    CANCELLED,
    /** TWAP order was not filled by the expiry time */
    EXPIRED,
    /** Order cannot be placed at all */
    FAILED,
    /** Placeholder for the inevitability of coinbase change the api out from under us */
    UNKNOWN,
}