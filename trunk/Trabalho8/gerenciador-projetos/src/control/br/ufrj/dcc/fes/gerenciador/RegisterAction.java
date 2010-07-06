//$Id: RegisterAction.java 5579 2010-06-27 00:06:49Z fes $
package control.br.ufrj.dcc.fes.gerenciador;

import static org.jboss.seam.ScopeType.EVENT;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.br.ufrj.dcc.fes.gerenciador.User;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

@Stateful
@Scope(EVENT)
@Name("register")
public class RegisterAction implements Register
{
   @In
   private User user;
   
   @PersistenceContext
   private EntityManager em;
   
   @In
   private FacesMessages facesMessages;
   
   private String verify;
   
   private boolean registered;
   
   public void register()
   {
      if ( user.getPassword().equals(verify) )
      {
         List existing = em.createQuery("select u.username from User u where u.username=#{user.username}")
            .getResultList();
         if (existing.size()==0)
         {
            em.persist(user);
            facesMessages.add("Usuario registrado com sucesso como #{user.username}");
            registered = true;
         }
         else
         {
            facesMessages.addToControl("username", "O login #{user.username} ja existe");
         }
      }
      else 
      {
         facesMessages.addToControl("verify", "A senha de verificacao nao confere");
         verify=null;
      }
   }
   
   public void invalid()
   {
      facesMessages.add("Tente novamente.");
   }
   
   public boolean isRegistered()
   {
      return registered;
   }
   public String getVerify()
   {
      return verify;
   }
   public void setVerify(String verify)
   {
      this.verify = verify;
   }
   
   @Remove
   public void destroy() {}
}
