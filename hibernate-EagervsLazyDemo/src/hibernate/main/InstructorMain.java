package hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;

public class InstructorMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//create the objects
		
		Instructor tempInstructor = new Instructor("John", "Doe", "john_doe@gmail.com");
		
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.john_doe.com/youtube", "Love to sing!!");
		
		Instructor tempInstructor2 = new Instructor("Bukola", "Shobade", "bukola_shobade@gmail.com");
		
		InstructorDetail tempInstructorDetail2 = new InstructorDetail("http://www.bukola_shobade.com/youtube", "Love to flex!!");
		
		//associate objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		tempInstructor2.setInstructorDetail(tempInstructorDetail2);
		
		//start a transaction
	
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			session.save(tempInstructor);
			session.save(tempInstructor2);
	//		int theInstructor = 2;
	//		Instructor tempInstructor = session.get(Instructor.class, theInstructor);
	//		
	//		System.out.println("Deleting Instructor: " +tempInstructor);
	//		
	//		if(tempInstructor != null) {
	//			session.delete(tempInstructor);
	//		}
			//commit transaction
			session.getTransaction().commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}

}
