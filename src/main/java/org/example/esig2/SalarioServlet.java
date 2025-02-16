package org.example.esig2;

import org.example.esig2.DAO.PessoaSalarioConsolidadoDAO;
import org.example.esig2.DAO.VencimentoDAO;
import org.example.esig2.DTO.UsuarioDTO;
import org.example.esig2.Domain.PessoaSalarioConsolidado;
import org.example.esig2.Domain.Usuario; // Supondo que você tenha uma classe Usuario
import org.example.esig2.Service.CalculoSalarioService; // Classe de serviço para calcular salários
import org.example.esig2.DAO.UsuarioDAO; // DAO para acessar os usuários

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SalarioServlet", value = "/salario")
public class SalarioServlet extends HttpServlet {
    private CalculoSalarioService salarioService;
    private UsuarioDAO usuarioDAO;
    private VencimentoDAO vencimentoDAO;
    private PessoaSalarioConsolidadoDAO pessoaSalarioConsolidadoDAO;

    public void init() throws ServletException {
        try {
            System.out.println("Inicializando VencimentoDAO...");
            vencimentoDAO = new VencimentoDAO(); // Inicializa o DAO de vencimentos
            System.out.println("VencimentoDAO inicializado com sucesso.");
            System.out.println("Inicializando UsuarioDAO...");
            usuarioDAO = new UsuarioDAO(); // Inicializa o DAO de usuários
            System.out.println("UsuarioDAO inicializado com sucesso.");
            System.out.println("Inicializando PessoaSalarioConsolidadoDAO...");
            pessoaSalarioConsolidadoDAO = new PessoaSalarioConsolidadoDAO(); // Inicializa o DAO de usuários
            System.out.println("PessoaSalarioConsolidadoDAO inicializado com sucesso.");
            System.out.println("Inicializando CalculoSalarioService...");
            salarioService = new CalculoSalarioService(vencimentoDAO, pessoaSalarioConsolidadoDAO); // Inicializa o serviço de salário
            System.out.println("CalculoSalarioService inicializado com sucesso.");

        } catch (Exception e) {
            e.printStackTrace(); // Imprime a stack trace para ajudar na depuração
            throw new ServletException("Erro ao inicializar o SalarioServlet", e);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); // Obter o PrintWriter para escrever na resposta

        List<PessoaSalarioConsolidado> salarios = pessoaSalarioConsolidadoDAO.listarTodos();

//        request.setAttribute("salarios", salarios);
//
//        request.getRequestDispatcher("/pages/usuarios.xhtml").forward(request, response);

        out.println("<html><head><title>Lista de Salários</title></head><body>");
        out.println("<h1>Lista de Salários</h1>");

        // Botão para recalcular salários
        out.println("<form method='post' action='salario'>");
        out.println("<input type='submit' value='Calcular/Recalcular Salários' />");
        out.println("</form>");

        if (salarios == null || salarios.isEmpty()) {
            out.println("<p>Nenhum salário encontrado.</p>");
        } else {
            out.println("<h2>Salários encontrados: " + salarios.size() + "</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Nome</th><th>Salário</th></tr>");

            for (PessoaSalarioConsolidado consolidado : salarios) {
                out.println("<tr>");
                out.println("<td>" + consolidado.getPessoaId() + "</td>");
                out.println("<td>" + consolidado.getNomePessoa() + "</td>");
                out.println("<td>" + consolidado.getSalario() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("</body></html>");
        out.close(); // Fechar o PrintWriter
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioDAO.listarTodos();

        for (Usuario usuario : usuarios) {
            salarioService.salvarSalario(usuario);
        }

        doGet(request, response);
    }

//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//
//        List<Usuario> usuarios = usuarioDAO.listarTodos();
//
//        // Calcula o salário para cada usuário e adiciona à lista
//        for (Usuario usuario : usuarios) {
//            BigDecimal salario = salarioService.calcularSalario(usuario);
//            salarioService.salvarSalario(usuario);
//            String cargoNome = (usuario.getCargo() != null) ? usuario.getCargo().getNome() : "N/A";
//        }
//
//        List<PessoaSalarioConsolidado> salarios = pessoaSalarioConsolidadoDAO.listarTodos();
//
//        request.setAttribute("usuarios", salarios);
//        request.getRequestDispatcher("/pages/usuarios.xhtml").forward(request, response);
//    }

    public void destroy() {
        // Limpeza, se necessário
    }
}