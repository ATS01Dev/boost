package bj.ats.devteam.afin.Services.Rest;

import bj.ats.devteam.afin.Entity.Course;
import bj.ats.devteam.afin.Entity.Information;
import bj.ats.devteam.afin.Entity.Teacher;
import bj.ats.devteam.afin.Repository.CourseRepository;
import bj.ats.devteam.afin.Repository.InformationRepository;
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
import java.util.List;


@RestController
@RequestMapping("/apiAfin")
@Api(description = "Rest Endpoint for Courses ", tags = {"Afin"})
public class InformationResource {

    @Autowired
    private InformationRepository informationRepository;
    @Autowired
    CourseRepository courseRepository;
    public static final Logger logger = LoggerFactory.getLogger(InformationResource.class);

    @ApiOperation("create new information")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/informations/", method = RequestMethod.POST)
    public ResponseEntity<?> createInformation(@RequestBody Information information) {
            information.setDate(ZonedDateTime.now());
            logger.info("Creating information : {} {}");
            informationRepository.save(information);
              return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @ApiOperation("update information")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/informations/", method = RequestMethod.PUT)
    public ResponseEntity<Information> updateInformation(@RequestBody Information information) {
        logger.info("Creating informations : {}", information.getId());
        Information information1 =   informationRepository.saveAndFlush(information);
        return new ResponseEntity<Information>(information1, HttpStatus.OK);
    }

    @ApiOperation("find Information by id")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/informations/{id}", method = RequestMethod.GET)
    ResponseEntity<Information> getInformations(@PathVariable Long id){
        Information information = informationRepository.findOne(id);
        logger.info("Finding teachers  {}", information.getId());
        if(information==null){

                logger.error("teachers with id {} not found.", id);
                return new ResponseEntity(new CustomErrorType("teachers with id " + id
                        + " not found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Information>(information, HttpStatus.OK);
        }

    /***
     * recupere tous les formateurs
     * @return
     */
    @ApiOperation("find all information")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/informations/all", method = RequestMethod.GET)
        public ResponseEntity<List<Information>> getAllInformations(){
            List<Information> informationList = informationRepository.findAll();
            if (informationList.isEmpty()){
                return new ResponseEntity(new CustomErrorType("no techears find"), HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity(informationList, HttpStatus.OK);
    }

    /***
     * recuperer les cours par page
     * @return une reponse http contenant les page de cours et le status http de la requete
     */
    @ApiOperation("find all Information by page")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/informations", method = RequestMethod.GET)
    public ResponseEntity<Page<Information>> getTeacherPaginate(int size, int page){
        Page<Information> informationPage = informationRepository.findAll(new PageRequest(page,size));

        if (!informationPage.hasContent()){
            return new ResponseEntity(new CustomErrorType("no teacher find"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Information>>(informationPage,HttpStatus.OK);
    }

    }

