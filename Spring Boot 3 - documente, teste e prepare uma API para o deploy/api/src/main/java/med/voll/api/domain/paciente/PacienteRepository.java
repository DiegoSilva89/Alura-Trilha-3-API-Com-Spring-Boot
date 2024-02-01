package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);


    @Query("""
            select m.ativo
            from Paciente m
            where
            m.id = :id            
            """)
    Boolean findAtivoById(Long id);
/*Repository é uma interface que substitui as classes DAO (Data Access Object) a interface extente JpaRepository
passando os generics, ou dois tipos de objeto, o primeiro é: qual é o tipo de entidade, e o segundo é o tipo de
atributo da chave primária da entidade.

A interfaço herda de JpaRepository, não precisa mais criar as classes ou criar outras definições
 */
}