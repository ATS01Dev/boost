package bj.ats.devteam.afin.Services.Rest;


import bj.ats.devteam.afin.Entity.HwQuestion;
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
@Api(description = "Rest Endpoint for Homework questions ", tags = {"Afin Materials"})
public class QuestionResources {
    @Autowired
    private QuestionRepository questionRepository;
    public static final Logger logger = LoggerFactory.getLogger(ModuleMaterialResource.class);

    /***
     * @Comment rest resource to create module
     * @param question
     * @return
     */
    @ApiOperation("create new question")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/questions/", method = RequestMethod.POST)
    public ResponseEntity<?> createModule(@RequestBody HwQuestion question) {
        logger.info("Creating question : {}", question.getId());
        questionRepository.save(question);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @ApiOperation("update question")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/questions/", method = RequestMethod.PUT)
    public ResponseEntity<HwQuestion> updateQuestions(@RequestBody HwQuestion hwQuestion) {
        logger.info("Creating questions : {}", hwQuestion.getId());
        HwQuestion hwQuestion1 =   questionRepository.saveAndFlush(hwQuestion);
        return new ResponseEntity<HwQuestion>(hwQuestion1, HttpStatus.OK);
    }

    @ApiOperation("find  questions by id")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET)
    ResponseEntity<HwQuestion> getHomeworkQuestion(@PathVariable Long id){
        HwQuestion hwQuestion = questionRepository.findOne(id);
        logger.info("Finding Course Module {}", hwQuestion.getId());
        if(hwQuestion==null){

            logger.error("Module with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("homework with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<HwQuestion>(hwQuestion, HttpStatus.OK);
    }


    @ApiOperation("find all questions")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/questions/all", method = RequestMethod.GET)
    public ResponseEntity<List<HwQuestion>> getAllQuestions(){
        List<HwQuestion> hwQuestions = questionRepository.findAll();

        if (hwQuestions.isEmpty()){
            return new ResponseEntity(new CustomErrorType("no module find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(hwQuestions, HttpStatus.OK);
    }

    @ApiOperation("find all questions by page")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public  ResponseEntity<Page<HwQuestion>> getMaterialsPage(int page, int size){
        Page<HwQuestion> questionPage = questionRepository.findAll(new PageRequest(page,size));
        if (!questionPage.hasContent()){
            return new ResponseEntity(new CustomErrorType("No module find"),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<HwQuestion>>(questionPage, HttpStatus.OK);
    }
}
