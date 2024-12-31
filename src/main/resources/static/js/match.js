document.addEventListener('DOMContentLoaded', () => {
    const findMatchButton = document.getElementById('find-match-btn');
    const matchResults = document.getElementById('match-results');

    findMatchButton.addEventListener('click', () => {
        matchResults.innerHTML = "<h2>Finding your perfect match...</h2>";

        // Simulated data (to be replaced by Java backend results)
        setTimeout(() => {
            const matchData = {
                name: "Alex Johnson",
                university: "University of California",
                department: "Computer Science",
                year: "3rd Year",
                hobbies: "Coding, Hiking, Photography",
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
        }, 2000); // Simulates processing time
    });
});
