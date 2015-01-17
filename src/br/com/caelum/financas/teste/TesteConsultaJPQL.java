package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaJPQL {
	
	public static void main(String[] args) {
		
		EntityManager manager = new JPAUtil().getEntityManager();
		
		Conta conta = new Conta();
		conta.setId(1);
		
		// Query query = manager.createQuery("select m from Movimentacao m where m.conta.id=?1"
		//		+ " AND m.tipoMovimentacao=?2");
		// query.setParameter(1, conta.getId());
		// query.setParameter(2, TipoMovimentacao.ENTRADA);
		
		Query query = manager.createQuery("SELECT m FROM Movimentacao m WHERE m.conta.id=:pConta"
				+ " AND m.tipoMovimentacao=:pTipo"
				+ " ORDER BY m.valor DESC");
		
		query.setParameter("pConta", conta.getId());
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		
		List<Movimentacao> movimentacoes = query.getResultList();
		
		for(Movimentacao m : movimentacoes) {
			
			System.out.println("\nDescrição:"+m.getDescricao());
			System.out.println("\nValor:"+m.getValor());
		}
		
		manager.close();
	}
}


