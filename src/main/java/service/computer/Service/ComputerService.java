package service.computer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.computer.Exception.RepositoryException;
import service.computer.Exception.ServiceException;
import service.computer.Models.Computer;
import service.computer.Repository.ComputerRepository;
import service.computer.Service.interfaces.IComputerService;

import java.util.Date;
import java.util.List;

@Service
public class ComputerService implements IComputerService {
    @Autowired
    private ComputerRepository computerRepository;

    @Override
    public void deleteById(Long id) {
        computerRepository.deleteById(id);
    }

    @Override
    public Computer create(Computer computer)throws ServiceException {
        return computerRepository.save(computer);
    }

    @Override
    public boolean existsByName(String name) throws ServiceException {
        try {
            return computerRepository.existsByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Computer> getAll()throws ServiceException {
        return computerRepository.findAll();
    }

    @Override
    public void deleteByName(String name)throws ServiceException {
        try {
            computerRepository.deleteByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public Computer getById(Long id)throws ServiceException {
        try {
            return computerRepository.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public Computer getByName(String name)throws ServiceException {
        try {
            return computerRepository.getByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public void updateComputerById(Long id, String name,String description,Date expirationDate, int cost ) throws ServiceException{
        try {
            computerRepository.updateComputerById(id, name, description,expirationDate, cost);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }
}
