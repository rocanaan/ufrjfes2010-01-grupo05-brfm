//$Id: ChangePassword.java 5579 2010-06-27 00:06:49Z fes $
package control.br.ufrj.dcc.fes.gerenciador;

import javax.ejb.Local;

@Local
public interface ChangePassword
{
   public void changePassword();
   public boolean isChanged();
   
   public String getVerify();
   public void setVerify(String verify);
   
   public void destroy();
}