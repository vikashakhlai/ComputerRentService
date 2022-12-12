package service.computer.Service.interfaces;

import service.computer.Exception.RepositoryException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;
import service.computer.Models.RentForm;

import java.util.Date;
import java.util.List;

public interface IUserRentFormService {
    @Transactional
    void deleteById(Long id)throws ServiceException;
    @Transactional
    void deleteByUserIdAndComputerId(Long user_id, Long computerStuff_id) throws ServiceException, service.computer.Exception.ServiceException;

    RentForm create(RentForm userRentForm)throws ServiceException, service.computer.Exception.ServiceException;
    boolean existsByComputerId(Long computerStuff_id) throws ServiceException, service.computer.Exception.ServiceException;
    RentForm getById(Long id)throws ServiceException, service.computer.Exception.ServiceException;

    List<RentForm> getAllByUserId(Long user_id)throws ServiceException, service.computer.Exception.ServiceException;
    List<RentForm> getAllByRent(boolean rent)throws ServiceException, service.computer.Exception.ServiceException;

    List<RentForm> getAllByComputerExpirationDateLessThan(Date computerStuff_expirationDate)throws ServiceException, service.computer.Exception.ServiceException;
    @Transactional
    void setUserRentFormById(Long id, boolean rent)throws ServiceException, service.computer.Exception.ServiceException;
}
