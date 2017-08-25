package bj.ats.devteam.afin.Services.Rest;

import bj.ats.devteam.afin.Entity.Course;
import bj.ats.devteam.afin.Entity.Student;
import bj.ats.devteam.afin.Repository.CourseRepository;
import bj.ats.devteam.afin.Repository.StudentRepository;
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
public class StudentResource {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    public static final Logger logger = LoggerFactory.getLogger(StudentResource.class);

    @ApiOperation("create new Student")
    @ApiResponses(value = @ApiResponse(code = 400, message = "invalid input"))
    @RequestMapping(value = "/students/", method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        student.setRegistring(ZonedDateTime.now());
        logger.info("Creating students : {} {}", student.getName(), student.getSurnames());
        studentRepository.save(student);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @ApiOperation("update students")
    @ApiResponses(value = @ApiResponse(code = 400, message = "invalid input"))
    @RequestMapping(value = "/students/", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        logger.info("Creating student : {}", student.getName());
        Student teacher1 = studentRepository.saveAndFlush(student);
        return new ResponseEntity<Student>(teacher1, HttpStatus.OK);
    }

    @ApiOperation("find student by id")
    @ApiResponses(value = @ApiResponse(code = 400, message = "invalid input"))
    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentRepository.findOne(id);
        logger.info("Finding students  {}", student.getId());
        if (student == null) {

            logger.error("students with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("teachers with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    /***
     * recupere tous les formateurs
     * @return
     */
    @ApiOperation("find all students")
    @ApiResponses(value = @ApiResponse(code = 400, message = "invalid input"))
    @RequestMapping(value = "/students/all", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> allTechers = studentRepository.findAll();
        if (allTechers.isEmpty()) {
            return new ResponseEntity(new CustomErrorType("no techears find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(allTechers, HttpStatus.OK);
    }

    /***
     * recuperer les cours par page
     * @return une reponse http contenant les page de students et le status http de la requete
     */
    @ApiOperation("find all students by page")
    @ApiResponses(value = @ApiResponse(code = 400, message = "invalid input"))
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<Page<Student>> getStudentPaginate(int size, int page) {
        Page<Student> studentPage = studentRepository.findAll(new PageRequest(page, size, Sort.Direction.ASC, "registring"));

        if (!studentPage.hasContent()) {
            return new ResponseEntity(new CustomErrorType("no student find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentPage, HttpStatus.OK);
    }

    @RequestMapping(value = "/students/addcourse", method = RequestMethod.PUT)
    public ResponseEntity<?> addStudentCourses(Long s_id, Long c_id) {
            Student student = studentRepository.findOne(s_id);
            Course course =  courseRepository.findOne(c_id);

            if (student.getCourses()==null) {
                Set<Course> stcouset = new HashSet<>();
                student.setCourses(stcouset);
            }
            student.getCourses().add(course);
            studentRepository.saveAndFlush(student);
        return new ResponseEntity<Student>(HttpStatus.OK);

    }

}