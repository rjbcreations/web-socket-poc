# Web Socket Proof of Concept

1. You'll need to install the following environment variables:

```
export COINBASE_API_KEY="<key>"
export COINBASE_API_SECRET="<secret>"
```

1. run `./gradlew clean build --info` to run the test containing the web socket connection.
2. The test will connect to the web socket and subscribe to the heartbeat channel for the `BTC-USD` and `ETH-USD` products.
3. I have logged some details to assist with debugging
4. Tests currently times out after 10 seconds
