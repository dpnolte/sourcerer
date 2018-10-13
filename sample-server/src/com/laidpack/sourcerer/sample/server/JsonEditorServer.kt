package com.laidpack.sourcerer.sample.server

import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import java.util.*

class JsonEditorServer {
    private val clients = Collections.synchronizedList(mutableListOf<WebSocketSession>())

    private var _json = """
[
    {
      "id":"greenText",
      "type":"textView",
      "attributes":{
        "text": "Test",
        "background": "green",
        "textColor": "white"
      },
      "children":[

      ]
   }
]
        """.trimIndent()

    var json: String
        get() {
            return synchronized(_json) {
                _json
            }
        }
        set(value) {
            synchronized(_json) {
                _json = value
            }
        }

    fun addClient(socket: WebSocketSession) {
        clients.add(socket)
    }

    fun removeClient(socket: WebSocketSession) {
        clients.remove(socket)
    }

    suspend fun broadcastJson() {
        val json = this.json
        clients.forEach { socket ->
            socket.send(Frame.Text(json))
        }
    }


}