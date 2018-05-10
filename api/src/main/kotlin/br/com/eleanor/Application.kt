package br.com.eleanor

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer

@SpringBootApplication
class Application : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        builder.sources(Application::class.java)
        return builder
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}