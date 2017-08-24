package bj.ats.devteam.afin.Services.Rest;

import bj.ats.devteam.afin.Entity.Course;
import bj.ats.devteam.afin.Entity.CourseModule;
import bj.ats.devteam.afin.Entity.Teacher;
import bj.ats.devteam.afin.Repository.CourseModuleRepository;
import bj.ats.devteam.afin.Repository.CourseRepository;
import bj.ats.devteam.afin.Repository.TeacherRepository;
import bj.ats.devteam.afin.utils.CustomErrorType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/apiAfin")
@Api(description = "Rest Endpoint for Courses ", tags = {"Afin"})
public class TeacherResource {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    CourseRepository courseRepository;
    public static final Logger logger = LoggerFactory.getLogger(TeacherResource.class);

    @ApiOperation("create new teachers")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/teachers/", method = RequestMethod.POST)
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
            teacher.setRegistring(ZonedDateTime.now());
            logger.info("Creating teachers : {} {}", teacher.getName(),teacher.getSurnames());
            teacherRepository.save(teacher);
              return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @ApiOperation("update teachers")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/teachers/", method = RequestMethod.PUT)
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher) {
        logger.info("Creating teachers : {}", teacher.getName());
        Teacher teacher1 =   teacherRepository.saveAndFlush(teacher);
        return new ResponseEntity<Teacher>(teacher1, HttpStatus.OK);
    }

    @ApiOperation("find teachers by id")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.GET)
    ResponseEntity<Teacher> getTecher(@PathVariable Long id){
        Teacher teacher = teacherRepository.findOne(id);
        logger.info("Finding teachers  {}", teacher.getId());
        if(teacher==null){

                logger.error("teachers with id {} not found.", id);
                return new ResponseEntity(new CustomErrorType("teachers with id " + id
                        + " not found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
        }

    /***
     * recupere tous les formateurs
     * @return
     */
    @ApiOperation("find all teachers")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/teachers/all", method = RequestMethod.GET)
        public ResponseEntity<List<Teacher>> getAllTeachers(){
            List<Teacher> allTechers = teacherRepository.findAll();
            if (allTechers.isEmpty()){
                return new ResponseEntity(new CustomErrorType("no techears find"), HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity(allTechers, HttpStatus.OK);
    }

    /***
     * recuperer les cours par page
     * @return une reponse http contenant les page de cours et le status http de la requete
     */
    @ApiOperation("find all teachers by page")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public ResponseEntity<Page<Teacher>> getTeacherPaginate(int size, int page){
        Page<Teacher> teachersPage = teacherRepository.findAll(new PageRequest(page,size, Sort.Direction.ASC, "registring"));

        if (!teachersPage.hasContent()){
            return new ResponseEntity(new CustomErrorType("no teacher find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Teacher>>(teachersPage,HttpStatus.OK);
    }

    @RequestMapping(value = "/teachers/{id}/course", method = RequestMethod.GET)
    public ResponseEntity<Set<Course>> getTeachersCourses(@PathVariable Long id){
        List<Course> courses = courseRepository.findAll();
        Set<Course> techersCours = new HashSet<>();

        for (Course cours : courses ){
            for (Teacher teacher : cours.getTeachers()){
                if (teacher.getId()==id)
                    techersCours.add(cours);
            }
        }
        if (techersCours.isEmpty()){
            return new ResponseEntity(new CustomErrorType("no course find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Course>>(techersCours,HttpStatus.OK);
    }

    }

