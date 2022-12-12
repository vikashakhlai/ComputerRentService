package service.computer.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.computer.Exception.RepositoryException;
import service.computer.Models.Computer;
import service.computer.Models.Computer;

import java.util.Date;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    @Modifying
    void deleteById(int id)throws RepositoryException;

    @Modifying
    void deleteByName(String name)throws RepositoryException;

    Computer getById(Long id);

    Computer getByName(String name)throws RepositoryException;

    boolean existsByName(String name)throws RepositoryException;

    @Modifying
    @Query("update Computer c set c.name=:name, c.description=:description,c.expirationDate=:expirationDate, c.cost=:cost where c.id=:id")
    void updateComputerById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("expirationDate") Date expirationDate,
            @Param("cost") int cost
    )throws RepositoryException;
}
