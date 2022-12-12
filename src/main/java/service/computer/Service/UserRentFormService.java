package service.computer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.computer.Exception.RepositoryException;
import service.computer.Exception.ServiceException;
import service.computer.Models.RentForm;
import service.computer.Repository.UserRentFormRepository;
import service.computer.Service.interfaces.IUserRentFormService;

import java.util.Date;
import java.util.List;

@Service
public class UserRentFormService implements IUserRentFormService {
    @Autowired
    private UserRentFormRepository userRentFormRepository;
    @Override
    public void deleteById(Long id) {
        userRentFormRepository.deleteById(id);
    }

    @Override
    public void deleteByUserIdAndComputerId(Long user_id, Long computerStuff_id) throws ServiceException {
        try {
            userRentFormRepository.deleteByUserIdAndComputerId(user_id, computerStuff_id);
        } catch (RepositoryException e) {

            throw new ServiceException(e);
        }
    }

    @Override
    public RentForm create(RentForm userRentForm){
        return userRentFormRepository.save(userRentForm);
    }

    @Override
    public boolean existsByComputerId(Long computerStuff_id) throws ServiceException {
        try {
            return userRentFormRepository.existsByComputerId(computerStuff_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RentForm getById(Long id)throws ServiceException {
        return userRentFormRepository.getById(id);
    }

    @Override
    public List<RentForm> getAllByUserId(Long user_id)throws ServiceException {
        try {
            return userRentFormRepository.getAllByUserId(user_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public List<RentForm> getAllByRent(boolean rent)throws ServiceException {
        try {
            return userRentFormRepository.getAllByRent(rent);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public List<RentForm> getAllByComputerExpirationDateLessThan(Date computerStuff_expirationDate) throws ServiceException{
        try {
            return userRentFormRepository.getAllByComputerExpirationDateLessThan(computerStuff_expirationDate);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setUserRentFormById(Long id, boolean rent) throws ServiceException{
        try {
            userRentFormRepository.setUserRentFormById(id, rent);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }
}
