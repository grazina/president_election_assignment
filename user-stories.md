# User stories for the President Election application

## As a Voter, I want to see the list of candidates, so that I could select the one to vote for
<ol>
    <li>Endpoint <b>/api/election/getCandidates</b> must return a list of <a name="candidate-obj"><b>Candidate</b></a> objects with the
        following structure:
        <ol>
            <li><b>id</b>. This is the unique identifier of the candidate in the system.</li>
            <li><b>name</b>. This is the full name of the candidate.</li>
            <li><b>number</b>. This is the order of the candidate among others on the ballot paper.</li>
            <li><b>agenda</b>. Summary of the candidate agenda.</li>
        </ol>
    </li>
</ol>

Example output:

    [
      {
        "id": 1,
        "name": "Gitanas Nausėda",
        "number": 1,
        "agenda": "Lietuva – transatlantinėje erdvėje"
      },
      {
        "id": 2,
        "name": "Ingrida Šimonytė",
        "number": 2,
        "agenda": "Vakarų vertybės ir į vakarus orietuota užsienio politika"
      }
    ]

## As a Voter, I want to vote for a particular candidate, so that my vote is saved exactly once
<ol>
    <li>Endpoint <b>/api/election/vote</b> must save a voter's vote for a particular candidate. 
        The input to the endpoint must be the <b>Vote</b> object with the following structure:
        <ol>
            <li><b>voterId</b>. This is the unique identifier of the voter in the system.</li>
            <li><b>selectedCandidateId</b>. This ID must match the one returned by the /api/election/getCandidates
                endpoint.
            </li>
        </ol>
    </li>
    <li>A voter is not allowed to change his vote.
    </li>
</ol>

Example input:

    {
      "selectedCandidateId": 1,
      "voterId": 125433
    }

## As a Voter, I want to view the election result report, so that I could compare the overall vote counts of the candidates 
<ol>
<li>Endpoint <b>/api/election/getElectionResults</b> must return the overall distribution of the votes among the candidates.
    An <a name="election-results-obj"><b>Election results by candidate</b></a> object with the following structure must be returned:
    <ol>
        <li><b>activeVotersCount</b>. The number of voters that have placed their votes. </li>
        <li><b>totalVotersCount</b>. The total number of voters.</li>
        <li><b>candidateResults</b>. A list of <a name="candidate-results-obj"><b>Candidate results</b></a> objects with the following structure:
            <ol>
                <li><b>candidate</b>. A <b>Candidate</b> object <a href="#candidate-obj">(see above)</a>, 
                		        where only <b>id</b> and <b>name</b> fields are filled.
                </li>
                <li><b>candidateVotesCount</b>. Number of voters that have voted for this candidate.</li>
            </ol>
        </li>  
    </ol>
    </li>
</ol>

Example output:

      {
        "activeVotersCount": 1342094,
        "totalVotersCount": 2491042,
        "candidateResults": [
          {
            "candidate": {
              "id": 1,
              "name": "Gitanas Nausėda"
            },
            "candidateVotesCount": 881495
          },
          {
            "candidate": {
              "id": 2,
              "name": "Ingrida Šimonytė"
            },
            "candidateVotesCount": 443394
          }
        ]
      }

## As a Voter, I want to view the election result report, so that I could compare the vote counts of the candidates by region
<ol>
    <li>Endpoint <b>/api/election/getElectionResultsByRegion</b> must return the distribution of the votes among
    the candidates by regions. A list of <b>Region results</b> objects must be returned, each with the
    following structure:
    <ol>
        <li><b>regionId</b>. This is the unique identifier of the region in the system.</li>
        <li><b>regionName</b>. Region name.</li>
        <li><b>resultsByCandidate</b>. An <b>Election results by candidate</b> object <a href="#election-results-obj">(see above)</a>. 
        Figures contained by this object (i.e. active voters count, total voters count, candidate votes count) 
        must be computed based on the portion of the voters from this particular region. 
    </ol>
    </li>
</ol>

Example output:

    [
      {
        "regionId": 1,
        "regionName": "Alytaus apskr.",
        "resultsByCandidate": {
          "activeVotersCount": 100000,
          "totalVotersCount": 200000,
          "candidateResults": [
            {
              "candidate": {
                "id": 4,
                "name": "Gitanas Nausėda"
              },
              "candidateVotesCount": 60000
            },
            {
              "candidate": {
                "id": 8,
                "name": "Ingrida Šimonytė"
              },
              "candidateVotesCount": 40000
            }
          ]
        }
      }
    ]

## As a Voter, I want to find out the election winner or the two top candidates, if a single winner has not been determined
<ol>
    <li>Endpoint <b>/api/election/getWinner</b> must return the winner of the election. A <b>Winner</b> object
     with the following structure must be returned:
    <ol>
        <li><b>singleWinner</b>. A boolean field indicating if a single winner has been determined by the election. 
        This field is TRUE if the top candidate has acquired more than 50% of the votes.</li>
        <li><b>totalVotersCount</b>. The total number of voters.</li>
        <li><b>activeVotersCount</b>. The number of active voters.</li>
        <li><b>firstCandidate</b>. A <b>Candidate results</b> object <a href="#candidate-results-obj">(see above)</a> for the top candidate. 
        <li><b>secondCandidate</b>. A <b>Candidate results</b> <a href="#candidate-results-obj">(see above)</a> object for the second top candidate.
         This field must be filled only if there is no single winner.</li>
    </ol>
    </li>
</ol>

Example output, when there is a single winner:

    {
      "singleWinner": true,
      "activeVotersCount": 100000,
      "totalVotersCount": 200000,
      "firstCandidate": {
        "candidate": {
          "id": 4,
          "name": "Gitanas Nausėda"
        },
        "candidateVotesCount": 60000
      },
      "secondCandidate": null
    }
    