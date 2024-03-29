package com.apps.staff_software_spring.util;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class TableUtil {
    private static final StandardServiceRegistry registry;
    private static final MetadataSources metadataSources;

    static {
        registry = new StandardServiceRegistryBuilder().configure().build();
        metadataSources = new MetadataSources(registry);
    }

    private TableUtil() {
        // Constructor privado para ocultar el implícito público
    }

    @SuppressWarnings("deprecation")
    public static boolean isTableExists(Class<?> entityClass) {
        try (Session session = metadataSources.buildMetadata().buildSessionFactory().openSession()) {
            String tableName = session.getSessionFactory().getMetamodel().entity(entityClass).getName();
            String sql = "SHOW TABLES LIKE '" + tableName + "'";
            return !session.createNativeQuery(sql).getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
