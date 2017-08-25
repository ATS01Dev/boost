package bj.ats.devteam.afin.Services.Rest;

import bj.ats.devteam.afin.Entity.ModuleMaterial;
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

import java.util.List;


@RestController
@RequestMapping("/apiAfin")
@Api(description = "Rest Endpoint for Modules Materials", tags = {"Afin Materials"})
public class ModuleMaterialResource {

    @Autowired
    private MaterialRepository materialRepository;
    public static final Logger logger = LoggerFactory.getLogger(ModuleMaterialResource.class);

    /***
     * @Comment rest resource to create module
     * @param materials
     * @return
     */
    @ApiOperation("create new materials")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/materials/", method = RequestMethod.POST)
    public ResponseEntity<?> createModule(@RequestBody ModuleMaterial materials) {
            logger.info("Creating materials : {}", materials.getType());
        materialRepository.save(materials);
              return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @ApiOperation("update modules")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/materials/", method = RequestMethod.PUT)
    public ResponseEntity<ModuleMaterial> updateModule(@RequestBody ModuleMaterial moduleMaterial1) {
        logger.info("Creating materials : {}", moduleMaterial1.getType());
        ModuleMaterial moduleMaterial =   materialRepository.saveAndFlush(moduleMaterial1);
        return new ResponseEntity<ModuleMaterial>(moduleMaterial, HttpStatus.OK);
    }

    @ApiOperation("find course materials by id")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/materials/{id}", method = RequestMethod.GET)
    ResponseEntity<ModuleMaterial> getCourseMaterials(@PathVariable Long id){
        ModuleMaterial moduleMaterial = materialRepository.findOne(id);
        logger.info("Finding Course Module {}", moduleMaterial.getId());
        if(moduleMaterial==null){

                logger.error("Module with id {} not found.", id);
                return new ResponseEntity(new CustomErrorType("course module with id " + id
                        + " not found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<ModuleMaterial>(moduleMaterial, HttpStatus.OK);
        }


    @ApiOperation("find all modules")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/materials/all", method = RequestMethod.GET)
        public ResponseEntity<List<ModuleMaterial>> getAllMaterials(){
            List<ModuleMaterial> allMaterials = materialRepository.findAll();

            if (allMaterials.isEmpty()){
                return new ResponseEntity(new CustomErrorType("no module find"), HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity(allMaterials, HttpStatus.OK);
    }

    @ApiOperation("find all courses by page")
    @ApiResponses(value = @ApiResponse(code =400, message = "invalid input" ))
    @RequestMapping(value = "/materials", method = RequestMethod.GET)
    public  ResponseEntity<Page<ModuleMaterial>> getMaterialsPage(int page, int size){
            Page<ModuleMaterial> materialPage = materialRepository.findAll(new PageRequest(page,size));
            if (!materialPage.hasContent()){
                return new ResponseEntity(new CustomErrorType("No module find"),HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<Page<ModuleMaterial>>(materialPage, HttpStatus.OK);
    }
    }

