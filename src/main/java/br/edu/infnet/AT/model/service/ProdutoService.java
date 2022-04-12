package br.edu.infnet.AT.model.service;

import br.edu.infnet.AT.model.domain.Produto;
import br.edu.infnet.AT.model.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public List<Produto> obterLista(){
        return (List<Produto>) produtoRepository.findAll();
    }
    
    public Produto consultarProduto(int id){
        return produtoRepository.findById(id).get();
    }
    
    public String incluirProduto(Produto produto){
        produtoRepository.save(produto);
        
        return "Produto incluído";
    }
    
    public String alterarProduto(Produto novoProduto, int id){
        String msg = "Produto inexistente";
        
        try{
        Produto produto = produtoRepository.findById(id).get();
        
        if(produto!= null){
            produto.setNome(novoProduto.getNome());
            produto.setCodigo(novoProduto.getCodigo());
            produto.setImagem(novoProduto.getImagem());
            
            produtoRepository.save(produto);
            msg = "Produto alterado";
        }
        }catch(Exception e){
            
        }
        
        return msg;
        
    }
    
    public String excluirProduto(int id ){
        String msg = "Erro ao excluir produto";
        try{
        Produto produto = produtoRepository.findById(id).get();
        
            if(produto!=null){
                produtoRepository.delete(produto);
                msg = "Produto excluído";
            }
        }catch(Exception e){ }
        return msg;
    }
}
