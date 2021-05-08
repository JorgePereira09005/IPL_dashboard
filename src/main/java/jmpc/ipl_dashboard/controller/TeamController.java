package jmpc.ipl_dashboard.controller;

import jmpc.ipl_dashboard.model.Match;
import jmpc.ipl_dashboard.model.Team;
import jmpc.ipl_dashboard.repository.MatchRepository;
import jmpc.ipl_dashboard.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
       Team team = this.teamRepository.findByTeamName(teamName);

       if (team!= null) team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName, 4));
       return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName,
                                         @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchRepository.getMatchesByTeamBetweenDates(
                teamName, startDate, endDate);
    }

}
