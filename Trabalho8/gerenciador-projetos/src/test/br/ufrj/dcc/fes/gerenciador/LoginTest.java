//$Id: LoginTest.java 5810 2010-07-16 06:46:47Z fes $
package test.br.ufrj.dcc.fes.gerenciador;

import org.jboss.seam.core.Manager;
import org.jboss.seam.web.Session;
import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.Test;

public class LoginTest extends SeamTest
{
   
   @Test
   public void testLoginComponent() throws Exception
   {
      new ComponentTest() {

         @Override
         protected void testComponents() throws Exception
         {
            assert getValue("#{identity.loggedIn}").equals(false);
            setValue("#{identity.username}", "demo");
            setValue("#{identity.password}", "demo");
            invokeMethod("#{identity.login}");
            assert getValue("#{user.name}").equals("Usuario Demo");
            assert getValue("#{user.username}").equals("demo");
            assert getValue("#{user.password}").equals("demo");
            assert getValue("#{identity.loggedIn}").equals(true);
            invokeMethod("#{identity.logout}");
            assert getValue("#{identity.loggedIn}").equals(false);
            setValue("#{identity.username}", "demo");
            setValue("#{identity.password}", "demo");
            invokeMethod("#{identity.login}");
            assert getValue("#{identity.loggedIn}").equals(false);
         }
         
      }.run();
   }
   
   @Test
   public void testLogin() throws Exception
   {
      
      new FacesRequest() {
         
         @Override
         protected void invokeApplication()
         {
            assert !isSessionInvalid();
            assert getValue("#{identity.loggedIn}").equals(false);
         }
         
      }.run();
      
      new FacesRequest() {

         @Override
         protected void updateModelValues() throws Exception
         {
            assert !isSessionInvalid();
            setValue("#{identity.username}", "demo");
            setValue("#{identity.password}", "demo");
         }

         @Override
         protected void invokeApplication()
         {
            invokeAction("#{identity.login}");
         }

         @Override
         protected void renderResponse()
         {
            assert getValue("#{user.name}").equals("Usuario Demo");
            assert getValue("#{user.username}").equals("demo");
            assert getValue("#{user.password}").equals("demo");
            assert !Manager.instance().isLongRunningConversation();
            assert getValue("#{identity.loggedIn}").equals(true);
         }
         
      }.run();
      
      new FacesRequest() {

         @Override
         protected void invokeApplication()
         {
            assert !isSessionInvalid();
            assert getValue("#{identity.loggedIn}").equals(true);
         }
         
      }.run();
      
      new FacesRequest() {

         @Override
         protected void invokeApplication()
         {
            assert !Manager.instance().isLongRunningConversation();
            assert !isSessionInvalid();
            invokeMethod("#{identity.logout}");
            assert Session.instance().isInvalid();
         }

         @Override
         protected void renderResponse()
         {
            assert getValue("#{identity.loggedIn}").equals(false);
            assert Session.instance().isInvalid();
         }
         
      }.run();
      
   }

}