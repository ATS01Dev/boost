package bj.ats.devteam.afin.Services.Rest;

import bj.ats.devteam.afin.Entity.Course;
import bj.ats.devteam.afin.Entity.CourseModule;
import bj.ats.devteam.afin.Repository.CourseModuleRepository;
import bj.ats.devteam.afin.utils.CustomErrorType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import bj.ats.devteam.afin.Repository.CourseRepository;
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


@RestController
@RequestMapping("/apiAfin")
@Api(description = "Rest Endpoint for Courses ", tags = {"Afin"})
public class CourseResource {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseModuleRepository courseModuleRepository;
    public static final Logger logger = LoggerFactory.getLogger(CourseResource.class);

    @ApiOperation("create new course")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/courses/", method = RequestMethod.POST)
    public ResponseEntity<?> createModule(@RequestBody Course course) {
            course.setRegistring(ZonedDateTime.now());
            logger.info("Creating course : {}", course.getTitle());
            courseRepository.save(course);
              return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @ApiOperation("update course")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/courses/", method = RequestMethod.PUT)
    public ResponseEntity<Course> updateModule(@RequestBody Course course) {
        logger.info("Creating course : {}", course.getTitle());
        Course course1 =   courseRepository.saveAndFlush(course);
        return new ResponseEntity<Course>(course1, HttpStatus.OK);
    }

    @ApiOperation("find course by id")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    ResponseEntity<Course> getCourse(@PathVariable Long id){
        Course course = courseRepository.findOne(id);
        logger.info("Finding Course Module {}", course.getId());
        if(course==null){

                logger.error("courses with id {} not found.", id);
                return new ResponseEntity(new CustomErrorType("course module with id " + id
                        + " not found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        }

    /***
     * recupere tous les cours
     * @return
     */
    @ApiOperation("find all courses")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/courses/all", method = RequestMethod.GET)
        public ResponseEntity<List<Course>> getAllCourses(){
            List<Course> allCoures = courseRepository.findAll();
            if (allCoures.isEmpty()){
                return new ResponseEntity(new CustomErrorType("no course find"), HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity(allCoures, HttpStatus.OK);
    }

    /***
     * recuperer les cours par page
     * @return une reponse http contenant les page de cours et le status http de la requete
     */
    @ApiOperation("find all courses by page")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<Page<Course>> getCoursesPaginate(int size, int page){
        Page<Course> coursePage = courseRepository.findAll(new PageRequest(page,size, Sort.Direction.ASC, "registring"));

        if (!coursePage.hasContent()){
            return new ResponseEntity(new CustomErrorType("no course find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Course>>(coursePage,HttpStatus.OK);
    }

    /***
     * Ajouter un module a un cours
     * @param id
     * @param courseModule
     * @return
     */
    @ApiOperation("add module to course the id param is for course")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/courses/{id}/module", method = RequestMethod.POST)
    public ResponseEntity<Course> addModule(@PathVariable Long id, @RequestBody CourseModule courseModule){
            Course course = courseRepository.findOne(id);
            if (course.getCourseModules()==null){
                course.setCourseModules(new HashSet<>());
            }
            course.getCourseModules().add(courseModuleRepository.save(courseModule));
            return new ResponseEntity<Course>(courseRepository.saveAndFlush(course),HttpStatus.CREATED);
    }


    }

