package org.example.esig2.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import org.example.esig2.Domain.PessoaSalarioConsolidado;
import org.example.esig2.Utils.JpaUtil;

import java.util.List;

public class PessoaSalarioConsolidadoDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void salvar(PessoaSalarioConsolidado pessoaSalarioConsolidado) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            if (pessoaSalarioConsolidado.getUsuario() != null) {
                pessoaSalarioConsolidado.setPessoaId(pessoaSalarioConsolidado.getUsuario().getId());
            }

            em.persist(pessoaSalarioConsolidado);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Buscar PessoaSalarioConsolidado por pessoaId
    public PessoaSalarioConsolidado buscarPorId(Integer pessoaId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(PessoaSalarioConsolidado.class, pessoaId);  // Buscar o registro com o pessoaId
        } finally {
            em.close(); // Certifique-se de fechar o EntityManager
        }
    }

    // Listar todos os registros
    public List<PessoaSalarioConsolidado> listarTodos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM PessoaSalarioConsolidado p", PessoaSalarioConsolidado.class).getResultList();  // Retorna todos os registros
        } finally {
            em.close(); // Certifique-se de fechar o EntityManager
        }
    }

    // Atualizar PessoaSalarioConsolidado
    public void atualizar(PessoaSalarioConsolidado pessoaSalarioConsolidado) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // Garantir que o objeto Usuario e Cargo estão atualizados corretamente
            if (pessoaSalarioConsolidado.getUsuario() != null) {
                pessoaSalarioConsolidado.setPessoaId(pessoaSalarioConsolidado.getUsuario().getId());  // Associar o ID de Usuario
            }

            em.merge(pessoaSalarioConsolidado);  // Atualizar o objeto
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback em caso de erro
            }
            e.printStackTrace(); // Log do erro
        } finally {
            em.close(); // Certifique-se de fechar o EntityManager
        }
    }

    // Deletar PessoaSalarioConsolidado
    public void deletar(Integer pessoaId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            PessoaSalarioConsolidado pessoaSalarioConsolidado = em.find(PessoaSalarioConsolidado.class, pessoaId);
            if (pessoaSalarioConsolidado != null) {
                em.remove(pessoaSalarioConsolidado);  // Remover o registro
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback em caso de erro
            }
            e.printStackTrace(); // Log do erro
        } finally {
            em.close(); // Certifique-se de fechar o EntityManager
        }
    }

    public PessoaSalarioConsolidado buscarPorPessoaId(Integer pessoaId) {
        EntityManager em = JpaUtil.getEntityManager(); // Obtenha o EntityManager
        try {
            return em.createQuery("SELECT p FROM PessoaSalarioConsolidado p WHERE p.pessoaId = :pessoaId", PessoaSalarioConsolidado.class).setParameter("pessoaId", pessoaId)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;

        }

    }
}