package websocket.poc.websocket.advancedtrade.model.status.model

import com.fasterxml.jackson.annotation.JsonProperty

class AdvancedTradeStatusMessageEvents(
    @JsonProperty("type") val type: String = "snapshot",
    @JsonProperty("products") val products : List<AdvancedTradeStatusMessageEventsProducts>,
)
