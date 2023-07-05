package websocket.poc.websocket.advancedtrade.model.status.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class AdvancedTradeStatusMessageEventsProducts(
    @JsonProperty("product_type") val productType: String,
    @JsonProperty("id") val id: String,
    @JsonProperty("base_currency") val baseCurrency: String,
    @JsonProperty("quote_currency") val quoteCurrency: String,
    @JsonProperty("base_increment") val baseIncrement: BigDecimal,
    @JsonProperty("quote_increment") val quoteIncrement: BigDecimal,
    @JsonProperty("display_name") val displayName: String,
    @JsonProperty("status") val status: String,
    @JsonProperty("status_message") val statusMessage: String,
    @JsonProperty("min_market_funds") val minMarketFunds: BigDecimal,
)
