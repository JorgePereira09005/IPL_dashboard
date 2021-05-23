import { React, useEffect, useState } from 'react';
import {useParams} from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

//watch the goddamn video before complaining
export const MatchPage = () => {

    //declare state variable and method to set that state
    const [matches, setMatches] = useState([]);
    const { teamName, year } = useParams();

    useEffect(
    
        //executes when component is loaded
        () => {
          const fetchMatches = async () => {
            const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
            const data = await response.json();
            setMatches(data);
          };
    
          fetchMatches();
    
        // empty array means "run only on first page load"
        // reload page when property "teamName" changes
        }, []
      ); 

    return (
        <div className="MatchPage">
            <h1>Match Page</h1>

            {
                matches.map(match => <MatchDetailCard teamName = {teamName} match = {match}/>)
            }

        </div>
    );
}
