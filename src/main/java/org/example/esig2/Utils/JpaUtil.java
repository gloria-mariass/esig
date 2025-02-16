package org.example.esig2.Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    // Instância do EntityManagerFactory
    private static final EntityManagerFactory emf;

    static {
        // Cria uma fábrica de EntityManager a partir da configuração de persistence.xml
        emf = Persistence.createEntityManagerFactory("default");
    }

    // Método para obter um EntityManager
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para fechar o EntityManagerFactory, liberando os recursos
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
