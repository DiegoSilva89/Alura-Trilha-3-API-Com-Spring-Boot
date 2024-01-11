package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //para o spring carregar a classe na inicialização do projeto
@RequestMapping("medicos") //para mapear a url pelo spring
public class MedicoController {

    @Autowired //injeção de dependencia
    private MedicoRepository repository;

    @PostMapping //se chamar uma requisição do tipo post na url /medicos é para usar o método cadastrar
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {

    }

    @GetMapping //método para ler
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"})
                                                Pageable paginacao) { //Page e Pageable para paginar os dados
        return repository.findAll(paginacao).map(DadosListagemMedico::new); //convert medido to DadosListagemMedico
    }

    @PutMapping //método para atualizar dados
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id()); //repository acessa o bd / busca médico pelo id
        medico.atualizarInformacoes(dados);


    }

}
