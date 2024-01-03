package med.voll.api.controller;

import med.voll.api.medico.DadosCadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //para o spring carregar a classe na inicialização do projeto
@RequestMapping("medicos") //para mapear a url pelo spring
public class MedicoController {

    @PostMapping //se chamar uma requisição do tipo post na url /medicos é para usar o método cadastrar
    public void cadastrar(@RequestBody DadosCadastroMedico dados) {
        System.out.println(dados);

    }


}
