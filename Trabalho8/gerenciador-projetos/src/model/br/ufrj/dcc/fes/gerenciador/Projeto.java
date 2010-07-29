//$Id: Projeto.java 5579 2010-06-27 00:06:49Z fes $
package model.br.ufrj.dcc.fes.gerenciador;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;

@Entity
@Name("projeto")
public class Projeto implements Serializable
{
   private Long id;
   private String nome;
   private String descricaoBreve;
   private String cliente; //TODO virar entidade User 
   private String localizacao;
   private String descricao;
   //TODO adicionar intervalo de tempo previsto

   
   @Id @GeneratedValue
   public Long getId()
   {
      return id;
   }
   public void setId(Long id)
   {
      this.id = id;
   }
   
   @Length(max=50) @NotNull
   public String getNome()
   {
      return nome;
   }
   public void setNome(String nome)
   {
      this.nome = nome;
   }
   
   @Length(max=100) @NotNull
   public String getDescricaoBreve()
   {
      return descricaoBreve;
   }
   public void setDescricaoBreve(String descricaoBreve)
   {
      this.descricaoBreve = descricaoBreve;
   }
   
   @Length(max=40) @NotNull
   public String getLocalizacao()
   {
      return localizacao;
   }
   public void setLocalizacao(String localizacao)
   {
      this.localizacao = localizacao;
   }
   
   @NotNull
   public String getCliente()
   {
      return cliente;
   }
   public void setCliente(String cliente)
   {
      this.cliente = cliente;
   }
   

   public String getDescricao()
   {
      return descricao;
   }
   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }
   
   
   @Override
   public String toString()
   {
      return "Projeto(" + nome + "," + cliente + "," + descricaoBreve + "," + localizacao + "," + id + ")";
   }
}
