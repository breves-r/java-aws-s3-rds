package br.edu.infnet.AT.controller;

import br.edu.infnet.AT.model.domain.Cotacao;
import br.edu.infnet.AT.model.domain.Produto;
import br.edu.infnet.AT.model.service.AmazonClient;
import br.edu.infnet.AT.model.service.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path={"/produtos"})
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired 
    private AmazonClient amazonClient;
    
    @GetMapping
    public List<Produto> consultarProdutos(){
        return produtoService.obterLista(); 
    }
    
    @GetMapping("/{id}")
    public Produto consultarProduto(@PathVariable int id){
        return produtoService.consultarProduto(id);
    }
    
    @PostMapping("/incluir")
    private String incluir(@RequestBody Produto produto){
        
        List<Cotacao> listaCotacao = produto.getCotacoes();
        
        if(listaCotacao != null && !listaCotacao.isEmpty()){
            for (Cotacao cotacao : listaCotacao){
                cotacao.setProduto(produto);
            }
        }
        
        return produtoService.incluirProduto(produto);
    }
    
    @PutMapping("/alterar/{id}")
    private String alterar(@RequestBody Produto novoProduto, @PathVariable int id){
        return produtoService.alterarProduto(novoProduto, id);
    }
    
    @DeleteMapping("/excluir/{id}")
    private String excluir(@PathVariable int id){
        
        try{
        Produto produto = produtoService.consultarProduto(id);
        
        if(produto!=null){
        String file = produto.getImagem().split(".com/")[1];

        amazonClient.deleteFile(file);
        }
        }catch(Exception e){}
        
        return produtoService.excluirProduto(id);
    } 
}
