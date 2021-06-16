package example.Repository;

import example.Models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users,Integer> {
    Page<Users> findAll(Pageable pageable);
    @Query(value = "SELECT a FROM Users a WHERE a.name=?1")
    Page<Users> findIdUser(String gg, Pageable pageable);
}
