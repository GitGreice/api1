package api1

import grails.gorm.transactions.Transactional

@Transactional
class EmpregadoService {
    def listarEmpregados() {
        Empregado.list()
    }

    def buscarEmpregado(Long id) {
        Empregado.get(id)
    }

    def salvarEmpregado(Empregado empregado) {
        empregado.validate()
        if (empregado.hasErrors()) {
            return empregado.errors
        }
        empregado.save(flush: true, failOnError: true)
    }

    def atualizarEmpregado(Empregado empregado) {
        empregado.validate()
        if (empregado.hasErrors()) {
            return empregado.errors
        }
        empregado.save(flush: true, failOnError: true)
    }

    def apagarEmpregado(Empregado empregado) {
        empregado.delete(flush: true, failOnError: true)
    }
}