package websocket.poc.websocket.advancedtrade.model.user.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.Instant

class AdvancedTradeUserMessageEventsOrders(
    @JsonProperty("order_id") val orderId: String,
    @JsonProperty("client_order_id") val clientOrderId: String,
    @JsonProperty("cumulative_quantity") val cumulativeQuantity: BigDecimal,
    @JsonProperty("leaves_quantity") val leavesQuantity: BigDecimal,
    @JsonProperty("avg_price") val avgPrice: BigDecimal,
    @JsonProperty("total_fees") val totalFees: BigDecimal,
    @JsonProperty("status") val status: String,
    @JsonProperty("product_id") val productId: String,
    @JsonProperty("creation_time") val creationTime: Instant,
    @JsonProperty("order_side") val orderSide: String,
    @JsonProperty("order_type") val orderType: String,
)
