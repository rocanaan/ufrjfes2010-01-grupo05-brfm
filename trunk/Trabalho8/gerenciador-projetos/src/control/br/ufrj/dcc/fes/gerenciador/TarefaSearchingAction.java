//$Id: ProjetoSearchingAction.java 8998 2010-06-27 03:08:11Z fes $
package control.br.ufrj.dcc.fes.gerenciador;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;
import model.br.ufrj.dcc.fes.gerenciador.Tarefa;
import model.br.ufrj.dcc.fes.gerenciador.User;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;

@Stateful
@Name("tarefaSearch")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class TarefaSearchingAction implements Searching
{
    @PersistenceContext
    private EntityManager em;
    
    private String searchString;
    private int pageSize = 10;
    private int page;
    private boolean nextPageAvailable;
    
    @In
    private Projeto projeto;
    
    @In
    private User user;
   
    @DataModel
    private List<Tarefa> tarefas;
   
    public void find() 
    {
        page = 0;
        queryTarefas();
    }

    public void nextPage() 
    {
        page++;
        queryTarefas();
    }
    
    
    //TODO Bug
    @Begin
    private void queryTarefas() {
    //	projeto = em.merge(projeto);
    	List<Tarefa> results = em.createQuery("select h from Tarefa h where  h.projeto.id=#{projeto.id} and h.usuario.username=#{identity.username} and lower(h.nome) like #{patternTarefa} or lower(h.descricaoBreve) like #{patternTarefa}")
        						.setMaxResults(pageSize+1)
                                .setFirstResult(page * pageSize)
                                .getResultList(); 
    	
        
        nextPageAvailable = results.size() > pageSize;
        if (nextPageAvailable) 
        {
        	tarefas = new ArrayList<Tarefa>(results.subList(0,pageSize));
        } else {
            tarefas = results;
        }
    }

    public boolean isNextPageAvailable()
    {
        return nextPageAvailable;
    }
   
   public int getPageSize() {
      return pageSize;
   }
   
   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }
   
   @Factory(value="patternTarefa", scope=ScopeType.EVENT)
   public String getSearchPattern()
   {
      return searchString==null ? 
            "%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
   }
   
   public String getSearchString()
   {
      return searchString;
   }
   
   public void setSearchString(String searchString)
   {
      this.searchString = searchString;
   }
   
   @Remove
   public void destroy() {}
}
