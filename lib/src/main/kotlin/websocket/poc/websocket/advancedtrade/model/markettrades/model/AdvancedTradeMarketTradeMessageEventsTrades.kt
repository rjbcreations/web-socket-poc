package websocket.poc.websocket.advancedtrade.model.markettrades.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.Instant

class AdvancedTradeMarketTradeMessageEventsTrades(
    @JsonProperty("trade_id") val tradeId: String,
    @JsonProperty("product_id") val productId: String,
    @JsonProperty("price") val price: BigDecimal,
    @JsonProperty("size") val size: BigDecimal,
    @JsonProperty("side") val side: String,
    @JsonProperty("time") val time: Instant,
)
