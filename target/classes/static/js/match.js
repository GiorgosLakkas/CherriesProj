document.addEventListener('DOMContentLoaded', () => {
    const findMatchButton = document.getElementById('find-match-btn');
    const matchResults = document.getElementById('match-results');

    findMatchButton.addEventListener('click', () => {
        matchResults.innerHTML = "<h2>Finding your perfect match...</h2>";

        setTimeout(() => {
            const matchData = {
                name: "Myrto Iovi",
                university: "Athens University of Economics and Business",
                department: "Management Science and Technology",
                year: "2nd Year",
                hobbies: "Coding, Hiking, Cooking",
                category: "Friendship",
                matchPercentage: 87,
            };

            matchResults.innerHTML = `
                <h2>Your Match Found!</h2>
                <p><strong>Name:</strong> ${matchData.name}</p>
                <p><strong>University:</strong> ${matchData.university}</p>
                <p><strong>Department:</strong> ${matchData.department}</p>
                <p><strong>Year of Study:</strong> ${matchData.year}</p>
                <p><strong>Hobbies:</strong> ${matchData.hobbies}</p>
                <p><strong>Category:</strong> ${matchData.category}</p>
                <p><strong>Match Percentage:</strong> ${matchData.matchPercentage}%</p>
            `;
        }, 2000); 
    });
});
