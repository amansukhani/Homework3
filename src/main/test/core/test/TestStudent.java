package core.test;
import core.api.IAdmin;
import core.api.IStudent;
import core.api.impl.Admin;
import core.api.impl.Student;
import org.junit.Before;
import core.api.IInstructor;
import core.api.impl.Instructor;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestStudent {

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
    public void noStudentName() { //no student name
 	   this.admin.createClass("Test", 2017, "Instructor", 15);
 	   this.student.registerForClass("", "Test", 2017);
 	   assertFalse(this.student.isRegisteredFor("","Test", 2017));
    
    		}
    
    
    @Test
    public void validStudentandCapacity() { //student name valid & class not full
 	   this.admin.createClass("Test", 2017, "Instructor", 15);
 	   this.student.registerForClass("A", "Test", 2017);
 	   assertTrue(this.student.isRegisteredFor("A","Test", 2017));
    
    		}
        
    @Test
    public void studentReginFullClass() { //student registered in a class that is full
 	   this.admin.createClass("Test", 2017, "Instructor", 2);
 	   this.student.registerForClass("A", "Test", 2017);
 	   this.student.registerForClass("B", "Test", 2017);
 	   this.student.registerForClass("C", "Test", 2017);
 	   assertFalse(this.student.isRegisteredFor("C","Test", 2017));
    		}
    
    @Test
    public void studentInClassThatDNE(){ //student registers in class that does not exist
    		this.student.registerForClass("A", "Test", 2017);
    		assertFalse(this.student.isRegisteredFor("A", "Test", 2017));    
    		}
    
    @Test
    public void studentDropClassRight(){ //student dropped the class 
    		this.admin.createClass("Test", 2017, "Instructor", 2);
    		this.student.registerForClass("A", "Test", 2017);
    		this.student.dropClass("A", "Test", 2017);
    		assertFalse(this.student.isRegisteredFor("A", "Test", 2017));
    }
    
    @Test
    public void studentDropClassWrong(){ //student dropped the class 
    		this.admin.createClass("Test", 2017, "Instructor", 2);
    		//this.student.registerForClass("A", "Test", 2017);
    		this.student.dropClass("A", "Test", 2017);
    		assertFalse(this.student.isRegisteredFor("A", "Test", 2017));
    }
    
    @Test
    public void studentSubmittedHwWrong() { //student who is not registered in the class submitted HW
  	  // this.admin.createClass("Test", 2017, "Instructor", 2);
  	   this.student.registerForClass("A", "Test", 2017);
  	   this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
  	   this.student.submitHomework("B", "HW1", "Ans1", "Test", 2017);
  	   assertFalse(this.student.hasSubmitted("B", "HW1", "Test", 2017));
    }
    
    @Test
    public void studentSubmittedHwWithNoAns() { //student submitted HW with no answer
  	   this.admin.createClass("Test", 2017, "Instructor", 2);
  	   this.student.registerForClass("A", "Test", 2017);
  	   this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
  	   this.student.submitHomework("A", "HW1", "", "Test", 2017);
  	   assertFalse(this.student.hasSubmitted("A", "HW1", "Test", 2017));
     	}
    
    @Test
    public void studentSubmittedHwRight() { //student registered in the class submitted HW
  	   this.admin.createClass("Test", 2017, "Instructor", 2);
  	   this.student.registerForClass("A", "Test", 2017);
  	   this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
  	   this.student.submitHomework("A", "HW1", "Ans1", "Test", 2017);
  	   assertTrue(this.student.hasSubmitted("A", "HW1", "Test", 2017));
     	}
    
    @Test
    public void noHWAddedbutSubmitted() { //student submits HW that was not created
  	   this.admin.createClass("Test", 2017, "Instructor", 2);
  	   this.student.registerForClass("A", "Test", 2017);
  	   this.student.submitHomework("B", "HW1", "Ans1", "Test", 2017);
  	   assertFalse(this.student.hasSubmitted("A", "HW1", "Test", 2017));
     	}
    
    @Test
    public void studentSubmittedHwInFuture(){ //student submitted HW for class in future
  	   this.admin.createClass("Test", 2018, "Instructor", 2);
  	   this.student.registerForClass("A", "Test", 2018);
  	   this.instructor.addHomework("Instructor", "Test", 2018, "HW1");
  	   this.student.submitHomework("A", "HW1", "Ans1", "Test", 2018);
  	   assertFalse(this.student.hasSubmitted("A", "HW1", "Test", 2018));
     	}
    
    
    }
