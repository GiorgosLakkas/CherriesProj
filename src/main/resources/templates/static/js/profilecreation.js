document.getElementById('profile-form').addEventListener('submit', async function(e) {
    e.preventDefault();

    // Gather form data
    const formData = new FormData(this);

    // Send to server (example endpoint)
    try {
        const response = await fetch('/api/saveProfile', {
            method: 'POST',
            body: formData,
        });

        if (response.ok) {
            alert("Profile saved successfully!");
            window.location.href = "dashboard.html"; // Redirect to dashboard or next page
        } else {
            alert("There was an error saving your profile. Please try again.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Unable to save profile. Please try again later.");
    }
});
