package com.laidpack.sourcerer.sample.server

import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import java.util.*

class JsonEditorServer {
    private val clients = Collections.synchronizedList(mutableListOf<WebSocketSession>())

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

    private var _json = simpleExample

    suspend fun loadAdvancedExample() {
        json = advancedExample
        broadcastJson()
    }

    suspend fun loadSimpleExample() {
        json = simpleExample
        broadcastJson()
    }

    companion object {
        private val simpleExample = """
[
  {
    "id": "simpleExample",
    "type": "linearLayout",
    "attributes": {
      "orientation": "vertical",
      "background": "green"
    },
    "children": [
      "header",
      "subtitle",
      "body"
    ]
  },
  {
    "id": "header",
    "type": "textView",
    "attributes": {
      "layout_height": "300dp",
      "text": "Hello..!",
      "textSize": "20sp",
      "textColor": "white",
      "paddingTop": "200dp",
      "paddingLeft": "50dp"
    },
    "children": []
  },
  {
    "id": "subtitle",
    "type": "textView",
    "attributes": {
      "layout_height": "wrap_content",
      "text": "Is it me that you are looking for?",
      "textSize": "12sp",
      "textColor": "white",
      "paddingLeft": "10dp"
    },
    "children": []
  },
  {
    "id": "body",
    "type": "textView",
    "attributes": {
      "layout_height": "wrap_content",
      "text": "You can change this layout by (remotely) updating the json without recompiling",
      "textColor": "white",
      "paddingLeft": "10dp"
    },
    "children": []
  }
]
        """.trimIndent()

        private val advancedExample = """
[
  {
    "id": "drawerLayout",
    "type": "drawerLayout",
    "attributes": {
      "layout_width": "match_parent",
      "layout_height": "match_parent"
    },
    "children": [
      "coordinatorLayout",
      "navigationView"
    ]
  },
  {
    "id": "coordinatorLayout",
    "type": "coordinatorLayout",
    "attributes": {
      "background": "#FEFEFE",
      "layout_width": "match_parent",
      "layout_height": "match_parent"
    },
    "children": [
      "appBarLayout",
      "nestedScrollView"
    ]
  },
  {
    "id": "appBarLayout",
    "type": "appBarLayout",
    "attributes": {
      "layout_height": "290dp",
      "layout_width": "match_parent"
    },
    "children": [
      "collapsingToolbar"
    ]
  },
  {
    "id": "collapsingToolbar",
    "type": "collapsingToolbarLayout",
    "attributes": {
      "layout_height": "match_parent",
      "layout_width": "match_parent",
      "expandedTitleGravity": "top",
      "title": "Sourcerer Playground",
      "expandedTitleMarginTop": "235dp",
      "expandedTitleMarginEnd": "48dp",
      "expandedTitleMarginStart": "48dp",
      "collapsedTitleTextAppearance": "@style/titleTextAppearance",
      "expandedTitleTextAppearance": "@style/expandedTitleTextAppearance",
      "contentScrim": "#008577",
      "statusBarScrim": "#008577",
      "layout_scrollFlags": "scroll|exitUntilCollapsed|snap"
    },
    "children": [
      "headerImage",
      "toolbar"
    ]
  },
  {
    "id": "headerImage",
    "type": "imageView",
    "attributes": {
      "layout_scrollFlags": "scroll|enterAlways|enterAlwaysCollapsed",
      "src": "@drawable/boat",
      "scaleType": "centerCrop",
      "layout_collapseMode": "parallax",
      "layout_collapseParallaxMultiplier": 0.5,
      "layout_height": "300dp",
      "layout_width": "wrap_content",
      "minHeight": "300dp"
    },
    "children": []
  },
  {
    "id": "toolbar",
    "type": "appcompat.toolbar",
    "attributes": {
      "src": "@drawable/ic_launcher_background",
      "titleMarginStart": "10dp",
      "titleMarginTop": "25dp",
      "titleTextColor": "white",
      "title": "Sourcerer Playground 1223",
      "layout_height": "60dp",
      "layout_width": "match_parent",
      "layout_collapseMode": "pin"
    },
    "children": []
  },
  {
    "id": "nestedScrollView",
    "type": "nestedScrollView",
    "attributes": {
      "layout_width": "match_parent",
      "layout_height": "match_parent",
      "layout_behavior": "@string/appbar_scrolling_view_behavior"
    },
    "children": [
      "linearLayout"
    ]
  },
  {
    "id": "linearLayout",
    "type": "linearLayout",
    "attributes": {
      "layout_width": "match_parent",
      "layout_height": "match_parent",
      "orientation": "vertical"
    },
    "children": [
      "firstText",
      "secondText",
      "imageView"
    ]
  },
  {
    "id": "firstText",
    "type": "textView",
    "attributes": {
      "text": "< Swipe from left edge to show drawer",
      "background": "#EFEFEF",
      "textColor": "gray",
      "paddingTop": "40dp",
      "paddingBottom": "40dp",
      "layout_height": "wrap_content"
    },
    "children": []
  },
  {
    "id": "secondText",
    "type": "textView",
    "attributes": {
      "text": "Scroll down to collapse toolbar",
      "background": "white",
      "textColor": "gray",
      "paddingTop": "60dp",
      "paddingBottom": "60dp",
      "layout_height": "wrap_content"
    },
    "children": []
  },
  {
    "id": "imageView",
    "type": "imageView",
    "attributes": {
      "src": "@drawable/ic_launcher_background",
      "layout_height": "200dp",
      "layout_width": "match_parent"
    },
    "children": []
  },
  {
    "id": "navigationView",
    "type": "navigationView",
    "attributes": {
      "layout_height": "match_parent",
      "layout_width": "wrap_content",
      "layout_gravity": "start|left",
      "background": "#f2fbf4"
    },
    "children": [
      "navigationViewText"
    ]
  },
  {
    "id": "navigationViewText",
    "type": "textView",
    "attributes": {
      "text": "I am inside the drawer",
      "background": "white",
      "textColor": "gray",
      "paddingTop": "60dp",
      "paddingRight": "20dp",
      "paddingBottom": "60dp",
      "layout_height": "wrap_content"
    },
    "children": []
  }
]
        """.trimIndent()
    }

}