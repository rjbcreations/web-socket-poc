package websocket.poc.websocket.advancedtrade.model.markettrades.model

import com.fasterxml.jackson.annotation.JsonProperty

class AdvancedTradeMarketTradeMessageEvents(
    @JsonProperty("type") val type: String,
    @JsonProperty("trades") val trades: List<AdvancedTradeMarketTradeMessageEventsTrades>,
)
