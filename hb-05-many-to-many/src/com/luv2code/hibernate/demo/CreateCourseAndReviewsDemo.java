package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//start transaction
			session.beginTransaction();
			
			//create a course
			Course theCourse = new Course("Spring Boot for beginners");
			
			//add some reviews
			theCourse.addReview(new Review("Great course... loved it"));
			theCourse.addReview(new Review("Cool tips!!!"));
			theCourse.addReview(new Review("Why i couldn't achieve that?"));
			theCourse.addReview(new Review("I had success with the advice"));
			
			
			//save the course
			System.out.println("Saving the course...\n" + theCourse);
			System.out.println("Saving the reviews...\n" + theCourse.getReviews());
			session.save(theCourse);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
		
	}

}
