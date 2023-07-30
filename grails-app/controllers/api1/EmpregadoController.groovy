package api1

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class EmpregadoController {
    static allowedMethods = [index: 'GET', show: 'GET', save: 'POST', update: 'PUT', delete: 'DELETE']

    def empregadoService

    def index() {
        def empregados = empregadoService.listarEmpregados()
        render empregados as JSON
    }

    def show(Long id) {
        def empregado = empregadoService.buscarEmpregado(id)
        if (empregado) {
            render empregado as JSON
        } else {
            render status: 404, contentType: 'application/json', text: '{"error": "Empregado não encontrado"}'
        }
    }

    def save() {
        def empregado = new Empregado(params)
        if (empregado.validate()) {
            empregadoService.salvarEmpregado(empregado)
            render empregado as JSON
        } else {
            respondWithError(empregado.errors)
        }
    }

    def update(Long id) {
        def empregado = empregadoService.buscarEmpregado(id)
        if (empregado) {
            empregado.properties = params
            if (empregado.validate()) {
                empregadoService.atualizarEmpregado(empregado)
                render empregado as JSON
            } else {
                respondWithError(empregado.errors)
            }
        } else {
            render status: 404, contentType: 'application/json', text: '{"error": "Empregado não encontrado"}'
        }
    }

    def delete(Long id) {
        def empregado = empregadoService.buscarEmpregado(id)
        if (empregado) {
            empregadoService.apagarEmpregado(empregado)
            render status: 204
        } else {
            render status: 404, contentType: 'application/json', text: '{"error": "Empregado não encontrado"}'
        }
    }

    private respondWithError(errors) {
        render status: 400, contentType: 'application/json', text: errors as JSON
    }
}