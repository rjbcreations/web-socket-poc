package websocket.poc.websocket.advancedtrade.model.level2.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.Instant

class AdvancedTradeLevel2MessageEventsUpdates(
    @JsonProperty("side") val side: String,
    @JsonProperty("event_time") val eventTime: Instant,
    @JsonProperty("price_level") val priceLevel: BigDecimal,
    @JsonProperty("new_quantity") val newQuantity: BigDecimal,
)
