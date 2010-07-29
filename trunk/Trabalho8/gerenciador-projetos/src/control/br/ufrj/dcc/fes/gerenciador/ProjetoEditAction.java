package control.br.ufrj.dcc.fes.gerenciador;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;


@Stateful
@Name("editarProjeto")
@Restrict("#{identity.loggedIn}")
public class ProjetoEditAction implements ProjetoEdit
{
	

		
		 @PersistenceContext //(type=EXTENDED)
		   private EntityManager em;
		   
		   @In  @Out
		   private Projeto projeto;
		   
		     
		   @In
		   private FacesMessages facesMessages;
		   
		   @Logger 
		   private Log log;
		   
		
		//   @Begin(join=true)
		   public void selectProjeto() //(Projeto selectedProjeto)
		   {
		      //projeto = em.merge(selectedProjeto);
		   }
		
		  
		   public void updateProjeto()
		   {
		      
			     // projeto.setAddress(address));
			     // projeto.setName(name);
		   }
		   
		   
			//@End
			   public void confirmProjeto()
			   {
		//  updateProjeto();
	      em.merge(projeto);
	      facesMessages.add("Projeto #{projeto.nome} inserido com sucesso");
	      log.info("Projeto atualizado: #{projeto.id} por #{user.username}");
	   }
	   
	//   @End
	   public void cancel() {}
	   
	   @Remove
	   public void destroy() {}

}
