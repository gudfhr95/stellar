package app.stellar.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StellarApplication

fun main(args: Array<String>) {
    runApplication<StellarApplication>(args = args)
}
