//$Id: ProjetoUsuarioListAction.java 8748 2010-06-20 12:08:30Z fes $
package control.br.ufrj.dcc.fes.gerenciador;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.br.ufrj.dcc.fes.gerenciador.ProjetoUsuario;
import model.br.ufrj.dcc.fes.gerenciador.User;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Scope(SESSION)
@Name("projetoUsuarioList")
@Restrict("#{identity.loggedIn}")
@TransactionAttribute(REQUIRES_NEW)
public class ProjetoUsuarioListAction implements ProjetoUsuarioList, Serializable
{
   private static final long serialVersionUID = 1L;
   
   @PersistenceContext
   private EntityManager em;
   
   @In
   private User user;
   
   @DataModel
   private List<ProjetoUsuario> projetosUsuarios;
   @DataModelSelection 
   private ProjetoUsuario projetoUsuario;
   
   @Logger 
   private Log log;
   
   @Factory
   @Observer("participacaoConfirmed")
   public void getProjetosUsuarios()
   {
	   projetosUsuarios = em.createQuery("select b from ProjetoUsuario b where b.user.username = :username order by b.checkinDate")
            .setParameter("username", user.getUsername())
            .getResultList();
   }
   
   public void cancel()
   {
      log.info("Cancel projetoUsuario: #{projetoUsuarioList.projetoUsuario.id} for #{user.username}");
      ProjetoUsuario cancelled = em.find(ProjetoUsuario.class, projetoUsuario.getId());
      if (cancelled!=null) em.remove( cancelled );
      getProjetosUsuarios();
      FacesMessages.instance().add("Participacao #{projetoUsuario.getId()} cancelada, transacao #0");
   }
   
   public ProjetoUsuario getProjetoUsuario()
   {
      return projetoUsuario;
   }
   
   @Remove
   public void destroy() {}
}
