package model.br.ufrj.dcc.fes.gerenciador;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;

@Entity
@Name("tarefa")
public class Tarefa implements Serializable
{
	   private Long id;
	   private Projeto projeto;
	   private User usuario;
	   private String nome;
	   private String prazo;
	   private String urgencia;
	   private String descricaoBreve;
	   private String descricao;
	   private String andamento;
	   private String arquivos; //TODO teste para arquivos... colocar ArquivoTarefa como entidade separada MAnyToOne
	   
	   @Id @GeneratedValue
	   public Long getId()
	   {
	      return id;
	   }
	   public void setId(Long id)
	   {
	      this.id = id;
	   }
	   
	   @ManyToOne @NotNull
	   public Projeto getProjeto()
	   {
	      return projeto;
	   }
	   public void setProjeto(Projeto projeto)
	   {
	      this.projeto = projeto;
	   }
	   
	   //TODO No prototipo somente um usuario por tarefa, a ideia e que na aplicacao final seja possivel alocar varios usuarios numamesma tarefa e selecionar um responsavel
	   @OneToOne
	   public User getUsuario()
	   {
	      return usuario;
	   }
	   public void setUsuario(User user)
	   {
	      this.usuario = user;
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
	   
	   @Length(max=100) 
	   public String getDescricao()
	   {
	      return descricao;
	   }
	   public void setDescricao(String descricao)
	   {
	      this.descricao = descricao;
	   }
	   
	
	   public String getArquivos()
	   {
	      return arquivos;
	   }
	   public void setArquivos(String arquivos)
	   {
	      this.arquivos = arquivos;
	   }
	   
	   public String getDescricaoBreve() {
			return descricaoBreve;
		}
		public void setDescricaoBreve(String descricaoBreve) {
			this.descricaoBreve = descricaoBreve;
		}

	   @Length(min=4, max=6)
	   public String getPrazo()
	   {
	      return prazo;
	   }
	   public void setPrazo(String prazo)
	   {
	      this.prazo = prazo;
	   }
	   
	   @Length(min=1, max=10) //TODO trocar para valor entre 0 a 10
	   public String getUrgencia()
	   {
	      return urgencia;
	   }
	   public void setUrgencia(String urgencia)
	   {
	      this.urgencia = urgencia;
	   }
	   
	   @Length(min=1, max=40)
	   public String getAndamento()
	   {
	      return andamento;
	   }
	   public void setAndamento(String andamento)
	   {
	      this.andamento = andamento;
	   }

	   
	   @Override
	   public String toString()
	   {
	      return "Tarefa(" + nome + "," + usuario + ","+ projeto + ")";
	   }

}
