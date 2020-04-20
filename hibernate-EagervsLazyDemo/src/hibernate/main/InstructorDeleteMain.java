package hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;

public class InstructorDeleteMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//create the objects
		
//		Instructor tempInstructor = new Instructor("John", "Doe", "john_doe@gmail.com");
//		
//		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.john_doe.com/youtube", "Love to sing!!");
		
		Instructor tempInstructor2 = new Instructor("Bukola", "Shobade", "bukola_shobade@gmail.com");
		
		InstructorDetail tempInstructorDetail2 = new InstructorDetail("http://www.bukola_shobade.com/youtube", "Love to flex!!");
		
		//asscociate objects
//		tempInstructor.setInstructorDetail(tempInstructorDetail);
		tempInstructor2.setInstructorDetail(tempInstructorDetail2);
		
		//start a transaction
	
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
//		System.out.println("Saving instructor: " +tempInstructor);
//		session.save(tempInstructor);
		
		System.out.println("Saving instructor: " +tempInstructor2);
		session.save(tempInstructor2);
		
		//commit transaction
		session.getTransaction().commit();
	}

}
