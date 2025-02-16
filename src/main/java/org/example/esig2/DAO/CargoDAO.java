package org.example.esig2.DAO;

import jakarta.persistence.*;
import org.example.esig2.Domain.Cargo;
import org.example.esig2.Utils.JpaUtil;

import java.util.List;

public class CargoDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void salvar(Cargo cargo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cargo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Cargo buscarPorId(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Cargo.class, id);
        } finally {
            em.close(); // Certifique-se de fechar o EntityManager
        }
    }

    public List<Cargo> listarTodos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Cargo> query = em.createQuery("SELECT c FROM Cargo c", Cargo.class);
            return query.getResultList();
        } finally {
            em.close(); // Certifique-se de fechar o EntityManager
        }
    }

    public void atualizar(Cargo cargo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cargo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void deletar(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Cargo cargo = em.find(Cargo.class, id);
            if (cargo != null) {
                em.getTransaction().begin();
                em.remove(cargo);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Cargo buscarPorNome(String nomeCargo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // Realizando a consulta para buscar o cargo pelo nome
            return em.createQuery("SELECT c FROM Cargo c WHERE c.nome = :nomeCargo", Cargo.class)
                    .setParameter("nomeCargo", nomeCargo)
                    .getSingleResult();  // Retorna o único resultado ou lança uma exceção caso não encontre
        } catch (NoResultException e) {
            // Retornar null caso não encontre nenhum cargo com o nome especificado
            return null;
        } finally {
            em.close();
        }
    }
}
