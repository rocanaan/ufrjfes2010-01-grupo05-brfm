//$Id: ProjetoSearchingAction.java 8998 2010-06-27 03:08:11Z fes $
package control.br.ufrj.dcc.fes.gerenciador;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;

@Stateful
@Name("projetoSearch")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class ProjetoSearchingAction implements ProjetoSearching
{
    @PersistenceContext
    private EntityManager em;
    
    private String searchString;
    private int pageSize = 10;
    private int page;
    private boolean nextPageAvailable;
   
    @DataModel
    private List<Projeto> projetos;
   
    public void find() 
    {
        page = 0;
        queryProjetos();
    }

    public void nextPage() 
    {
        page++;
        queryProjetos();
    }
    
    //TODO colocar nome do User do cliente
    private void queryProjetos() {
        List<Projeto> results = em.createQuery("select h from Projeto h where lower(h.nome) like #{pattern} or lower(h.descricaoBreve) like #{pattern} or lower(h.localizacao) like #{pattern} or lower(h.cliente) like #{pattern}")
                                .setMaxResults(pageSize+1)
                                .setFirstResult(page * pageSize)
                                .getResultList();
        
        nextPageAvailable = results.size() > pageSize;
        if (nextPageAvailable) 
        {
            projetos = new ArrayList<Projeto>(results.subList(0,pageSize));
        } else {
            projetos = results;
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
   
   @Factory(value="pattern", scope=ScopeType.EVENT)
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
