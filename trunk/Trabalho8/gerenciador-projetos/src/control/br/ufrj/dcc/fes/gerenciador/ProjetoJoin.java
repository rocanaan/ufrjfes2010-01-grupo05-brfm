//$Id: ProjetoJoin.java 5579 2010-06-27 00:06:49Z fes $
package control.br.ufrj.dcc.fes.gerenciador;

import javax.ejb.Local;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;

@Local
public interface ProjetoJoin
{
   public void selectProjeto(Projeto selectedProjeto);
   
   public void participarProjeto();
   
   public void setParticipacaoDetails();
   public boolean isParticipacaoValid();
   
   public void confirm();
   
   public void cancel();
   
   public void destroy();
   
}