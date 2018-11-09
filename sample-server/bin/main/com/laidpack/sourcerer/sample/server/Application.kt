package com.laidpack.sourcerer.sample.server

import io.ktor.application.*
import io.ktor.client.features.HttpRedirect
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.features.HttpsRedirect
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import io.ktor.http.cio.websocket.CloseReason
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import io.ktor.http.content.*
import kotlinx.html.*
import kotlinx.css.*
import io.ktor.locations.*
import io.ktor.util.raw
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.css.Float
import java.io.File
import java.time.Duration

fun main(args: Array<String>): Unit = io.ktor.server.netty.DevelopmentEngine.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.main() {
    JsonEditorApplication().apply { main() }
}

class JsonEditorApplication {
    private val server = JsonEditorServer()

    fun Application.main() {
        install(WebSockets) {
            //pingPeriod = Duration.ofSeconds(60) // Disabled (null) by default
            //timeout = Duration.ofSeconds(15)
            //maxFrameSize = Long.MAX_VALUE // Disabled (max value). The connection will be closed if surpassed this length.
            //masking = false
        }
        install(DefaultHeaders)
        install(CallLogging)
        //install(Locations)

        routing {
            webSocket("mobile") {
                server.addClient(this)
                // send current json
                this.send(Frame.Text(server.json))
                try {
                    // We starts receiving messages (frames).
                    // Since this is a coroutine. This coroutine is suspended until receiving frames.
                    // Once the connection is closed, this consumeEach will finish and the code will continue.
                    incoming.consumeEach { frame ->
                        // Frames can be [Text], [Binary], [Ping], [Pong], [Close].
                        // We are only interested in textual messages, so we filter it.
                        if (frame is Frame.Text) {
                            // whatever we receiving, respond with current json
                            outgoing.send(Frame.Text(server.json))
                        }
                    }
                } finally {
                    // Either if there was an error, of it the connection was closed gracefully.
                    server.removeClient(this)
                }
            }

            get("/") {
                call.respondHtml {
                    head {
                        meta {
                            httpEquiv = "Content-Type"
                            content = "text/html;charset=utf-8"
                        }
                        styleLink("/static/jsoneditor.css")
                        script(src="/static/jsoneditor.js") {}
                    }
                    body {
                        h3 {
                            style { margin = "0" }
                            +"JSON Editor:"
                        }
                        div {
                            style {
                                position = Position.relative
                                height = LinearDimension("55px")
                            }
                            p {
                                style {
                                    float = Float.left
                                }
                                +"edit json and update app without recompiling"
                            }
                            button {
                                style {
                                    float = Float.left
                                    height = LinearDimension("30px")
                                    marginLeft = LinearDimension("10px")
                                    marginTop = LinearDimension("10px")
                                }
                                onClick = """
                                            |var json = window.editor1.get();
                                            |var xhttp = new XMLHttpRequest();
                                            |xhttp.open("POST", "/update", true);
                                            |xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                            |xhttp.send("json="+JSON.stringify(json));
                                        """.trimMargin()
                                + "Update mobile app"
                            }
                        }
                        ul {
                            style {
                                position = Position.relative
                                height = LinearDimension("30px")
                                margin = "0"
                                padding = "0"
                                listStyleType = ListStyleType.none
                            }
                            li {
                                style { float = Float.left }
                                a {
                                    href = "/advancedExample"
                                    +"Load advanced example"
                                }
                            }
                            li {
                                style {
                                    float = Float.left
                                    marginLeft = LinearDimension("10px")
                                }
                                a {
                                    href = "/simpleExample"
                                    +"Load simple example"
                                }
                            }

                        }
                        div {
                            id = "container"
                            style {
                                position = Position.relative
                            }
                            div {
                                id = "jsoneditor1"
                                style {
                                    float = Float.left
                                    width = LinearDimension("600px")
                                    height = LinearDimension("600px")
                                }
                            }
                            div {
                                id = "jsoneditor2"
                                style {
                                    float = Float.left
                                    marginLeft = LinearDimension("20px")
                                    width = LinearDimension("600px")
                                    height = LinearDimension("600px")
                                }
                            }
                        }

                        script(type = ScriptType.textJavaScript) {
                            unsafe {
                                raw("""
                                    |
                                    |var json = ${server.json}
                                    |window.editor1 = null;
                                    |window.editor2 = null;
                                    |
                                    |var container1 = document.getElementById("jsoneditor1");
                                    |var options1 = {
                                    |    onChangeText: function (json) {
                                    |       window.editor2.set(JSON.parse(json));
                                    |       window.editor2.expandAll();
                                    |    },
                                    |};
                                    |window.editor1 = new JSONEditor(container1, options1);
                                    |window.editor1.setMode("code");
                                    |window.editor1.set(json);
                                    |
                                    |var container2 = document.getElementById("jsoneditor2");
                                    |var options2 = {
                                    |    onChangeJSON: function (json) {
                                    |       window.editor1.set(json)
                                    |    },
                                    |};
                                    |window.editor2  = new JSONEditor(container2, options2);
                                    |window.editor2.set(json);
                                    |window.editor2.expandAll();
                                    |
                                """.trimMargin())
                            }
                        }
                    }
                }
            }

            post("/update") {
                val post = call.receive<Parameters>()
                val json = post["json"] ?: return@post call.respondRedirect("/")

                server.json = json
                server.broadcastJson()
                call.respondText("{ \"success\": true }", ContentType.Application.Json)
            }

            get("/advancedExample") {
                server.loadAdvancedExample()
                call.respondRedirect("/")
            }

            get("/simpleExample") {
                server.loadSimpleExample()
                call.respondRedirect("/")
            }

            static ("static") {
                // When running under IDEA make sure that working directory is set to this sample's project folder
                staticRootFolder = File("static")
                files("css")
                files("js")
            }

        }
    }
}

/*
@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")

@Location("/type/{name}") data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}

fun FlowOrMetaDataContent.styleCss(builder: CSSBuilder.() -> Unit) {
    style(type = ContentType.Text.CSS.toString()) {
        +CSSBuilder().apply(builder).toString()
    }
}*/

fun CommonAttributeGroupFacade.style(builder: CSSBuilder.() -> Unit) {
    this.style = CSSBuilder().apply(builder).toString().trim()
}

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
