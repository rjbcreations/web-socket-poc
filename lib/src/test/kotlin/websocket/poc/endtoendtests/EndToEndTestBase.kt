package websocket.poc.endtoendtests

import mu.KLogging
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.awaitility.Awaitility
import org.awaitility.Duration.ONE_HUNDRED_MILLISECONDS
import org.awaitility.Duration.TEN_SECONDS

abstract class EndToEndTestBase : KLogging() {
    companion object : KLogging() {
        val COINBASE_API_ADVANCED_TRADE_KEY: String = getenvOrEmpty("COINBASE_API_KEY")
        val COINBASE_API_ADVANCED_TRADE_SECRET: String = getenvOrEmpty("COINBASE_API_SECRET")
        const val WEBSOCKET_ENDPOINT_PRODUCTION_ADVANCE_TRADE = "wss://advanced-trade-ws.coinbase.com"

        private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor { message -> logger.info(message) }
            .apply { level = HttpLoggingInterceptor.Level.BODY }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        private fun getenvOrEmpty(name: String): String = System.getenv(name) ?: ""


        fun waitUntil(predicate: () -> Boolean): Unit =
            Awaitility.await()
                .pollDelay(ONE_HUNDRED_MILLISECONDS)
                .atMost(TEN_SECONDS)
                .until(predicate)
    }
}
