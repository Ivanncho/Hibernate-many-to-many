package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForErikDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//start transaction
			session.beginTransaction();
			
			//get the student erik form database
			int theId=3;
			Student studentErik = session.get(Student.class, theId);
			System.out.println("\nLoaded the student: " + studentErik);
			System.out.println("\nErik's courses: " + studentErik.getCourses());
			
			//create more courses
			Course theCourse = new Course("Rubik's Cube - How to speed Cube");
			System.out.println("\nAdding Course: " + theCourse);
			//add student to courses
			theCourse.addStudent(studentErik);
			System.out.println("\nAdding student to course...");
			//save the courses
			session.save(theCourse);
			System.out.println("\nSaving the course with the student");
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
		
	}

}
