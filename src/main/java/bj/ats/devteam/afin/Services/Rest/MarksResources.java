package bj.ats.devteam.afin.Services.Rest;


import bj.ats.devteam.afin.Entity.Marks;
import bj.ats.devteam.afin.Repository.MarksRepository;
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


import java.util.List;


@RestController
@RequestMapping("/apiAfin")
@Api(description = "Rest Endpoint for Marks ", tags = {"Afin"})
public class MarksResources  {
    @Autowired
    private MarksRepository marksRepository;
    @Autowired
    private QuestionRepository questionRepository;
    public static final Logger logger = LoggerFactory.getLogger(CourseResource.class);

    @ApiOperation("create new marks")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/marks/", method = RequestMethod.POST)
    public ResponseEntity<?> createMarks(@RequestBody Marks marks) {
        logger.info("Creating marks : {}", marks.getId());
        marksRepository.save(marks);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @ApiOperation("update marks")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/marks/", method = RequestMethod.PUT)
    public ResponseEntity<Marks> updateMarks(@RequestBody Marks marks) {
        logger.info("Creating marks : {}", marks.getId());
        Marks marks1 =   marksRepository.saveAndFlush(marks);
        return new ResponseEntity<Marks>(marks1, HttpStatus.OK);
    }

    @ApiOperation("find course by id")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/marks/{id}", method = RequestMethod.GET)
    ResponseEntity<Marks> getMarks(@PathVariable Long id){
        Marks marks = marksRepository.findOne(id);
        logger.info("Finding marks {}", marks.getId());
        if(marks==null){

            logger.error("marks with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("marks module with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Marks>(marks, HttpStatus.OK);
    }

    /***
     * recupere tous les cours
     * @return
     */
    @ApiOperation("find all marks")
    @ApiResponses(value = @ApiResponse(code = 400 , message = "invalid input" ))
    @RequestMapping(value = "/marks/all", method = RequestMethod.GET)
    public ResponseEntity<List<Marks>> getAllMarks(){
        List<Marks> marks = marksRepository.findAll();
        if (marks.isEmpty()){
            return new ResponseEntity(new CustomErrorType("no marks find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(marks, HttpStatus.OK);
    }

    /***
     * recuperer les homework par page
     * @return une reponse http contenant les page de cours et le status http de la requete
     */
    @ApiOperation("find all marks by page")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/marks", method = RequestMethod.GET)
    public ResponseEntity<Page<Marks>> getCoursesPaginate(int size, int page){
        Page<Marks> marksPage = marksRepository.findAll(new PageRequest(page,size));

        if (!marksPage.hasContent()){
            return new ResponseEntity(new CustomErrorType("no homework find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Marks>>(marksPage,HttpStatus.OK);
    }

}
