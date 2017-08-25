package bj.ats.devteam.afin.Services.Rest;

import bj.ats.devteam.afin.Entity.CourseModule;
import bj.ats.devteam.afin.Entity.ModuleMaterial;
import bj.ats.devteam.afin.Repository.CourseModuleRepository;
import bj.ats.devteam.afin.Repository.MaterialRepository;
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
import java.util.Set;


@RestController
@RequestMapping("/apiAfin")
@Api(description = "Rest Endpoint for Courses Modules", tags = {"Afin"})
public class CourseModuleResource {

    @Autowired
    private CourseModuleRepository courseModuleRepository;
    private MaterialRepository materialRepository;
    public static final Logger logger = LoggerFactory.getLogger(CourseModuleResource.class);

    /***
     * @Comment rest resource to create module
     * @param courseModule
     * @return
     */
    @ApiOperation("create new modules")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/modules/", method = RequestMethod.POST)
    public ResponseEntity<?> createModule(@RequestBody CourseModule courseModule) {
            logger.info("Creating module : {}", courseModule.getTitle());
            courseModuleRepository.save(courseModule);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }


    @ApiOperation("update modules")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/modules/", method = RequestMethod.PUT)
    public ResponseEntity<CourseModule> updateModule(@RequestBody CourseModule courseModule) {
        logger.info("Creating module : {}", courseModule.getTitle());
        CourseModule courseModule1 =   courseModuleRepository.saveAndFlush(courseModule);
        return new ResponseEntity<CourseModule>(courseModule1, HttpStatus.OK);
    }

    @ApiOperation("find course module by id")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/modules/{id}", method = RequestMethod.GET)
    ResponseEntity<CourseModule> getCourseModule(@PathVariable Long id){
        CourseModule courseModule = courseModuleRepository.findOne(id);
        logger.info("Finding Course Module {}", courseModule.getId());
        if(courseModule==null){

                logger.error("Module with id {} not found.", id);
                return new ResponseEntity(new CustomErrorType("course module with id " + id
                        + " not found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<CourseModule>(courseModule, HttpStatus.OK);
        }


    @ApiOperation("find all modules")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/modules/all", method = RequestMethod.GET)
        public ResponseEntity<List<CourseModule>> getAllModules(){
            List<CourseModule> allModules = courseModuleRepository.findAll();

            if (allModules.isEmpty()){
                return new ResponseEntity(new CustomErrorType("no module find"), HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity(allModules, HttpStatus.OK);
    }

    @ApiOperation("find all courses by page")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/modules", method = RequestMethod.GET)
    public  ResponseEntity<Page<CourseModule>> getModulesPage(int page, int size){
            Page<CourseModule> modulePage = courseModuleRepository.findAll(new PageRequest(page,size));
            if (!modulePage.hasContent()){
                return new ResponseEntity(new CustomErrorType("No module find"),HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<Page<CourseModule>>(modulePage, HttpStatus.OK);
    }

    /***
     * @Comment rest resource to create module
     * @param moduleMaterial
     * @return
     */
    @ApiOperation("create new modules")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/modules/{id}/addmaterials", method = RequestMethod.POST)
    public ResponseEntity<?> createModule(@PathVariable Long id, @RequestBody ModuleMaterial moduleMaterial) {
        logger.info("Creating module : {}", moduleMaterial.getTitle());
        CourseModule courseModule = courseModuleRepository.findOne(id);
        if (courseModule.getModuleMaterials()==null)
        {
            Set<ModuleMaterial> materials = new HashSet<>();
            courseModule.setModuleMaterials(materials);
        }
        courseModule.getModuleMaterials().add(materialRepository.save(moduleMaterial));
        courseModuleRepository.saveAndFlush(courseModule);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    }

