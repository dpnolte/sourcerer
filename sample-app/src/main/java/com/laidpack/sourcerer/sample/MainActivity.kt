package com.laidpack.sourcerer.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.laidpack.sourcerer.services.SourcererComponent

class MainActivity : AppCompatActivity() {
    // if used in emulators: "ws://10.0.2.2:9000/mobile"
    private val SERVER_URL = "ws://192.168.220.122:9000/mobile"
    private lateinit var serverConnection: ServerConnection
    private lateinit var services : SourcererComponent
    private var connectionStatus = ServerConnection.ConnectionStatus.DISCONNECTED
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.activity_main)
        val container = this.findViewById<ViewGroup>(R.id.container)
        val connectionStatusTextView = this.findViewById<TextView>(R.id.connectionStatus)
        connectionStatusTextView.text = "Connection status: $connectionStatus"

        services = SourcererComponent.bootstrap(this, ViewGroup.LayoutParams::class)
        serverConnection = ServerConnection(SERVER_URL)
        serverConnection.connect(
                { status ->
                    connectionStatus = status
                    connectionStatusTextView.text = "Connection status: $connectionStatus"
                },
                { json ->
                    try {
                        services.inflater.inflate(this, json, container)
                    } catch (e: Exception) {
                        services.inflater.inflate(this, errorJson(e), container)
                    }
                }
        )
        // serverConnection.sendMessage("I'm listening...")


    }
    companion object {
        val errorJson : (exception: java.lang.Exception) -> String = { exception -> """
[
    {
      "id":"errorText",
      "type":"textView",
      "attributes":{
        "text": "Error!\n${exception.message}\n\nStacktrace:${exception.stackTrace}",
        "background": "red",
        "textColor": "white"
      },
      "children":[

      ]
   }
]
        """.trimIndent()}

        val nestedScrollViewContent1 = """
[
   {
      "id":"myLinearLayout",
      "type":"linearLayout",
      "attributes":{
        "orientation": ${LinearLayout.VERTICAL}
      },
      "children":[
         "myTextView",
         "myButton"
      ]
   },
   {
      "id":"myTextView",
      "type":"textView",
      "attributes":{
        "text": "Now I'm a ugly duckling. Please prettify me",
        "background": "red",
        "layout_width": "wrap_content",
        "layout_height": "wrap_content"
      },
      "children":[

      ]
   },
   {
      "id":"myButton",
      "type":"button",
      "attributes":{
        "text": "Prettify!",
        "background": "#CDC",
        "layout_height": "wrap_content"
      },
      "children":[

      ]
   }
]
        """.trimIndent()

        val nestedScrollViewContent2 = """
[
   {
      "id":"myLinearLayout",
      "type":"linearLayout",
      "attributes":{
        "orientation": ${LinearLayout.VERTICAL}
      },
      "children":[
         "myTextView",
         "myButton"
      ]
   },
   {
      "id":"myTextView",
      "type":"textView",
      "attributes":{
        "text": "Now I'm yellow. I like it!",
        "background": "yellow",
        "layout_width": "wrap_content",
        "layout_height": "wrap_content"
      },
      "children":[

      ]
   },
   {
      "id":"myButton",
      "type":"button",
      "attributes":{
        "text": "If I could turn back the hands of time...",
        "background": "#CDC",
        "layout_height": "wrap_content"
      },
      "children":[

      ]
   }
]
        """.trimIndent()

        val contentViewJson = """
[
   {
      "id":"drawerLayout",
      "type":"drawerLayout",
      "attributes":{

      },
      "children":[
         "coordinatorLayout",
         "navigationView"
      ]
   },
   {
      "id":"coordinatorLayout",
      "type":"coordinatorLayout",
      "attributes":{

      },
      "children":[
         "appBarLayout",
         "nestedScrollView"
      ]
   },
   {
      "id":"appBarLayout",
      "type":"appBarLayout",
      "attributes":{
         "fitsSystemWindow":true,
         "layout_height": "wrap_content"
      },
      "children":[
         "toolbar"
      ]
   },
   {
      "id":"toolbar",
      "type":"appcompat.toolbar",
      "attributes":{
         "title":"Navigation Playground",
         "layout_height": "wrap_content"
      },
      "children":[

      ]
   },
   {
      "id":"nestedScrollView",
      "type":"nestedScrollView",
      "attributes":{

      },
      "children":[
         "container"
      ]
   },
   {
      "id":"container",
      "type":"frameLayout",
      "attributes":{

      },
      "children":[
        "myLinearLayout"
      ]
   },
   {
      "id":"myLinearLayout",
      "type":"linearLayout",
      "attributes":{
        "orientation": ${LinearLayout.VERTICAL}
      },
      "children":[
         "textView2",
         "myButton"
      ]
   },
   {
      "id":"textView2",
      "type":"textView",
      "attributes":{
        "text": "First text view",
        "background": "red"
      },
      "children":[

      ]
   },
   {
      "id":"myButton",
      "type":"button",
      "attributes":{
        "text": "Start!",
        "background": "#CDC",
        "layout_height": "wrap_content"
      },
      "children":[

      ]
   },
   {
      "id":"navigationView",
      "type":"navigationView",
      "attributes":{
        "layout_width": "wrap_content",
        "layout_height": "match_parent",
        "layout_gravity": "start|left"
      },
      "children":[
        "textView3"
      ]
   },
   {
      "id":"textView3",
      "type":"textView",
      "attributes":{
        "text": "Navigation View Content",
        "background": "red"
      },
      "children":[

      ]
   }
]
        """.trimMargin()
    }
}
