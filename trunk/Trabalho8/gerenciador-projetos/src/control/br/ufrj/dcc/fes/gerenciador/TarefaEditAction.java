package control.br.ufrj.dcc.fes.gerenciador;

import static javax.persistence.PersistenceContextType.EXTENDED;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;
import model.br.ufrj.dcc.fes.gerenciador.Tarefa;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;


@Stateful
@Name("editarTarefa")
@Restrict("#{identity.loggedIn}")
public class TarefaEditAction implements TarefaEdit
{
	

	
	 @PersistenceContext(type=EXTENDED)
	   private EntityManager em;
	   
	   @In(required=false)
	   @Out(required=false)
	   private Tarefa tarefa;
	   
	   
	   @In(required=false)
	   private Projeto projeto;
	   
	     
	   @In
	   private FacesMessages facesMessages;
	   
	   @Logger 
	   private Log log;
	   
	
	   @Begin(join=true)
	   public void selectTarefa(Tarefa selectedTarefa)
	   {
	      tarefa = em.merge(selectedTarefa);
	   }
	
	  
	   public void updateTarefa()
	   {
	      
		     // projeto.setAddress(address));
		     // projeto.setName(name);
	   }
	   
	   
	@End
	public void confirmTarefa()
		   {
	//  updateProjeto();
     em.merge(tarefa);
     facesMessages.add("Tarefa #{tarefa.nome} inserido com sucesso");
  //   log.info("Tarefa atualizada: #{tarefa.id} por #{user.username}");
  }
	


  @End
  public void cancel() {}
  
  @Remove
  public void destroy() {}


}
