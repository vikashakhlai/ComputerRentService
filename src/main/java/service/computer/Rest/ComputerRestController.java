package service.computer.Rest;

import service.computer.DTO.NameRequest;
import service.computer.DTO.ComputerRequestNoIdRent;
import service.computer.DTO.ComputerRequestNoRent;
import service.computer.Exception.ControllerException;
import service.computer.Exception.RepositoryException;
import service.computer.Exception.ServiceException;
import service.computer.Models.Computer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.computer.Repository.UserRentFormRepository;
import service.computer.Service.ComputerService;
import service.computer.Service.UserRentFormService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class ComputerRestController {
    @Autowired
    private ComputerService computerService;
    @Autowired
    private UserRentFormService userRentFormService;
    @Autowired
    private UserRentFormRepository userRentFormRepository;

    private static final Logger logger = Logger.getLogger(ComputerRestController.class);

    @PostMapping("/admin/createComputer")
    public ResponseEntity<?> createComputer(@RequestBody ComputerRequestNoIdRent computerRequestNoIdRent) throws ControllerException {
        Computer stuff = new Computer(
                computerRequestNoIdRent.getName(),
                computerRequestNoIdRent.getDescription(),
                computerRequestNoIdRent.getCost(),
                computerRequestNoIdRent.getExpirationDate()
        );
        try {
            computerService.create(stuff);
            return new ResponseEntity<>(stuff, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @DeleteMapping("/admin/deleteComputerByNameA")
    public ResponseEntity<?> deleteComputerByNameA(@RequestBody NameRequest nameRequest) throws ControllerException {
        try {
            Computer man = computerService.getByName(nameRequest.getName());
            computerService.deleteByName(nameRequest.getName());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @PutMapping("/admin/updateComputer")
    public ResponseEntity<?> updateComputer(@RequestBody ComputerRequestNoRent ComputerRequestNoRent)throws ControllerException {
        try {
            Computer man = computerService.getById( ComputerRequestNoRent.getId());
            computerService.updateComputerById(
                    ComputerRequestNoRent.getId(),
                    ComputerRequestNoRent.getName(),
                    ComputerRequestNoRent.getDescription(),
                    ComputerRequestNoRent.getExpirationDate(),
                    ComputerRequestNoRent.getCost()
            );
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
    }
    @DeleteMapping("/user/deleteComputerByNameU")
    public ResponseEntity<?> deleteComputerByNameU(@RequestBody NameRequest nameRequest)throws ControllerException {

        try {
            Computer man = computerService.getByName(nameRequest.getName());
            userRentFormRepository.deleteByUserName(nameRequest.getName());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException | RepositoryException e ) {
            throw new ControllerException(e);

        }


    }
    @GetMapping("/admin/getAllComputersForAdmin")
    public ResponseEntity<?> getAllComputersForAdmin() throws ControllerException{
        try {
            return new ResponseEntity<>(computerService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {

            throw new ControllerException(e);

        }
    }
    @PostMapping("/admin/isComputerExistByName")
    public ResponseEntity<?> isComputerExistByName(@RequestBody NameRequest nameRequest) throws ControllerException{
        try {
            if(!computerService.existsByName(nameRequest.getName())){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FOUND);
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }

    }
    @GetMapping("/user/userGetComputerByName/{name}")
    public ResponseEntity<?> userGetComputerByName(@PathVariable(name = "name")String name)throws ControllerException {
        Computer stuff = null;
        try {
            stuff = computerService.getByName(name);
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @GetMapping("admin/adminGetComputerByName/{name}")
    public ResponseEntity<?> adminGetComputerByName(@PathVariable(name = "name")String name) throws ParseException, ControllerException {
        Computer stuff = null;
        try {
            stuff = computerService.getByName(name);
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String date = simpleDateFormat.format(stuff.getExpirationDate());
            System.out.println(date);
            stuff.setExpirationDate(simpleDateFormat.parse(date));
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("Error occured");
            throw new ControllerException(e);

        }

    }
    @GetMapping("/user/getAllComputersForUser")
    public ResponseEntity<?> getAllComputersForUser()throws ControllerException {
        try {
            return new ResponseEntity<>(computerService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
    }
}
