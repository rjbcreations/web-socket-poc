package websocket.poc.websocket.advancedtrade.model.tickerbatch.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class AdvancedTradeTickerBatchMessageEventsTickers(
    @JsonProperty("type") val type: String,
    @JsonProperty("product_id") val productId: String,
    @JsonProperty("price") val price: BigDecimal,
    @JsonProperty("volume_24_h") val volume24h: BigDecimal,
    @JsonProperty("low_24_h") val low24h: BigDecimal,
    @JsonProperty("high_24_h") val high24h: BigDecimal,
    @JsonProperty("low_52_w") val low52w: BigDecimal,
    @JsonProperty("high_52_w") val high52w: BigDecimal,
    @JsonProperty("price_percent_chg_24_h") val pricePercentChg24H: BigDecimal,
)
