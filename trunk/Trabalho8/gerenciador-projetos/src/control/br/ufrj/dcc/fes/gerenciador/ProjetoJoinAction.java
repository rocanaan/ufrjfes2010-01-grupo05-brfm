//$Id: ProjetoJoinAction.java 5579 2010-06-27 00:06:49Z fes $
package control.br.ufrj.dcc.fes.gerenciador;

import static javax.persistence.PersistenceContextType.EXTENDED;

import java.util.Calendar;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;
import model.br.ufrj.dcc.fes.gerenciador.ProjetoUsuario;
import model.br.ufrj.dcc.fes.gerenciador.User;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Name("participarProjeto")
@Restrict("#{identity.loggedIn}")
public class ProjetoJoinAction implements ProjetoJoin
{
   
   @PersistenceContext(type=EXTENDED)
   private EntityManager em;
   
   @In 
   private User user;
   
   @In(required=false) @Out
   private Projeto projeto;
   
   @In(required=false) 
   @Out(required=false)
   private ProjetoUsuario projetoUsuario;
     
   @In
   private FacesMessages facesMessages;
      
   @In
   private Events events;
   
   @Logger 
   private Log log;
   
   private boolean participacaoValid;
   
   @Begin
   public void selectProjeto(Projeto selectedProjeto)
   {
      projeto = em.merge(selectedProjeto);
   }
   
   public void participarProjeto()
   {      
	  projetoUsuario = new ProjetoUsuario(projeto, user);
      Calendar calendar = Calendar.getInstance();
      projetoUsuario.setCheckinDate( calendar.getTime() );
      calendar.add(Calendar.DAY_OF_MONTH, 1);
      projetoUsuario.setCheckoutDate( calendar.getTime() );
   }
   
   public void setParticipacaoDetails()
   {
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.DAY_OF_MONTH, -1);
      if ( projetoUsuario.getCheckinDate().before( calendar.getTime() ) )
      {
         facesMessages.addToControl("checkinDate", "Check in date must be a future date");
         participacaoValid=false;
      }
      else if ( !projetoUsuario.getCheckinDate().before( projetoUsuario.getCheckoutDate() ) )
      {
         facesMessages.addToControl("checkoutDate", "Check out date must be later than check in date");
         participacaoValid=false;
      }
      else
      {
    	  participacaoValid=true;
      }
   }

   
   public boolean isParticipacaoValid()
   {
      return participacaoValid;
   }
   
   @End
   public void confirm()
   {
      em.persist(projetoUsuario);
      facesMessages.add("O usuario #{user.nome} esta participando do projeto #{projeto.name}");
      log.info("Novo projetoUsuario: #{projetoUsuario.id} para #{user.username}");
      events.raiseTransactionSuccessEvent("participacaoConfirmed");
   }
   
   @End
   public void cancel() {}
   
   @Remove
   public void destroy() {}
}