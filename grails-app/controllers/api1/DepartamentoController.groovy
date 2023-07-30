package api1

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class DepartamentoController {
    static allowedMethods = [index: 'GET', show: 'GET', save: 'POST', update: 'PUT', delete: 'DELETE']

    def departamentoService

    def index() {
        def departamentos = departamentoService.listarDepartamentos()
        render departamentos as JSON
    }

    def show(Long id) {
        def departamento = departamentoService.buscarDepartamento(id)
        if (departamento) {
            render departamento as JSON
        } else {
            render status: 404, contentType: 'application/json', text: '{"error": "Departamento não encontrado"}'
        }
    }

    def save() {
        def departamento = new Departamento(params)
        if (departamento.validate()) {
            departamentoService.salvarDepartamento(departamento)
            render departamento as JSON
        } else {
            respondWithError(departamento.errors)
        }
    }

    def update(Long id) {
        def departamento = departamentoService.buscarDepartamento(id)
        if (departamento) {
            departamento.properties = params
            if (departamento.validate()) {
                departamentoService.atualizarDepartamento(departamento)
                render departamento as JSON
            } else {
                respondWithError(departamento.errors)
            }
        } else {
            render status: 404, contentType: 'application/json', text: '{"error": "Departamento não encontrado"}'
        }
    }

    def delete(Long id) {
        def departamento = departamentoService.buscarDepartamento(id)
        if (departamento) {
            departamentoService.apagarDepartamento(departamento)
            render status: 204
        } else {
            render status: 404, contentType: 'application/json', text: '{"error": "Departamento não encontrado"}'
        }
    }

    private respondWithError(errors) {
        render status: 400, contentType: 'application/json', text: errors as JSON
    }
}