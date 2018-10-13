package com.laidpack.sourcerer.sample


import android.os.Handler

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

typealias StatusChangeEvent = (status: ServerConnection.ConnectionStatus) -> Unit
typealias JsonChangeEvent = (json: String) -> Unit
class ServerConnection(private val serverUrl: String) {

    private lateinit var webSocket: WebSocket
    private lateinit var client: OkHttpClient
    private lateinit var messageHandler: Handler
    private lateinit var statusHandler: Handler

    enum class ConnectionStatus {
        DISCONNECTED,
        CONNECTED
    }
    private inner class SocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket?, response: Response?) {
            val m = statusHandler.obtainMessage(0, ConnectionStatus.CONNECTED)
            statusHandler.sendMessage(m)
        }

        override fun onMessage(webSocket: WebSocket?, text: String?) {
            val m = messageHandler.obtainMessage(0, text)
            messageHandler.sendMessage(m)
        }

        override fun onClosed(webSocket: WebSocket?, code: Int, reason: String?) {
            val m = statusHandler.obtainMessage(0, ConnectionStatus.DISCONNECTED)
            statusHandler.sendMessage(m)
        }

        override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
            disconnect()
        }
    }

    fun connect(statusListener: StatusChangeEvent, jsonListener: JsonChangeEvent) {
        client = OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        messageHandler = Handler { msg ->
            jsonListener(msg.obj as String)
            true
        }
        statusHandler = Handler { msg ->
            statusListener(msg.obj as ConnectionStatus)
            true
        }
        val request = Request.Builder()
                .url(serverUrl)
                .build()
        webSocket = client.newWebSocket(request, SocketListener())
    }

    fun disconnect() {
        webSocket.cancel()
        messageHandler.removeCallbacksAndMessages(null)
        statusHandler.removeCallbacksAndMessages(null)
    }

    fun sendMessage(message: String) {
        webSocket.send(message)
    }
}