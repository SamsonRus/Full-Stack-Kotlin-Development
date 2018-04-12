package com.packtpub.route

import com.packtpub.HelloSayer
import com.packtpub.util.json
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

class ApiRoutes(private val helloSayer: HelloSayer) {

    @Bean
    fun apiRouter() =
            router {
                (accept(MediaType.APPLICATION_JSON_UTF8) and "/api").nest {
                    GET("/hello") {req ->
                        ServerResponse.ok()
                                .json(Mono.just(
                                        helloSayer.sayHello(
                                                req.queryParam("name").orElse("User")
                                                           )
                                               ))

                    }
                }
            }
}