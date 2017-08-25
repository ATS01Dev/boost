package bj.ats.devteam.afin.Services.Rest;

import bj.ats.devteam.afin.Entity.Solutions;
import bj.ats.devteam.afin.Repository.CourseRepository;
import bj.ats.devteam.afin.Repository.SolutionRepository;
import bj.ats.devteam.afin.utils.CustomErrorType;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.ZonedDateTime;
import java.util.List;

public class SolutionResources {

    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    CourseRepository courseRepository;
    public static final Logger logger = LoggerFactory.getLogger(TeacherResource.class);

    @ApiOperation("create new solution")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/solutions/", method = RequestMethod.POST)
    public ResponseEntity<?> createSolution(@RequestBody Solutions solutions) {
        solutions.setRegistering(ZonedDateTime.now());
        logger.info("Creating new solutions ");
        solutionRepository.save(solutions);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @ApiOperation("update solutions")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/solutions/", method = RequestMethod.PUT)
    public ResponseEntity<Solutions> updateSolution(@RequestBody Solutions solutions) {
        logger.info("updating solutions : {}", solutions.getId());
        Solutions solutions1 =   solutionRepository.saveAndFlush(solutions);
        return new ResponseEntity<Solutions>(solutions1, HttpStatus.OK);
    }

    @ApiOperation("find solutions by id")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/solutions/{id}", method = RequestMethod.GET)
    ResponseEntity<Solutions> getTecher(@PathVariable Long id){
        Solutions solutions = solutionRepository.findOne(id);
        logger.info("Finding solutions  {}", solutions.getId());
        if(solutions==null){

            logger.error("solutions with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("teachers with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Solutions>(solutions, HttpStatus.OK);
    }

    /***
     * recupere tous les formateurs
     * @return
     */
    @ApiOperation("find all solutions")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/solutions/all", method = RequestMethod.GET)
    public ResponseEntity<List<Solutions>> getAllSolutions(){
        List<Solutions> allSolutions = solutionRepository.findAll();
        if (allSolutions.isEmpty()){
            return new ResponseEntity(new CustomErrorType("no solutions find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(allSolutions, HttpStatus.OK);
    }

    /***
     * recuperer les cours par page
     * @return une reponse http contenant les page de cours et le status http de la requete
     */
    @ApiOperation("find all solutions by page")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public ResponseEntity<Page<Solutions>> getSolutionsPaginate(int size, int page){
        Page<Solutions> teachersPage = solutionRepository.findAll(new PageRequest(page,size, Sort.Direction.ASC, "registering"));

        if (!teachersPage.hasContent()){
            return new ResponseEntity(new CustomErrorType("no teacher find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Solutions>>(teachersPage,HttpStatus.OK);
    }



}
