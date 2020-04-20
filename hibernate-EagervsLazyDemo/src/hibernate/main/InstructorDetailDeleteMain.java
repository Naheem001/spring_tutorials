package hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;

public class InstructorDetailDeleteMain {

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
			
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, 4);
			
			//Set instructorDetail to Null in Instructor
			instructorDetail.getInstructor().setInstructorDetail(null);
			
			System.out.println("Deleting Instructor Detail while not deleting instructor: " +instructorDetail);
//			System.out.println("Deleting Instructor attached to InstructorDetail: " +instructorDetail.getInstructor());
			
			session.delete(instructorDetail);
			
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
