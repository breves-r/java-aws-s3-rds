package br.edu.infnet.AT.controller;

import br.edu.infnet.AT.model.domain.Produto;
import br.edu.infnet.AT.model.service.AmazonClient;
import br.edu.infnet.AT.model.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path={"/bucket"})
public class BucketController {
    
    @Autowired 
    private AmazonClient amazonClient;
    
    @Autowired
    ProdutoService produtoService;
    
    @PostMapping("/upload/{idProduto}")
    public String uploadFile(@RequestPart(value="file") MultipartFile file, @PathVariable int idProduto) {
        try{
            String img = "";
            Produto produto = produtoService.consultarProduto(idProduto);
        
            if(produto != null){
                    img = amazonClient.uploadFile(file);
                    produto.setImagem(img);
                    produtoService.alterarProduto(produto, idProduto);
            }
            
            return img;
        }catch(Exception e){
            return "Produto inexistente";
        }
    }
    
    @GetMapping("/download")
    public String downloadFile(@RequestPart(value="file") String file) {
        return amazonClient.downloadFile(file);
    }
    
    @DeleteMapping("/delete")
    public String deleteFile(@RequestPart(value="file") String file) {
        return amazonClient.deleteFile(file);
    }
    
    @DeleteMapping("/deleteFileProduto/{idProduto}")
    public String deleteFilePorProduto(@PathVariable int idProduto) {
        try{
            String retorno = "";
            
            Produto produto = produtoService.consultarProduto(idProduto);
        
            if(produto != null){
                    String file = produto.getImagem().split(".com/")[1];
                    produto.setImagem(null);
                    produtoService.alterarProduto(produto, idProduto);
                    
                    return amazonClient.deleteFile(file);
            }
            
            return retorno;
        }catch(Exception e){
            return "Produto inexistente";
        }
        
    }
}
