package core.test;

import core.api.IAdmin;
import core.api.IStudent;
import core.api.impl.Admin;
import core.api.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestAdmin {

    private IAdmin admin; 
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
    }

    @Test
    public void testMakeClass() { //class creation
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass2() {//class taught in past years
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }
    
    @Test
    public void classCapacityNegative(){//capacity negative
 	   this.admin.createClass("Test", 2017, "Instructor", -1);
 	   assertFalse(this.admin.classExists("Test", 2017));
    }
    
    @Test
    public void classCapacityZero(){//capacity zero
 	   this.admin.createClass("Test", 2017, "Instructor", 0);
 	   assertFalse(this.admin.classExists("Test", 2017));
    }
    
    @Test
    public void noInstructor() { //no instructor name
        this.admin.createClass("Test", 2017, "", 15);
        assertFalse(this.admin.classExists("Test", 2017));
    }
    
    @Test
    public void noClass() { //no class name
        this.admin.createClass("", 2017, "Instructor", 15);
        assertFalse(this.admin.classExists("", 2017));
    }
    
    @Test
    public void instructorCourseDuplicates() { //2 instructors teaching the same course in the same year
 	  this.admin.createClass("Test", 2017, "Instructor", 15);
 	  this.admin.createClass("Test", 2017, "Instructor2", 15);
 	  assertFalse((this.admin.getClassInstructor("Test",2017)).equals("Intructor2"));
    }
    
    @Test
    public void instructorCourseLimitCornerCase() { //instructor teaching 2 courses
 	  this.admin.createClass("Test", 2017, "Instructor", 15);
 	  this.admin.createClass("Test2", 2017, "Instructor", 15);
 	  assertTrue(this.admin.classExists("Test2",2017));
    }
    
   @Test
   public void instructorCourseLimitViolated() { //instructor cannot teach more than 2 classes
	   this.admin.createClass("Test", 2017, "Instructor", 15);
	   this.admin.createClass("Test2", 2017, "Instructor", 15);
	   this.admin.createClass("Test3", 2017, "Instructor", 15);
	   assertFalse(this.admin.classExists("Test3", 2017));
   }
   
   @Test
   public void changeCapacityLower() { //changing capacity to less than people enrolled
	   this.admin.createClass("Test", 2017, "Instructor", 15);
	   this.student.registerForClass("A", "Test", 2017);
	   this.student.registerForClass("B", "Test", 2017);
	   this.admin.changeCapacity("Test",2017,1);
	   assertEquals(15,this.admin.getClassCapacity("Test", 2017));
   }
   
   @Test
   public void changeCapacityUpper() { //changing capacity to more than people enrolled
	   this.admin.createClass("Test", 2017, "Instructor", 15);
	   this.student.registerForClass("A", "Test", 2017);
	   this.admin.changeCapacity("Test",2017,3);
	   assertEquals(3,this.admin.getClassCapacity("Test", 2017));
   }
   
   @Test
   public void changeCapacityCornerCase() { //changing capacity to same number as people enrolled
	   this.admin.createClass("Test", 2017, "Instructor", 15);
	   this.student.registerForClass("A", "Test", 2017);
	   this.admin.changeCapacity("Test",2017,1);
	   assertEquals(1,this.admin.getClassCapacity("Test", 2017));
   }
   
   @Test
   public void changeCapacitytoMore() { //changing capacity to more than org capacity
	   this.admin.createClass("Test", 2017, "Instructor", 15);
	   this.admin.changeCapacity("Test",2017, 16);
	   assertEquals(16,this.admin.getClassCapacity("Test", 2017));
	  
   }
   
   @Test
   public void changeCapacitytoZero() { //changing capacity to zero
	   this.admin.createClass("Test", 2017, "Instructor", 15);
	   this.admin.changeCapacity("Test",2017, 0);
	   assertEquals(15,this.admin.getClassCapacity("Test", 2017));
	  
   }
   
   @Test
   public void changeCapacitytoNeg() { //changing capacity to negative
	   this.admin.createClass("Test", 2017, "Instructor", 15);
	   this.admin.changeCapacity("Test",2017, -1);
	   assertEquals(15,this.admin.getClassCapacity("Test", 2017));
	  
   }
   
}
