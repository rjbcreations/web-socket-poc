package websocket.poc.websocket.advancedtrade.model.level2.model

import com.fasterxml.jackson.annotation.JsonProperty

class AdvancedTradeLevel2MessageEvents(
    @JsonProperty("type") val type: String = "snapshot",
    @JsonProperty("product_id") val productId: String,
    @JsonProperty("updates") val products: List<AdvancedTradeLevel2MessageEventsUpdates>,
)
