package api1

import grails.gorm.annotation.Entity
import javax.validation.constraints.NotBlank

@Entity
class Departamento {
    Long id

    @NotBlank
    String nome

    static hasMany = [empregados: Empregado]

    static constraints = {
        nome blank: false
    }
}