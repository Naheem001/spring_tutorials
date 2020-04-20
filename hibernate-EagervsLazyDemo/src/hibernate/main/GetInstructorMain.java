package hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;

public class GetInstructorMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//start a transaction
	
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			//Get instructor detail
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class,  1);
			System.out.println("The TempInstructorDetail: " +tempInstructorDetail);
			
			//Print Associated instructor
			System.out.println("The Associated Instructor: " +tempInstructorDetail.getInstructor());
			
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
