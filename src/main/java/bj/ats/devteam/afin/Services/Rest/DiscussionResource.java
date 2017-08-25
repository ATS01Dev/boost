package bj.ats.devteam.afin.Services.Rest;


import bj.ats.devteam.afin.Entity.Discussion;
import bj.ats.devteam.afin.Entity.Homework;
import bj.ats.devteam.afin.Entity.HwQuestion;
import bj.ats.devteam.afin.Repository.DiscussionRepository;
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
@Api(description = "Rest Endpoint for discussion ", tags = {"Afin"})
public class DiscussionResource {

    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private QuestionRepository questionRepository;
    public static final Logger logger = LoggerFactory.getLogger(CourseResource.class);

    @ApiOperation("create new discussion")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/discussions/", method = RequestMethod.POST)
    public ResponseEntity<?> createDiscussion(@RequestBody Discussion discussion) {
        logger.info("Creating discussion : ");
        discussionRepository.save(discussion);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @ApiOperation("update discussion")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/discussions/", method = RequestMethod.PUT)
    public ResponseEntity<Discussion> updateDiscussion(@RequestBody Discussion discussion) {
        logger.info("updating discussion : {}", discussion.getId());
        Discussion discussion1 =   discussionRepository.saveAndFlush(discussion);
        return new ResponseEntity<Discussion>(discussion1, HttpStatus.OK);
    }

    @ApiOperation("find discussion by id")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/discussions/{id}", method = RequestMethod.GET)
    ResponseEntity<Discussion> getDiscussion(@PathVariable Long id){
        Discussion discussion = discussionRepository.findOne(id);
        logger.info("Finding Discussion {}", discussion.getId());
        if(discussion==null){

            logger.error("discussion with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("discussion module with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Discussion>(discussion, HttpStatus.OK);
    }

    /***
     * recupere tous les cours
     * @return
     */
    @ApiOperation("find all discussion")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/discussions/all", method = RequestMethod.GET)
    public ResponseEntity<List<Discussion>> getAllDiscussions(){
        List<Discussion> allDiscussions = discussionRepository.findAll();
        if (allDiscussions.isEmpty()){
            return new ResponseEntity(new CustomErrorType("no course find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(allDiscussions, HttpStatus.OK);
    }

    /***
     * recuperer les homework par page
     * @return une reponse http contenant les page de cours et le status http de la requete
     */
    @ApiOperation("find all discussion by page")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/discussions", method = RequestMethod.GET)
    public ResponseEntity<Page<Discussion>> getDiscussionPaginate(int size, int page){
        Page<Discussion> discussionPage = discussionRepository.findAll(new PageRequest(page,size));

        if (!discussionPage.hasContent()){
            return new ResponseEntity(new CustomErrorType("no homework find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Discussion>>(discussionPage,HttpStatus.OK);
    }

    /***
     * Ajouter une solution a une  discussion
     * @param id
     * @param
     * @return

    @RequestMapping(value = "/Discussions/{id}/questions", method = RequestMethod.POST)
    public ResponseEntity<Homework> addQuestion(@PathVariable Long id, @RequestBody HwQuestion hwQuestion){
        Homework homework = discussionRepository.findOne(id);
        if (homework.getHwQuestions()==null){
            homework.setHwQuestions(new HashSet<>());
        }
        homework.getHwQuestions().add(questionRepository.save(hwQuestion));
        return new ResponseEntity<Homework>(discussionRepository.saveAndFlush(homework),HttpStatus.CREATED);
    }
     */
}
