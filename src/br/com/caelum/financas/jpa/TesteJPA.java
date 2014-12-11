package br.com.caelum.financas.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPA {

	public static void main(String[] args) {

		double inicio = System.currentTimeMillis();
		
		Conta conta = new Conta();
		conta.setTitular("Priscila gatinha");
		conta.setBanco("Caixa");
		conta.setAgencia("043");
		conta.setNumero("54321");
		conta.setSaldo(new BigDecimal(653.82));

		/**
		 * Usando HSQLDB
		 */

		// EntityManagerFactory emf = Persistence
		// 		.createEntityManagerFactory("contas-hsqldb");

		/**
		 * Usando PostgreSQL
		 */
		// EntityManagerFactory emf = Persistence
		// 		.createEntityManagerFactory("contas-postgres");

		/**
		 * Usando MySQL
		 */
		/*EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("contas-mysql");

		EntityManager manager = emf.createEntityManager();
		*/
		EntityManager manager = new JPAUtil().getEntityManager();
		
		manager.getTransaction().begin();

		manager.persist(conta);

		manager.getTransaction().commit();
		manager.close();
		
		double fim = System.currentTimeMillis();
		
		System.out.println("Executado em: " + (fim - inicio)/1000 + "s");
	}
}
