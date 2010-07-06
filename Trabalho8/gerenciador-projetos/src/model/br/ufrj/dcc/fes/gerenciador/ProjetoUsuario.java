//$Id: ProjetoUsuario.java 5579 2010-06-27 00:06:49Z fes $
package model.br.ufrj.dcc.fes.gerenciador;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;
import org.jboss.seam.annotations.Name;

@Entity
@Name("projetoUsuario")
public class ProjetoUsuario implements Serializable
{
   private Long id;
   private User user;
   private Projeto projeto;
   private Date checkinDate;
   private Date checkoutDate;
   private String observacoes;
   private int ultimoRelatorioMes;
   private int ultimoRelatorioAno;
   private boolean responsavel;
   private int equipe;
   
   public ProjetoUsuario() {}
   
   public ProjetoUsuario(Projeto projeto, User user)
   {
      this.projeto = projeto;
      this.user = user;
   }
   

   @Transient
   public int getNights()
   {
      return (int) ( checkoutDate.getTime() - checkinDate.getTime() ) / 1000 / 60 / 60 / 24;
   }

   @Id @GeneratedValue
   public Long getId()
   {
      return id;
   }
   public void setId(Long id)
   {
      this.id = id;
   }
   
   @NotNull(message="Campo faltando")
   @Basic @Temporal(TemporalType.DATE) 
   public Date getCheckinDate()
   {
      return checkinDate;
   }
   public void setCheckinDate(Date datetime)
   {
      this.checkinDate = datetime;
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
   
   @ManyToOne @NotNull
   public User getUser()
   {
      return user;
   }
   public void setUser(User user)
   {
      this.user = user;
   }
   
   @Basic @Temporal(TemporalType.DATE) 
   @NotNull(message="Campo faltando")
   public Date getCheckoutDate()
   {
      return checkoutDate;
   }
   public void setCheckoutDate(Date checkoutDate)
   {
      this.checkoutDate = checkoutDate;
   }
   
   
  
   
   @Transient
   public String getDescription()
   {
      DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
      return projeto==null ? null : projeto.getNome() + 
            ", " + df.format( getCheckinDate() ) + 
            " ate " + df.format( getCheckoutDate() );
   }

   public boolean isResponsavel()
   {
      return responsavel;
   }

   public void setResponsavel(boolean responsavel)
   {
      this.responsavel = responsavel;
   }
   
  /* @Pattern(regex="^\\d*$", message="Campo numerico")
   @NotNull(message="Campo faltando")
   @Length(min=1, max=10, message="Campo faltando")*/
   public int getEquipe()
   {
      return equipe;
   }

   public void setEquipe(int equipe)
   {
      this.equipe = equipe;
   }
   
   
   public String getObservacoes()
   {
      return observacoes;
   }

   public void setObservacoes(String observacoes)
   {
      this.observacoes = observacoes;
   }

   public int getUltimoRelatorioMes()
   {
      return ultimoRelatorioMes;
   }

   public void setUltimoRelatorioMes(int ultimoRelatorioMes)
   {
      this.ultimoRelatorioMes = ultimoRelatorioMes;
   }

   public int getUltimoRelatorioAno()
   {
      return ultimoRelatorioAno;
   }

   public void setUltimoRelatorioAno(int ultimoRelatorioAno)
   {
      this.ultimoRelatorioAno = ultimoRelatorioAno;
   }
   
   @Override
   public String toString()
   {
      return "ProjetoUsuario(" + user + ","+ projeto + ")";
   }

}
