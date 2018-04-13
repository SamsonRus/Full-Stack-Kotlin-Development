plugins {
    id("org.jetbrains.kotlin.plugin.spring") version "1.1.4-2"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.1.4-2"
}

dependencies {
    val springBootVersion: String = parent.properties["springBootVersion"] as String
    val mySQLConnectorVersion: String = parent.properties["mySQLConnectorVersion"] as String
    compile("org.springframework.boot:spring-boot-starter:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    compile("mysql:mysql-connector-java:$mySQLConnectorVersion")
}