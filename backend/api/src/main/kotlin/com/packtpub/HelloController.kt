package com.packtpub

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val helloSayer: HelloSayer) {

    val links = mapOf(
            "Kotlin" to "https://github.com/JetBrains/kotlin",
            "Full Stack Development" to "https://github.com/SamsonRus/Full-Stack-Kotlin-Development/"
                     )

    @GetMapping(produces = arrayOf("text/html"))
    fun sayHello(@RequestParam(value = "name", required = false) name: String?)
            : String =
            createHTML(true).html {
                head {
                    title = "Full Stack Kotlin"
                }
                body {
                    h4 {
                        +helloSayer.sayHello(name ?: "User")
                    }
                    p {
                        +"Welcome to full stack Kotlin"
                    }
                    p {
                        +"Our Resources: "
                        ul {
                            links.map { (name, url) ->
                                li {
                                    a(url) {
                                        target = ATarget.blank
                                        +name
                                    }
                                }
                            }
                        }
                    }
                }
            }
}