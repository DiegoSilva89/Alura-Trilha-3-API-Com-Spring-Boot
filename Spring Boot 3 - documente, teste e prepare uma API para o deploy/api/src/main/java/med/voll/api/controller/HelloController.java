package med.voll.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //anotação necessária para que as requisições HTTP sejam devidamente mapeadas e tratadas
@RequestMapping("/hello")
public class HelloController {

    /*Para verificar, acessar localhost:8080/hello no navegador.
    * É preciso configurar o projeto - Ctrl+Alt+S para acessar opções do IntelliJ, no Menu Advanced, marcar a primeira
    * opção de Compiler "Allow auto make..."
    * Depois em Build, Execution, Deployment, Compilet e marcar "Build project automatically*/
    @GetMapping
    public String olaMundo() {
        return "Hello World Spring!";
    }
}
