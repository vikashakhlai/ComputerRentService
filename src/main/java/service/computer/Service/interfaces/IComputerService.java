package service.computer.Service.interfaces;

import org.springframework.stereotype.Service;
import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;
import service.computer.Models.Computer;

import java.util.Date;
import java.util.List;

@Service
public interface IComputerService {
    @Transactional
    void deleteById(Long id) throws ServiceException;

    Computer create(Computer computerStuff)throws ServiceException, service.computer.Exception.ServiceException;

    boolean existsByName(String name) throws ServiceException, service.computer.Exception.ServiceException;

    List<Computer> getAll()throws ServiceException, service.computer.Exception.ServiceException;
    @Transactional
    void deleteByName(String name)throws ServiceException, service.computer.Exception.ServiceException;

    Computer getById(Long id)throws ServiceException, service.computer.Exception.ServiceException;

    Computer getByName(String name)throws ServiceException, service.computer.Exception.ServiceException;

    @Transactional
    void updateComputerById(
            Long id,
            String name,
            String description,
            Date expirationDate,
            int cost)throws ServiceException, service.computer.Exception.ServiceException;

}