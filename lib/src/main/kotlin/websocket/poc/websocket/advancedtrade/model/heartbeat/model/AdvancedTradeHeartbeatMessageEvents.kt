package websocket.poc.websocket.advancedtrade.model.heartbeat.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigInteger
import java.time.Instant

class AdvancedTradeHeartbeatMessageEvents(
    @JsonProperty("current_time") val currentTime: Instant,
    @JsonProperty("heartbeat_counter") val heartbeatCounter: BigInteger,
)
