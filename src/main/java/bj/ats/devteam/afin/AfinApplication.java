package bj.ats.devteam.afin;

import bj.ats.devteam.afin.Entity.CourseModule;
import bj.ats.devteam.afin.Repository.CourseModuleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AfinApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(AfinApplication.class, args);
		CourseModuleRepository courseModuleRepository = ctx.getBean(CourseModuleRepository.class);
		int i=0;
		for (i=0;i<15;i++){
			courseModuleRepository.save(new CourseModule("module "+i,"module sommary "+i));
		}
	}
}
