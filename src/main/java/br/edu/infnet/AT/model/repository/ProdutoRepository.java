package br.edu.infnet.AT.model.repository;

import br.edu.infnet.AT.model.domain.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer>{
    
}
