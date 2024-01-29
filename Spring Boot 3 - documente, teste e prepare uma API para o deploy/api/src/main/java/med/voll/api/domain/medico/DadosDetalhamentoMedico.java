package med.voll.api.domain.medico;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import med.voll.api.domain.endereco.Endereco;

@JsonPropertyOrder({"Id", "nome", "email", "telefone", "crm", "especialidade", "endereco", "ativo"})
public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone,
                                      Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedico (Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
                medico.getEspecialidade(), medico.getEndereco());

    }
}