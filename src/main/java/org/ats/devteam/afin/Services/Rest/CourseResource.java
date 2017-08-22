package org.ats.devteam.afin.Services.Rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ats.devteam.afin.Entity.Course;
import org.ats.devteam.afin.Repository.CourseRepository;
import org.ats.devteam.afin.utils.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/apiAfin")
@Api(description = "Rest Endpoint for Courses ", tags = {"Afin"})
public class CourseResource {

    @Autowired
    private CourseRepository courseRepository;
    public static final Logger logger = LoggerFactory.getLogger(CourseResource.class);

    @ApiOperation("create new course")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/courses/", method = RequestMethod.POST)
    public ResponseEntity<?> createModule(@RequestBody Course course) {
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


    @ApiOperation("find all courses")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/courses/all", method = RequestMethod.GET)
        public ResponseEntity<List<Course>> getAllCourses(){
            List<Course> allCoures = courseRepository.findAll();
            if (allCoures.isEmpty()){
                return new ResponseEntity(new CustomErrorType("no module find"), HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity(allCoures, HttpStatus.OK);
    }

    }

