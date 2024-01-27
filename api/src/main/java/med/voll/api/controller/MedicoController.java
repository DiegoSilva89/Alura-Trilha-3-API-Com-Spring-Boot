package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController //para o spring carregar a classe na inicialização do projeto
@RequestMapping("medicos") //para mapear a url pelo spring
public class MedicoController {

    @Autowired //injeção de dependencia
    private MedicoRepository repository;

    @PostMapping //se chamar uma requisição do tipo post na url /medicos é para usar o método cadastrar
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping //método para ler
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    } //convert medido to DadosListagemMedico

    @PutMapping //método para atualizar dados
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id()); //repository acessa o bd / busca médico pelo id
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}") //parametro dinamico para deletar pelo id do médico
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id); //repository acessa o bd / busca médico pelo id
        medico.excluir();
    }


}
