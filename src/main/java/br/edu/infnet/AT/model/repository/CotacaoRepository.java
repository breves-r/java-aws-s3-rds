package br.edu.infnet.AT.model.repository;

import br.edu.infnet.AT.model.domain.Cotacao;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotacaoRepository extends CrudRepository<Cotacao, Integer>{
    
    @Query("from Cotacao c where c.produto.id = :idProduto")
    Collection<Cotacao> findByProduto(Integer idProduto);
    
}
