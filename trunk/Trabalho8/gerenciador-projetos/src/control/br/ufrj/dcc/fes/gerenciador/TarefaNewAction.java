package control.br.ufrj.dcc.fes.gerenciador;

import static javax.persistence.PersistenceContextType.EXTENDED;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;
import model.br.ufrj.dcc.fes.gerenciador.Tarefa;
import model.br.ufrj.dcc.fes.gerenciador.User;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;


@Stateful
@Name("newTarefa")
@Restrict("#{identity.loggedIn}")
public class TarefaNewAction implements TarefaNew
{
	
	 @PersistenceContext(type=EXTENDED)
	   private EntityManager em;
	 
	   @In @Out
	   private User user;
	   
	   @In(required=false) @Out
	   private Projeto projeto;
	   
	     
	   @In
	   private FacesMessages facesMessages;
	   
	   @In(required=false) 
	   @Out(required=false)
	   private Tarefa tarefa;
	

	@Begin(join=true)
	public void novaTarefa()
	{      
		tarefa = new Tarefa();
		tarefa.setProjeto(projeto);
			     
			   }
	@End
	public void confirm()
	{
	   em.persist(tarefa);
	   facesMessages.add("O projeto #{projeto.nome} possui a nova tarefa #{tarefa.name}");
	 //  log.info("Novo projetoUsuario: #{projetoUsuario.id} para #{user.username}");
	   
	}

	  @End
	  public void cancel() {}
	  
	  @Remove
	  public void destroy() {}


}
