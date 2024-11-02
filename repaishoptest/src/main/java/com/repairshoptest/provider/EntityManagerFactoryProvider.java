package com.repairshoptest.provider;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;

	public static EntityManager getEntityManager() {
		if (emf == null || !emf.isOpen()) {
			emf = Persistence.createEntityManagerFactory("repairPU");
			em = emf.createEntityManager();
		}
		return em;
	}
}
