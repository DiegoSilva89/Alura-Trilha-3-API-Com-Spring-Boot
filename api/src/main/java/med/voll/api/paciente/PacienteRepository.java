package med.voll.api.paciente;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
/*Repository é uma interface que substitui as classes DAO (Data Access Object) a interface extente JpaRepository
passando os generics, ou dois tipos de objeto, o primeiro é: qual é o tipo de entidade, e o segundo é o tipo de
atributo da chave primária da entidade.

A interfaço herda de JpaRepository, não precisa mais criar as classes ou criar outras definições
 */
}