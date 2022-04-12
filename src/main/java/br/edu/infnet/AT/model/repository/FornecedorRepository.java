package br.edu.infnet.AT.model.repository;


import br.edu.infnet.AT.model.domain.Fornecedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor, Integer>{
    
}


