package api1

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonFormat
import grails.gorm.annotation.Entity
import javax.validation.constraints.NotBlank
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
class Empregado {
    Long id
    @NotBlank
    String nome

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataNascimento

    Integer matricula

    static belongsTo = [departamento: Departamento]

    static constraints = {
        nome blank: false
        dataNascimento blank: false
        matricula nullable: true // Pode ser nulo, mas deve ser único se preenchido
    }

    // Método para retornar a data formatada manualmente
    @JsonIgnore
    String getFormattedDataNascimento() {
        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}