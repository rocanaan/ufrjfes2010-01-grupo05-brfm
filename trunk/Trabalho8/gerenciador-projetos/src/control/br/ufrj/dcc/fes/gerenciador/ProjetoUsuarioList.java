//$Id: ProjetoUsuarioList.java 5579 2007-06-27 00:06:49Z fes $
package control.br.ufrj.dcc.fes.gerenciador;

import javax.ejb.Local;

import model.br.ufrj.dcc.fes.gerenciador.ProjetoUsuario;

@Local
public interface ProjetoUsuarioList
{
   public void getProjetosUsuarios();
   public ProjetoUsuario getProjetoUsuario();
   public void cancel();
   public void destroy();
}