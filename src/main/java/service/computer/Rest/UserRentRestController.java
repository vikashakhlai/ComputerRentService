package service.computer.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.computer.DTO.DateRequest;
import service.computer.DTO.IdRentRequest;
import service.computer.DTO.IdRequest;
import service.computer.DTO.UserRentRequestNoId;
import service.computer.Exception.ControllerException;
import service.computer.Exception.RepositoryException;
import service.computer.Models.RentForm;
import service.computer.Repository.UserRentFormRepository;
import service.computer.Service.ComputerService;
import service.computer.Service.UserRentFormService;
import service.computer.Validator.RentValidator;
import service.computer.Exception.ServiceException;

@RestController
public class UserRentRestController {
    @Autowired
    private UserRentFormService userRentFormService;
    @Autowired
    private UserRentFormRepository rentFormRepository;
    @Autowired
    private ComputerService ComputerService;
    @Autowired
    private RentValidator rentValidator;

    @PostMapping("/admin/getAllByComputerExpirationDateLessThan")
    public ResponseEntity<?> getAllByComputerExpirationDateLessThan(@RequestBody DateRequest dateRequest) throws ControllerException {
        try {
            return new ResponseEntity<>(userRentFormService.getAllByComputerExpirationDateLessThan(dateRequest.getDate()), HttpStatus.FOUND);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

    }
    @DeleteMapping("/admin/deleteByUserIdAndComputerId")
    public ResponseEntity<?> deleteByUserIdAndComputerId() {
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping("/admin/setUserRentFormById")
    public ResponseEntity<?> setUserRentFormById(@RequestBody IdRentRequest idRentRequest)throws ControllerException {
        try {
            RentForm man = userRentFormService.getById(idRentRequest.getId());
            userRentFormService.setUserRentFormById(idRentRequest.getId(),idRentRequest.isRent());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

    }
    @GetMapping("/user/getAllByUserId/{id}")
    public ResponseEntity<?> getAllByUserId(@PathVariable(name = "id")Long id) throws ControllerException{
        try {
            return new ResponseEntity<>(userRentFormService.getAllByUserId(id),HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @GetMapping("/admin/getAllByRent/{data}")
    public ResponseEntity<?> getAllByRent(@PathVariable(name = "data")boolean data) throws ControllerException{

        try {
            return new ResponseEntity<>(userRentFormService.getAllByRent(data),HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @PostMapping("/admin/isUserRentExistByComputerId")
    public ResponseEntity<?> isUserRentExistByComputerId(@RequestBody IdRequest idRequest) throws ControllerException{
        try {
            if(userRentFormService.existsByComputerId(idRequest.getId())) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @PostMapping("/user/isUserRentExistByUserId")
    public ResponseEntity<?> isUserRentExistByUserId(@RequestBody IdRequest idRequest) throws ControllerException{
        try {
            if(!rentFormRepository.existsByUserId(idRequest.getId())){
                return  new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FOUND);
        } catch (RepositoryException e) {
            throw new ControllerException(e);
        }
    }
    @PostMapping("/user/createUserRent")
    public ResponseEntity<?> createUserRent(@RequestBody @Validated UserRentRequestNoId userRentRequestNoId , BindingResult bindingResult)throws ControllerException {
        if(!bindingResult.hasErrors()) {
            RentForm userRentForm = new RentForm(
                    userRentRequestNoId.getUser(),
                    userRentRequestNoId.getName(),
                    userRentRequestNoId.getSurname(),
                    userRentRequestNoId.getComputer()
            );
            try {
                RentForm test = new RentForm();
                test.setId(15L);
                rentValidator.validate(test,bindingResult);
                if(bindingResult.hasErrors()) throw new ControllerException("not correct data");
                userRentFormService.create(userRentForm);
                return new ResponseEntity<>(userRentForm, HttpStatus.OK);
            } catch (Exception e) {
                throw new ControllerException(e);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
