package websocket.poc.websocket.advancedtrade.model.user.model

import com.fasterxml.jackson.annotation.JsonProperty

class AdvancedTradeUserMessageEvents(
    @JsonProperty("type") val type: String,
    @JsonProperty("orders") val orders: List<AdvancedTradeUserMessageEventsOrders>,
)
