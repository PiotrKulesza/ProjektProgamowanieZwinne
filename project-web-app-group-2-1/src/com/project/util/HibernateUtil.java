package com.project.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	private static final String DATA_SOURCE = "psqlManager";
	private EntityManagerFactory entityManagerFactory;

	private HibernateUtil() {
	}

	private static class Inner {
		private static final HibernateUtil INSTANCE = new HibernateUtil();
	}

	public static HibernateUtil getInstance() {
		return Inner.INSTANCE;
	}

	public EntityManagerFactory createEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(DATA_SOURCE);
		}
		return entityManagerFactory;
	}

	public EntityManager createEntityManager() {
		return this.createEntityManagerFactory().createEntityManager();
	}

	public void closeEntityManagerFactory() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
}
