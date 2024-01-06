package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
