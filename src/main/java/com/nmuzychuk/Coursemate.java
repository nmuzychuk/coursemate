package com.nmuzychuk;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class Coursemate {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
        List<Course> courses = Arrays.asList(
                new Course("Java", "Java course"),
                new Course("SQL", "SQL course"),
                new Course("JavaScript", "JavaScript course")
        );

        List<Lesson> javaLessons = Arrays.asList(
                new Lesson("Java Overview", "Regular lesson"),
                new BonusLesson("Java Tips and Tricks", "Bonus lesson", "Tip and Trick")
        );

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (String name : new String[]{"bob", "kate", "mike"}) {
            session.save(new User(name + "@example.com", name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()));
        }

        for (Course course : courses) {
            session.save(course);
        }
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        User bob = session.get(User.class, 1);
        User kate = session.get(User.class, 2);
        Course javaCourse = session.get(Course.class, 1);
        javaCourse.addUser(bob);
        javaCourse.addUser(kate);
        for (Lesson lesson : javaLessons) {
            javaCourse.addLesson(lesson);
        }
        Course sqlCourse = session.get(Course.class, 2);
        sqlCourse.addLesson(new Lesson("SQL for Beginners", "SQL introduction"));
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        List objects = session.createQuery("from Course").list();
        System.out.println(objects);
        session.getTransaction().commit();
        session.close();
    }
}
