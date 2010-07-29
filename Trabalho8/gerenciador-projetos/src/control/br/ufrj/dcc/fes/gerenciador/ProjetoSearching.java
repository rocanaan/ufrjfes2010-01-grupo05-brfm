//$Id: ProjetoSearching.java 5579 2010-06-27 00:06:49Z fes $
package control.br.ufrj.dcc.fes.gerenciador;

import javax.ejb.Local;

@Local
public interface ProjetoSearching
{
   public int getPageSize();
   public void setPageSize(int pageSize);
   
   public String getSearchString();
   public void setSearchString(String searchString);
   
   public String getSearchPattern();
   
   public void find();
   public void nextPage();
   public boolean isNextPageAvailable();

   public void destroy();
   
}