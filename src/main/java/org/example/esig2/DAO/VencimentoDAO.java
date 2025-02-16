package org.example.esig2.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.esig2.Domain.Vencimento;
import org.example.esig2.Utils.JpaUtil;

import java.util.ArrayList;
import java.util.List;

public class VencimentoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void salvar(Vencimento vencimento) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(vencimento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Vencimento buscarPorId(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Vencimento.class, id);
        } finally {
            em.close();
        }
    }

    public List<Vencimento> listarTodos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Vencimento v", Vencimento.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Vencimento vencimento) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(vencimento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deletar(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Vencimento vencimento = em.find(Vencimento.class, id);
            if (vencimento != null) {
                em.remove(vencimento);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Vencimento> buscarVencimentosPorPessoaId(Integer pessoaId) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Vencimento> vencimentos = null;

        try {
            TypedQuery<Vencimento> query = em.createQuery(
                    "SELECT v FROM Usuario u " +
                            "JOIN u.cargo c " +
                            "JOIN c.cargoVencimentos cv " +
                            "JOIN cv.vencimento v " +
                            "WHERE u.id = :pessoaId", Vencimento.class);
            query.setParameter("pessoaId", pessoaId);

            vencimentos = query.getResultList();
        } finally {
            em.close();
        }

        return (vencimentos != null) ? vencimentos : new ArrayList<>();
    }
}
