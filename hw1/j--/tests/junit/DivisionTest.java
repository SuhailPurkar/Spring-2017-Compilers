/** DivisionTest.java
*James Quach
*William Campbell
*CS451
*1/30/2017
*HW1
*/
package junit;
import junit.framework.TestCase;
import pass.Division;
public class DivisionTest extends TestCase{
	private Division division;
	protected void setUp() throws Exception{
		super.setUp();
		division = new Division();
	}
	protected void tearDown() throws Exception{
		super.tearDown();
	}
	public void testDivide(){
		this.assertEquals(division.divide(0, 7),0);
		this.assertEquals(division.divide(99, 1), 99);
		this.assertEquals(division.divide(99, 3), 33);
	}
}