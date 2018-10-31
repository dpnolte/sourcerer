package com.laidpack.sourcerer.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.laidpack.sourcerer.services.SourcererComponent

class MainActivity : AppCompatActivity() {
    // private val SERVER_URL = "ws://10.0.2.2:9000/mobile "// if used in emulators:
    private val SERVER_URL = "ws://192.168.220.122:9000/mobile" // if used on device @ local network
    private lateinit var serverConnection: ServerConnection
    private lateinit var services : SourcererComponent
    private var connectionStatus = ServerConnection.ConnectionStatus.DISCONNECTED
    private var drawerLayout: DrawerLayout? = null
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
            val inflatedLayoutMap = services.inflater.inflate(this, json, container)
            drawerLayout = inflatedLayoutMap.findFirstViewOfType(this)
            inflatedLayoutMap.findFirstViewOfType<Toolbar>(this)?.let {toolbar ->
                setSupportActionBar(toolbar)
                val actionbar = supportActionBar
                actionbar?.apply {
                    setDisplayHomeAsUpEnabled(true)
                    setHomeAsUpIndicator(R.drawable.baseline_menu_white_24)
                }

            }
        } catch (e: Exception) {
            services.inflater.inflate(this, errorJson(e), container)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout?.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
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
