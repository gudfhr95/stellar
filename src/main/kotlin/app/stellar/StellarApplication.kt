package app.stellar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StellarApplication

fun main(args: Array<String>) {
	runApplication<StellarApplication>(*args)
}
