package br.edu.infnet.AT.controller;

import br.edu.infnet.AT.model.domain.Cotacao;
import br.edu.infnet.AT.model.domain.Produto;
import br.edu.infnet.AT.model.service.CotacaoService;
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
@RequestMapping(path={"/cotacoes"})
public class CotacaoController {
    
    @Autowired
    private CotacaoService cotacaoService;
    
    
    
    @GetMapping
    public List<Cotacao> consultarCotacoes(){
        return cotacaoService.obterLista(); 
    }
    
    @GetMapping("/produto={idProduto}")
    public List<Cotacao> consultarCotacaoPorProduto(@PathVariable int idProduto){
        return cotacaoService.obterPorProduto(idProduto);
    }
    
    @GetMapping("/{id}")
    public Cotacao consultarCotacao(@PathVariable int id){
        return cotacaoService.consultarCotacao(id);
    }
    
    @PostMapping("/incluir/produto={idProduto}&fornecedor={idFornecedor}")
    private String incluir(@RequestBody Cotacao cotacao, @PathVariable int idProduto, @PathVariable int idFornecedor){

        return cotacaoService.incluirCotacao(cotacao, idProduto, idFornecedor);
    }
    
    @PutMapping("/alterar/{id}")
    private String alterar(@RequestBody Cotacao novaCotacao, @PathVariable int id){
        return cotacaoService.alterarCotacao(novaCotacao, id);
    }
    
    @DeleteMapping("/excluir/{id}")
    private String excluir(@PathVariable int id){
        return cotacaoService.excluirCotacao(id);
    } 
    
    
    
}
