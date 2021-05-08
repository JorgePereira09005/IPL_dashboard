import { React, useEffect, useState } from 'react';
import {useParams} from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

//watch the goddamn video before complaining
export const TeamPage = () => {

  //initialize team object with empty object
  const [team, setTeam] = useState({matches : []});

  //useParams returns an object with every path parameter
  // We use destructuring to get the teamName from that object
  const { teamName } = useParams();

  useEffect(
    
    //executes when component is loaded
    () => {
      const fetchMatches = async () => {
        const response = await fetch(`http://localhost:8080/team/${teamName}`);
        const data = await response.json();
        setTeam(data);
      };

      fetchMatches();

    // empty array means "run only on first page load"
    // reload page when property "teamName" changes
    }, [teamName]
  );
  
  if (!team || !team.teamName) {
    return <h1>Team not found!</h1>
  }

  return (
    <div className="TeamPage">
        <h1>{team.teamName}</h1>

        <MatchDetailCard teamName = {team.teamName} match = {team.matches[0]} />

        {/*render from the 1st element forward*/}
        {team.matches.slice(1).map(match => <MatchSmallCard teamName = {team.teamName} match = {match}/>)}

    </div>
  );
}
