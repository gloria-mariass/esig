package org.example.esig2.Service;

import org.example.esig2.DAO.PessoaSalarioConsolidadoDAO; // Certifique-se de ter este DAO
import org.example.esig2.DAO.VencimentoDAO;
import org.example.esig2.Domain.PessoaSalarioConsolidado;
import org.example.esig2.Domain.Usuario;
import org.example.esig2.Domain.Vencimento;

import java.math.BigDecimal;
import java.util.List;

public class CalculoSalarioService {
    private VencimentoDAO vencimentoDAO;
    private PessoaSalarioConsolidadoDAO pessoaSalarioConsolidadoDAO; // Adicione o DAO

    public CalculoSalarioService(VencimentoDAO vencimentoDAO, PessoaSalarioConsolidadoDAO pessoaSalarioConsolidadoDAO) {
        this.vencimentoDAO = vencimentoDAO;
        this.pessoaSalarioConsolidadoDAO = pessoaSalarioConsolidadoDAO; // Inicialize o DAO
    }

    public BigDecimal calcularSalario(Usuario pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("O objeto Usuario não pode ser nulo");
        }

        BigDecimal salario = BigDecimal.ZERO;
        List<Vencimento> vencimentos = vencimentoDAO.buscarVencimentosPorPessoaId(pessoa.getId());

        for (Vencimento vencimento : vencimentos) {
            if ("CREDITO".equalsIgnoreCase(vencimento.getTipo())) {
                salario = salario.add(vencimento.getValor());
            } else if ("DEBITO".equalsIgnoreCase(vencimento.getTipo())) {
                salario = salario.subtract(vencimento.getValor());
            }
        }

        return salario;
    }

    public void salvarSalario(Usuario usuario) {
        BigDecimal salario = calcularSalario(usuario); // Calcula o salário

        PessoaSalarioConsolidado pessoaSalarioConsolidadoExistente = pessoaSalarioConsolidadoDAO.buscarPorPessoaId(usuario.getId());

        if (pessoaSalarioConsolidadoExistente != null) {
            pessoaSalarioConsolidadoExistente.setSalario(salario);
            pessoaSalarioConsolidadoExistente.setNomePessoa(usuario.getNome());
            pessoaSalarioConsolidadoExistente.setCargo(usuario.getCargo());
            pessoaSalarioConsolidadoDAO.atualizar(pessoaSalarioConsolidadoExistente);

        } else {
            PessoaSalarioConsolidado pessoaSalarioConsolidado = new PessoaSalarioConsolidado();
            pessoaSalarioConsolidado.setPessoaId(usuario.getId());
            pessoaSalarioConsolidado.setNomePessoa(usuario.getNome());
            pessoaSalarioConsolidado.setCargo(usuario.getCargo()); // Supondo que o usuário tem um cargo associado
            pessoaSalarioConsolidado.setSalario(salario);
            pessoaSalarioConsolidadoDAO.salvar(pessoaSalarioConsolidado);
        }
    }
}