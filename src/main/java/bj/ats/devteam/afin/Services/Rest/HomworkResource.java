package bj.ats.devteam.afin.Services.Rest;


import bj.ats.devteam.afin.Entity.Homework;
import bj.ats.devteam.afin.Entity.HwQuestion;
import bj.ats.devteam.afin.Repository.HomeworkRepository;
import bj.ats.devteam.afin.Repository.QuestionRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/apiAfin")
@Api(description = "Rest Endpoint for Homeworks ", tags = {"Afin"})
public class HomworkResource {

    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private QuestionRepository questionRepository;
    public static final Logger logger = LoggerFactory.getLogger(CourseResource.class);

    @ApiOperation("create new homework")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/homeworks/", method = RequestMethod.POST)
    public ResponseEntity<?> createHomework(@RequestBody Homework homework) {
        logger.info("Creating homeworks : {}", homework.getId());
        homeworkRepository.save(homework);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @ApiOperation("update homework")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/homeworks/", method = RequestMethod.PUT)
    public ResponseEntity<Homework> updateHomework(@RequestBody Homework homework) {
        logger.info("Creating homework : {}", homework.getId());
        Homework homework1 =   homeworkRepository.saveAndFlush(homework);
        return new ResponseEntity<Homework>(homework1, HttpStatus.OK);
    }

    @ApiOperation("find course by id")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/homeworks/{id}", method = RequestMethod.GET)
    ResponseEntity<Homework> getHomework(@PathVariable Long id){
        Homework homework = homeworkRepository.findOne(id);
        logger.info("Finding Homework {}", homework.getId());
        if(homework==null){

            logger.error("homework with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("homework module with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Homework>(homework, HttpStatus.OK);
    }

    /***
     * recupere tous les cours
     * @return
     */
    @ApiOperation("find all courses")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/homeworks/all", method = RequestMethod.GET)
    public ResponseEntity<List<Homework>> getAllCourses(){
        List<Homework> allhomework = homeworkRepository.findAll();
        if (allhomework.isEmpty()){
            return new ResponseEntity(new CustomErrorType("no course find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(allhomework, HttpStatus.OK);
    }

    /***
     * recuperer les homework par page
     * @return une reponse http contenant les page de cours et le status http de la requete
     */
    @ApiOperation("find all homework by page")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/homeworks", method = RequestMethod.GET)
    public ResponseEntity<Page<Homework>> getCoursesPaginate(int size, int page){
        Page<Homework> homeworkPage = homeworkRepository.findAll(new PageRequest(page,size));

        if (!homeworkPage.hasContent()){
            return new ResponseEntity(new CustomErrorType("no homework find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Homework>>(homeworkPage,HttpStatus.OK);
    }

    /***
     * Ajouter une question a un  homework
     * @param id
     * @param
     * @return
     */
    @RequestMapping(value = "/homeworks/{id}/questions", method = RequestMethod.POST)
    public ResponseEntity<Homework> addQuestion(@PathVariable Long id, @RequestBody HwQuestion hwQuestion){
        Homework homework = homeworkRepository.findOne(id);
        if (homework.getHwQuestions()==null){
            homework.setHwQuestions(new HashSet<>());
        }
        homework.getHwQuestions().add(questionRepository.save(hwQuestion));
        return new ResponseEntity<Homework>(homeworkRepository.saveAndFlush(homework),HttpStatus.CREATED);
    }

}
