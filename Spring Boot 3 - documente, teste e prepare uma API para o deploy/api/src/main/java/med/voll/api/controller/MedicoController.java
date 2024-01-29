package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController //para o spring carregar a classe na inicialização do projeto
@RequestMapping("medicos") //para mapear a url pelo spring
public class MedicoController {

    @Autowired //injeção de dependencia
    private MedicoRepository repository;

    @PostMapping //se chamar uma requisição do tipo post na url /medicos é para usar o método cadastrar
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping //método para ler
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    } //convert medido to DadosListagemMedico

    @PutMapping //método para atualizar dados
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id()); //repository acessa o bd / busca médico pelo id
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}") //parametro dinamico para deletar pelo id do médico
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id); //repository acessa o bd / busca médico pelo id
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") //parametro dinamico para encontrar e ler os dados pelo id do médico
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id); //repository acessa o bd / busca médico pelo id

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    /*Por padrão, exceções não tratadas no código são interpretados pelo Spring Boot como erro 500, mesmo para
    * os casos onde a URL tenha aluma parametro inexistente, sendo assim é necessário tratar para que ao invés do erro
    * 500, seja mostrado o erro 404*/
    /*Para tratar erro 500 no insominia, escrever no application.properties -> server.error.include-stacktrace=never*/

}
