package app.stellar.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    private val helloWorld = "Hello, World!"

    @GetMapping("/hello")
    fun hello() = helloWorld
}
