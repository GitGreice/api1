import api1.Departamento
import api1.Empregado

import java.time.LocalDate
import java.util.Random

class Bootstrap {

    def init = { servletContext ->
        println "Método init chamado."
        createDepartamentos()
        createEmpregados()
    }

    private void createDepartamentos() {
        Departamento.withTransaction { status ->
            def departamentos = ['RH', 'Vendas']

            departamentos.each { nome ->
                new Departamento(nome: nome).save(flush: true, failOnError: true)
            }
        }
    }

    private void createEmpregados() {
        Empregado.withTransaction { status ->
            def departamentos = Departamento.list()

            departamentos.each { departamento ->
                def nomes = ['João', 'Maria', 'Carlos', 'Ana', 'Pedro']

                nomes.each { nome ->
                    def empregado = new Empregado(
                            nome: nome,
                            dataNascimento: LocalDate.of(1990, 1, 1),
                            matricula: Math.abs(new Random().nextInt() % 10000),
                            departamento: departamento
                    )
                    empregado.save(flush: true, failOnError: true)
                    println "Empregado ${nome} salvo com ID ${empregado.id}"
                }
            }
        }
    }
}