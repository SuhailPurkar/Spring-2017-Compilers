//Mark Vo
//cs451
package junit;
import junit.framework.TestCase;
import pass.Modulo;
public class ModuloTest extends TestCase {
	private Modulo mod;
	protected void setUp() throws Exception {
		super.setUp();
		mod = new Modulo();
	}
	protected void tearDown() throws Exception {
	super.tearDown();
	}
	public void testMod() {
	this.assertEquals(mod.modulo(5, 3), 2);
	this.assertEquals(mod.modulo(1, 1), 0);
	this.assertEquals(mod.modulo(1, 2), 1);
	}
}