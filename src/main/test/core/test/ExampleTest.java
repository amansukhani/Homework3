package core.test;

import core.api.IAdmin;
import core.api.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ExampleTest {

    private IAdmin admin; 

    @Before
    public void setup() {
        this.admin = new Admin();
    }

    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }
   @Test
   public void instructorCourseLimitViolated() {
	   this.admin.createClass("Test", 2017, "Instructor", 15);
	   this.admin.createClass("Test2", 2017, "Instructor", 15);
	   this.admin.createClass("Test3", 2017, "Instructor", 15);
	   assertFalse(this.admin.classExists("Test3", 2017));
   }
   
   @Test
   public void instructorCourseLimitCornerCase() {
	   
   }
}
