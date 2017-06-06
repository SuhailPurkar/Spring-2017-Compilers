//Mark Vo
//cs451
package junit;
import junit.framework.TestCase;
import pass.UnaryPlus;
public class UnaryPlusTest extends TestCase {
	private UnaryPlus plus;
	protected void setUp() throws Exception {
		super.setUp();
		plus = new UnaryPlus();
	}
	protected void tearDown() throws Exception {
	super.tearDown();
	}
	public void testUnaryPlusIt() {
	this.assertEquals(plus.unaryplusit(+ 3), 3);
	this.assertEquals(plus.unaryplusit( + 1 + 1), 2);
	this.assertEquals(plus.unaryplusit(+ + + + + + +4), 4);
	}
}