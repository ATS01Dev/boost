package bj.ats.devteam.afin;

import bj.ats.devteam.afin.Entity.Course;
import bj.ats.devteam.afin.Entity.CourseModule;
import bj.ats.devteam.afin.Entity.Student;
import bj.ats.devteam.afin.Entity.Teacher;
import bj.ats.devteam.afin.Repository.CourseModuleRepository;
import bj.ats.devteam.afin.Repository.CourseRepository;
import bj.ats.devteam.afin.Repository.StudentRepository;
import bj.ats.devteam.afin.Repository.TeacherRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AfinApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(AfinApplication.class, args);
		CourseModuleRepository courseModuleRepository = ctx.getBean(CourseModuleRepository.class);
		CourseRepository courseRepository = ctx.getBean(CourseRepository.class);
		TeacherRepository teacherRepository = ctx.getBean(TeacherRepository.class);
		StudentRepository studentRepository= ctx.getBean(StudentRepository.class);

		int i=0;
		for (i=0;i<55;i++){
			Course course = new Course("Domain "+i,"Titre "+i);
			Student student = new Student("Nom de l'Etudiant "+i, "Prénom de l'Etudiant "+i);
			Set<Course> stcoust = new HashSet<>();

			course.setRegistring(ZonedDateTime.now());
			Set<CourseModule> cmset= new HashSet<>();
			Set<Teacher> ctset = new HashSet<>();

			for (int j=0;j<5;j++){
				CourseModule cm = courseModuleRepository.save(new CourseModule("module "+i,"module sommary "+i));
				cmset.add(cm);
				Teacher tc = teacherRepository.save(new Teacher("teacher name "+j,"techer surnames "+j));
				ctset.add(tc);
			}
			course.setCourseModules(cmset);
			course.setTeachers(ctset);
			Course cs =courseRepository.save(course);
			stcoust.add(cs);
			//student.setCourses(stcoust);
			studentRepository.save(student);
				}
	}
}
