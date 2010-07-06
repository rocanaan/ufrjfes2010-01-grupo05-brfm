package test.br.ufrj.dcc.fes.gerenciador;

import org.testng.annotations.BeforeClass;

import junit.framework.TestCase;

public class ParticiparProjetoTest extends TestCase {
    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code   
    	System.out.println("@BeforeClass - oneTimeSetUp");
    }
    
    /*
     *       @Test(expected = ArithmeticException.class)  
	public void divisionWithException() {  
	  int i = 1/0;
	}
     */
}
