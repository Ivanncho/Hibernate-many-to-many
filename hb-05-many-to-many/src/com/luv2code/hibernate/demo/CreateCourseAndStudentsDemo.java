package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

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
			
			//create a course
			Course theCourse = new Course("How to be a millionaire");
			Course theCourse1 = new Course("1000 steps to success");
			Course theCourse2 = new Course("Motivation is the way");
			
			
			//save the course
			System.out.println("\nSaving the course...");
			session.save(theCourse);
			session.save(theCourse1);
			session.save(theCourse2);
			System.out.println("Saved course: " + theCourse);
			
			//create the student
			Student student1 = new Student("John","Doe","john.doe@email.com");
			Student student2 = new Student("Jane","Doe","jane.doe@email.com");
			Student student3 = new Student("Erik","Donalds","eDonalds@email.com");
			//add students to the course
			System.out.println("\nAdding student to courses");
			theCourse.addStudent(student1);
			theCourse1.addStudent(student2);
			theCourse2.addStudent(student3);
			theCourse.addStudent(student2);
			theCourse2.addStudent(student1);
			//save the students
			System.out.println("\nSaving the students:" + "\n" + student1 + "\n" + student2 + "\n" + student3);
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
		
	}

}
