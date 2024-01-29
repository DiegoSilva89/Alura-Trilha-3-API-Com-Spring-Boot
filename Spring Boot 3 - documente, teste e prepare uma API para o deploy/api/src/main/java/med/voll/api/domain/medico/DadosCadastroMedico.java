package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

//Especialidade é um enum
public record DadosCadastroMedico(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "CRM é obrigatório")
        @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM é inválido")
        String crm,

        @NotNull(message = "Especialidade é obrigatória")
        Especialidade especialidade,

        @NotNull(message = "Dados do endereço são obrigatórios")
        @Valid DadosEndereco endereco) {}

/*Desse modo, conseguimos receber os dados na API. Mas ao invés de recebermos como string, usamos o record. Esse
tipo de classe Java ou record chamamos de padrão DTO - Data Transfer Object ("Objeto de transferência de dados").

É um padrão usado em APIs para representar os dados que chegam na API e também os dados que devolvemos dela.

Usaremos bastante esse padrão DTO nos pontos de entrada e saída. Isto é, sempre que precisarmos receber ou devolver
dados da API, criaremos um DTO - sendo uma classe ou record que contém apenas os campos que desejamos receber ou
devolver da API.

Assim, recebemos os dados na nossa API e agora precisamos pegá-los para executarmos as validações, isso para
verificar se os campos estão chegando corretamente, e depois salvar essas informações em um banco de dados.

Mas isso é assunto da próxima aula. Te espero lá!*/