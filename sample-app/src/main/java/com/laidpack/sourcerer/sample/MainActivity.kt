package com.laidpack.sourcerer.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.laidpack.sourcerer.services.SourcererComponent

class MainActivity : AppCompatActivity() {
    // private val SERVER_URL = "ws://10.0.2.2:9000/mobile "// if used in emulators:
    private val SERVER_URL = "ws://192.168.220.122:9000/mobile" // if used on device @ local network
    private lateinit var serverConnection: ServerConnection
    private lateinit var services : SourcererComponent
    private var connectionStatus = ServerConnection.ConnectionStatus.DISCONNECTED
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.activity_main)

        val connectionStatusTextView = this.findViewById<TextView>(R.id.connectionStatus)
        connectionStatusTextView.setText(R.string.connection_status_connecting)
        connectionStatusTextView.setOnClickListener {
            connectionStatusTextView.setText(R.string.connection_status_connecting)
            connect()
        }

        services = SourcererComponent.bootstrap(this)
        serverConnection = ServerConnection(SERVER_URL)
        connect()
    }

    private fun connect() {
        serverConnection.disconnect()
        serverConnection.connect(this::onConnectionStatusChange, this::onJsonChange)
    }

    private fun onConnectionStatusChange(status: ServerConnection.ConnectionStatus) {
        val connectionStatusTextView = this.findViewById<TextView>(R.id.connectionStatus)
        connectionStatus = status
        if (connectionStatus == ServerConnection.ConnectionStatus.CONNECTED) {
            connectionStatusTextView.setText(R.string.connection_status_connected)
        } else {
            connectionStatusTextView.setText(R.string.connection_status_disconnected)
        }
    }

    private fun onJsonChange(json: String) {
        val container = this.findViewById<ViewGroup>(R.id.container)
        try {
            services.inflater.inflate(this, json, container)
        } catch (e: Exception) {
            services.inflater.inflate(this, errorJson(e), container)
        }
    }

    companion object {
        val errorJson : (exception: java.lang.Exception) -> String = { exception -> """
[
    {
        "id": "errorContainer",
        "type": "linearLayout",
        "attributes": {
          "orientation": "vertical",
          "background": "red"
        },
        "children": [
          "errorTitle",
          "errorMessage",
          "errorStacktrace"
        ]
    },
    {
      "id":"errorTitle",
      "type":"textView",
      "attributes":{
        "text": "Error",
        "layout_height": "wrap_content",
        "textSize": "10sp",
        "paddingTop": "30dp",
        "textColor": "white",
        "paddingLeft": "10dp"
      },
      "children":[

      ]
    },
    {
      "id":"errorMessage",
      "type":"textView",
      "attributes":{
        "text": "${exception.message}",
        "layout_height": "wrap_content",
        "textSize": "7sp",
        "paddingTop": "10dp",
        "textColor": "white",
        "paddingLeft": "10dp"
      },
      "children":[

      ]
   },
   {
      "id":"errorStacktrace",
      "type":"textView",
      "attributes":{
        "text": "${exception.stackTrace.joinToString("\n") { "${it.className}.${it.methodName}: ${it.lineNumber}"}}",
        "layout_height": "wrap_content",
        "layout_width": "match_parent",
        "paddingTop": "10dp",
        "textColor": "white",
        "paddingLeft": "10dp"
      },
      "children":[

      ]
   }
]
        """.trimIndent()}
    }
}
