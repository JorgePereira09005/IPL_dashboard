package jmpc.ipl_dashboard.repository;

import jmpc.ipl_dashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    //WRONG
//    List<Match> getByTeam1OrTeam2AndDateBetweenOrderByDateDesc(String teamName1, String teamName2,
//                                                               LocalDate date1, LocalDate date2);

    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) " +
            "AND m.date Between :dateStart and :dateEnd " +
            "order by date desc")
    List<Match> getMatchesByTeamBetweenDates(@Param("teamName") String teamName,
                                             @Param("dateStart") LocalDate dateStart,
                                             @Param("dateEnd") LocalDate dateEnd);

    //used to obtain only 4 records at a time
    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0,4));
    }
}
