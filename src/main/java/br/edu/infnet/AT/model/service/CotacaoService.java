package br.edu.infnet.AT.model.service;

import br.edu.infnet.AT.model.domain.Cotacao;
import br.edu.infnet.AT.model.domain.Fornecedor;
import br.edu.infnet.AT.model.domain.Produto;
import br.edu.infnet.AT.model.repository.CotacaoRepository;
import br.edu.infnet.AT.model.repository.FornecedorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CotacaoService {
    
    @Autowired
    private CotacaoRepository cotacaoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired 
    private FornecedorRepository fornecedorRepository;
    
    
    public List<Cotacao> obterLista(){
        return (List<Cotacao>) cotacaoRepository.findAll();
    }
    
    public List<Cotacao> obterPorProduto(Integer id){
        return (List<Cotacao>) cotacaoRepository.findByProduto(id);
    }
    
    public Cotacao consultarCotacao(int id){
        return cotacaoRepository.findById(id).get();
    }
    
    public String incluirCotacao(Cotacao cotacao, int idProduto, int idFornecedor){
        
        Produto produto = produtoService.consultarProduto(idProduto);
        
        if(produto != null){
                cotacao.setProduto(produto);
        }
        
        Fornecedor fornecedor = fornecedorRepository.findById(idFornecedor).get();
        
        if(fornecedor != null){
                cotacao.setFornecedor(fornecedor);
        }
        
        cotacaoRepository.save(cotacao);
        return "Cotação incluída.";
    }
    
    public String alterarCotacao(Cotacao novaCotacao, int id){
        String msg = "Cotação inexistente";
        
        Cotacao cotacao = cotacaoRepository.findById(id).get();
        
        if(cotacao!= null){
                cotacao.setPreco(novaCotacao.getPreco());
                msg = "Cotação alterada";
        }
        
        cotacaoRepository.save(cotacao);
        
        return msg;
        
    }
    
    public String excluirCotacao(int id ){
        String msg = "Erro ao excluir cotação";
        
        Cotacao cotacao = cotacaoRepository.findById(id).get();
        
            if(cotacao!=null){
                cotacaoRepository.delete(cotacao);
                msg = "Cotação excluído";
            }
        return msg;
    }
}
