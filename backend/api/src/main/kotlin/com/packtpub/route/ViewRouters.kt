package com.packtpub.route

import com.packtpub.HelloSayer
import com.packtpub.util.htmlView
import com.packtpub.util.json
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

class ViewRouters(private val helloSayer: HelloSayer) {

    val links = mapOf(
            "Kotlin" to "https://github.com/JetBrains/kotlin",
            "Full Stack Development" to "https://github.com/SamsonRus/Full-Stack-Kotlin-Development/"
                     )

    @Bean
    fun viewRouter(): RouterFunction<ServerResponse> =
            router {
                accept(MediaType.TEXT_HTML).nest {
                    GET("/hello") { req ->
                        val name = req.queryParam("name").orElse("User")
                        ServerResponse.ok()
                                .htmlView(Mono.just(createHTML(true).html {
                            head {
                                title = "Full Stack Kotlin"
                                styleLink("/static/css/hello.css")
                            }
                            body {
                                h4 {
                                    +helloSayer.sayHello(name)
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
                                script(src = "/static/js/hello.js")
                            }
                        }))
                    }
                }
                resources("/**", ClassPathResource("/static"))
            }
}