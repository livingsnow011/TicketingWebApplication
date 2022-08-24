package ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ticket.entity.Show;
import ticket.entity.ShowDate;

import java.util.List;

public interface ShowDateRepository extends JpaRepository<ShowDate,Long> {
    List<ShowDate> findByShowIdOrderByShowDateAsc(Long showId);

    List<ShowDate> findByShowId(Long showId);

    @Modifying
    @Query("delete from ShowDate sd where sd.show=:show")
    void deleteAllByShowIdInQuery(@Param("show") Show show);
}
