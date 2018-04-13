package com.packtpub.views

import kotlinx.html.*
import kotlinx.html.stream.createHTML


fun index(header: String): String {
    val links = mapOf("Kotlin" to "https://github.com/JetBrains/kotlin",
            "Spring" to "https://github.com/spring-projects/spring-framework",
            "React" to "https://github.com/facebook/react",
            "Full Stack Development" to "https://github.com/Xantier/fullstack-kotlin")
    return createHTML(true).html {
        head {
            title = "Full Stack Kotlin"
            styleLink("/static/css/hello.css")
        }
        body {
            h4 { +header }
            p { +"Welcome to full stack Kotlin" }
            p {
                +"Our Resouces: "
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
    }
}


