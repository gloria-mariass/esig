package org.example.esig2.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.esig2.Domain.CargoVencimentos;
import org.example.esig2.Utils.JpaUtil;

import java.util.List;

public class CargoVencimentosDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void salvar(CargoVencimentos cargoVencimentos) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cargoVencimentos);
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

    public CargoVencimentos buscarPorId(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(CargoVencimentos.class, id);
        } finally {
            em.close(); // Certifique-se de fechar o EntityManager
        }
    }

    public List<CargoVencimentos> listarTodos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT cv FROM CargoVencimentos cv", CargoVencimentos.class).getResultList();
        } finally {
            em.close(); // Certifique-se de fechar o EntityManager
        }
    }

    public void atualizar(CargoVencimentos cargoVencimentos) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cargoVencimentos);
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

    public void deletar(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            CargoVencimentos cargoVencimentos = em.find(CargoVencimentos.class, id);
            if (cargoVencimentos != null) {
                em.remove(cargoVencimentos);
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
}