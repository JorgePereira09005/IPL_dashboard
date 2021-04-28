import { React, useEffect, useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

//watch the goddamn video before complaining
export const TeamPage = () => {

  //initialize team object with empty object
  const [team, setTeam] = useState({matches : []});

  useEffect(
    
    //executes when component is loaded
    () => {
      const fetchMatches = async () => {
        const response = await fetch('http://localhost:8080/team/Rajasthan Royals');
        const data = await response.json();
        setTeam(data);
      };

      fetchMatches();

    // empty array means "run only on first page load"
    }, []
  );
  
  //{team.teamName}

  return (
    <div className="TeamPage">
        <h1>{team.teamName}</h1>

        <MatchDetailCard match = {team.matches[0]} />

        {/*render from the 1st element forward*/}
        {team.matches.slice(1).map(match => <MatchSmallCard  match = {match}/>)}

    </div>
  );
}
