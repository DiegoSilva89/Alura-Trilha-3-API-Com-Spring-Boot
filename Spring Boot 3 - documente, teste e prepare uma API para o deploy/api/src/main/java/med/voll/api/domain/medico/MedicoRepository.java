package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from Medico m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
                and
                c.motivoCancelamento is null
            )
            order by rand()
            limit 1
                       
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);


    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id       
            """)
    Boolean findAtivoById(Long id);
}
/*Repository é uma interface que substitui as classes DAO (Data Access Object) a interface extente JpaRepository
passando os generics, ou dois tipos de objeto, o primeiro é: qual é o tipo de entidade, e o segundo é o tipo de
atributo da chave primária da entidade.

A interfaço herda de JpaRepository, não precisa mais criar as classes ou criar outras definições
 */

