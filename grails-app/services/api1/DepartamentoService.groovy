package api1

import grails.gorm.transactions.Transactional
import api1.Departamento

@Transactional
class DepartamentoService {

    def listarDepartamentos() {
        Departamento.list()
    }

    def buscarDepartamento(Long id) {
        Departamento.get(id)
    }

    def salvarDepartamento(Departamento departamento) {
        departamento.save(failOnError: true)
    }

    def atualizarDepartamento(Departamento departamento) {
        departamento.merge(failOnError: true)
    }

    def apagarDepartamento(Departamento departamento) {
        departamento.delete(failOnError: true, flush: true)
    }
}