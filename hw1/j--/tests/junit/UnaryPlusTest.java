/** UnaryPlusTest.java
*James Quach
*Bill Campbell
*CS451
*1/30/2017
*HW
*/
package junit;
import junit.framework.TestCase;
import pass.UnaryPlus;
public class UnaryPlusTest extends TestCase{
	private UnaryPlus unaryplus;
	protected void setUp() throws Exception{
		super.setUp();
		unaryplus = new UnaryPlus();
	}
	protected void tearDown() throws Exception{
		super.tearDown();
	}
	public void testUnaryPlus(){
		this.assertEquals(unaryplus.uplus(+7),7);
		this.assertEquals(unaryplus.uplus(+ - + - + 7),7);
		this.assertEquals(unaryplus.uplus(+ + + 7),7);
		this.assertEquals(unaryplus.uplus(+ (+7) + 7),14);
	}
}