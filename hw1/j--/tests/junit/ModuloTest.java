/** ModuloTest.java
*James Quach
*William Campbell
*CS451
*1/30/2017
*HW1
*/
package junit;
import junit.framework.TestCase;
import pass.Modulo;
public class ModuloTest extends TestCase{
	private Modulo modulo;
	protected void setUp() throws Exception{
		super.setUp();
		modulo = new Modulo();
	}
	protected void tearDown() throws Exception{
		super.tearDown();
	}
	public void testModulo(){
		this.assertEquals(modulo.mod(0, 7),0);
		this.assertEquals(modulo.mod(7, 1), 0);
		this.assertEquals(modulo.mod(130, 3), 1);
	}
}