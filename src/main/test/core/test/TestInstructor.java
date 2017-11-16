package core.test;
import core.api.IAdmin;
import core.api.IStudent;
import core.api.impl.Admin;
import core.api.impl.Student;
import core.api.IInstructor;
import core.api.impl.Instructor;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestInstructor  {

    private IAdmin admin; 
    private IStudent student;
    private IInstructor instructor;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
        this.instructor = new Instructor();
    }
    		@Test
    			public void TestWrongInsHW(){//wrong instructor name on hw
    			this.admin.createClass("Test", 2017, "Instructor", 15);
    			this.instructor.addHomework("Instructor2", "Test", 2017, "HW1");
    			assertFalse(this.instructor.homeworkExists("Test", 2017, "HW1"));
    		}
	
    		@Test
			public void TestRightInsHW(){//correct instructor name on hw
			this.admin.createClass("Test", 2017, "Instructor", 15);
			this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
			assertTrue(this.instructor.homeworkExists("Test", 2017, "HW1"));
		}

    		@Test
			public void TestNoHWName(){//no name of hw
			this.admin.createClass("Test", 2017, "Instructor", 15);
			this.instructor.addHomework("Instructor", "Test", 2017, "");
			assertFalse(this.instructor.homeworkExists("Test", 2017, ""));
		}
    		
    		@Test
			public void TestWrongInsAssignsGrade(){//grade assigned by instructor other than instructor of class
			this.admin.createClass("Test", 2017, "Instructor", 15);
			this.student.registerForClass("A", "Test", 2017);
			this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
			this.student.submitHomework("A", "HW1", "HW1Ans", "Test", 2017);
			this.instructor.assignGrade("Instructor2", "Test", 2017, "HW1", "A", 89);
			assertNull(this.instructor.getGrade("Test", 2017, "HW1", "A"));
		}
    		
    		@Test
			public void TestRightInsAssignsGrade(){//grade assigned by instructor of class
			this.admin.createClass("Test", 2017, "Instructor", 15);
			this.student.registerForClass("A", "Test", 2017);
			this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
			this.student.submitHomework("A", "HW1", "HW1Ans", "Test", 2017);
			this.instructor.assignGrade("Instructor", "Test", 2017, "HW1", "A", 89);
			assertNotNull(this.instructor.getGrade("Test", 2017, "HW1", "A"));
		}
    		
    		@Test
			public void TestNoHWSubmitted(){//grade assigned by instructor when no hw was submitted
			this.admin.createClass("Test", 2017, "Instructor", 15);
			this.student.registerForClass("A", "Test", 2017);
			this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
			//this.student.submitHomework("A", "HW1", "HW1Ans", "Test", 2017);
			this.instructor.assignGrade("Instructor", "Test", 2017, "HW1", "A", 89);
			assertNull(this.instructor.getGrade("Test", 2017, "HW1", "A"));
		}
    		
    		@Test
    		public void TestHWAddedbyWrongIns() {//homework added by wrong instructor
    			this.admin.createClass("Test", 2017, "Instructor", 15);
    			this.instructor.addHomework("Instructor2", "Test", 2017, "HW1");
    			assertFalse(this.instructor.homeworkExists("Test", 2017, "HW1"));
    		}
    		
    		@Test
    		public void TestHWAddedbyCorrectIns() {//homework added by correct instructor
    			this.admin.createClass("Test", 2017, "Instructor", 15);
    			this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
    			assertTrue(this.instructor.homeworkExists("Test", 2017, "HW1"));
    		}
    		
    		@Test
			public void TestRightInsAssignsNegGrade(){//neg grade assigned by instructor of class
			this.admin.createClass("Test", 2017, "Instructor", 15);
			this.student.registerForClass("A", "Test", 2017);
			this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
			this.student.submitHomework("A", "HW1", "HW1Ans", "Test", 2017);
			this.instructor.assignGrade("Instructor", "Test", 2017, "HW1", "A", -10);
			assertNull(this.instructor.getGrade("Test", 2017, "HW1", "A"));
		}
    		
}

