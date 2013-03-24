package data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Long> {

    @Query("select SUM(p.price) from Position p where p.purchased = true")
    Long getTotal();

}
