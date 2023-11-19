// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

class Usuario (val nome: String)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>, val nivel: Nivel) {

    val inscritos: MutableList<Usuario> = mutableListOf()
    
    infix fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }

}

fun Formacao.getInfo(): String {

    val conteudosFormatados = conteudos.joinToString(separator = "\n\t"){
        "Nome: ${it.nome}, Duração: ${it.duracao}, Dificuldade: ${it.nivel}"
    }

    val inscritosFormatado = inscritos.joinToString(separator = "\n\t") {it.nome}

    return "Nome da formação: \n\t${this.nome}\n" +
            "Conteudos:\n" +
            "\t$conteudosFormatados\n" +
            "Nível: \n" +
            "\t$nivel\n" +
            "Inscritos: \n" +
            "\t${inscritosFormatado}"
}

fun main() {

    /*
    * CONTEUDOS FORMAÇÕES
    * */
    // Kotlin Backend
    val conteudosKotlinBackendSpring: List<ConteudoEducacional> = listOf(
        ConteudoEducacional("Introdução ao Kotlin", 120, Nivel.BASICO),
        ConteudoEducacional("Funções e Orientação à Objetos em Kotlin", 240, Nivel.INTERMEDIARIO),
        ConteudoEducacional("Construção de Espaçonaves e Reatores Termonucleares com Kotlin", 999999, Nivel.AVANCADO)
    )
    // SvelteKit
    val conteudosSvelteKitFrontend: List<ConteudoEducacional> = listOf(
        ConteudoEducacional("Aprendendo o básico de Javascript", nivel = Nivel.BASICO),
        ConteudoEducacional("Construindo um jogo multiplayer com Javascript", nivel = Nivel.INTERMEDIARIO),
        ConteudoEducacional("Hackeando todos os computadores do universo com HTML", nivel = Nivel.AVANCADO)
    )

    /*
    *   FORMAÇÕES
    * */
    val kotlinBackend = Formacao("Backend com Kotlin e Spring", conteudosKotlinBackendSpring, Nivel.INTERMEDIARIO)
    val sveltekitFrontend = Formacao("Desenvolvimento de games e segurança com SvelteKit (piada)", conteudosSvelteKitFrontend, Nivel.BASICO)

    /*
    *   PESSOAS
    * */
    val maria = Usuario("Maria").also {
        kotlinBackend matricular it
    }

    val jose = Usuario("José")

    val pedro = Usuario("Pedro")

    /*
    *   EXECUÇÕES DIVERSAS
    * */
    sveltekitFrontend.matricular(jose)
    sveltekitFrontend.matricular(maria)
    sveltekitFrontend.matricular(pedro)

    kotlinBackend.matricular(pedro)

    println("--------------------------------------------------------------------")

    // Ver os matriculados nos cursos
    println("Matriculados no curso de SvelteKit: ")
    sveltekitFrontend.inscritos.forEach{
        println("\t${it.nome}")
    }

    println("\n")

    println("Matriculados no curso de Kotlin Backend: ")
    kotlinBackend.let {
        it.run {
            for (u in inscritos) {
                println("\t${u.nome}")
            }
        }
    }

    println("--------------------------------------------------------------------")
    // Informações do Curso
    println("Sobre o Curso de Kotlin Backend")
    println(kotlinBackend.getInfo())

    println("\n\nSobre o Curso de SvelteKit")
    println(sveltekitFrontend.getInfo())
    println("--------------------------------------------------------------------")

}
