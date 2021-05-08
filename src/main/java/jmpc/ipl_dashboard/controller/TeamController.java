package jmpc.ipl_dashboard.controller;

import jmpc.ipl_dashboard.model.Team;
import jmpc.ipl_dashboard.repository.MatchRepository;
import jmpc.ipl_dashboard.repository.TeamRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
