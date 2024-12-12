window.addEventListener('scroll', () => {
    document.querySelector('nav').classList.toggle('window-scroll', window.scrollY > 0)
})



const faqs = document.querySelectorAll('.faq');

faqs.forEach(faq => {
    faq.addEventListener('click', () => {
        faq.classList.toggle('open');

        const icon = faq.querySelector('.faq__icon i');
        if (icon.className === "uil uil-plus") {
            icon.className = "uil uil-minus";
        } else {
            icon.className = "uil uil-plus";
        }
    })
})

document.addEventListener('DOMContentLoaded', () => {
    const messageInput = document.getElementById('messageInput');
    const sendButton = document.getElementById('sendButton');
    const chatMessages = document.querySelector('.chat-messages');

    // Function to add a new message
    const addMessage = (text, type) => {
        const message = document.createElement('div');
        message.classList.add('message', type);
        message.textContent = text;
        chatMessages.appendChild(message);

        // Scroll to the bottom of the chat
        chatMessages.scrollTop = chatMessages.scrollHeight;
    };

    // Handle send button click
    sendButton.addEventListener('click', () => {
        const text = messageInput.value.trim();
        if (text) {
            addMessage(text, 'sent');
            messageInput.value = '';

            // Simulate a received message
            setTimeout(() => {
                addMessage('This is a received message!', 'received');
            }, 1000);
        }
    });

    // Handle Enter key press
    messageInput.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            sendButton.click();
        }
    });
});


document.addEventListener('DOMContentLoaded', () => {
    const notifications = document.querySelectorAll('.notification');

    notifications.forEach(notification => {
        notification.addEventListener('click', () => {
            notification.classList.remove('unread');
            notification.classList.add('read');
        });
    });
});


document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById("searchInput");
    const searchButton = document.getElementById("searchButton");
    const usersGrid = document.getElementById("usersGrid");

    // Sample users data
    const users = [
        { name: "John Doe", username: "@johndoe", avatar: "https://via.placeholder.com/150" },
        { name: "Jane Smith", username: "@janesmith", avatar: "https://via.placeholder.com/150" },
        { name: "Emily Clark", username: "@emilyclark", avatar: "https://via.placeholder.com/150" },
        { name: "Mark Lee", username: "@marklee", avatar: "https://via.placeholder.com/150" },
        { name: "Sophia Brown", username: "@sophiabrown", avatar: "https://via.placeholder.com/150" },
    ];

    // Render users to the grid
    function renderUsers(filter = "") {
        const filteredUsers = users.filter(user =>
            user.name.toLowerCase().includes(filter.toLowerCase()) ||
            user.username.toLowerCase().includes(filter.toLowerCase())
        );

        usersGrid.innerHTML = filteredUsers.map(user => `
            <div class="user__card">
                <div class="card__image">
                    <img src="${user.avatar}" alt="${user.name}">
                </div>
                <div class="card__content">
                    <h4>${user.name}</h4>
                    <p>${user.username}</p>
                    <button class="follow-btn">Follow</button>
                </div>
            </div>
        `).join("");
    }

    // Initial render
    renderUsers();

    // Search functionality
    searchButton.addEventListener("click", () => {
        renderUsers(searchInput.value);
    });

    searchInput.addEventListener("keyup", (event) => {
        if (event.key === "Enter") {
            renderUsers(searchInput.value);
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    // Get the profile picture element
    const profilePic = document.querySelector('.profile-pic');
  
    // Get the dropdown menu
    const dropdownMenu = document.querySelector('.dropdown-menu');
  
    // Toggle the dropdown menu when the profile picture is clicked
    profilePic.addEventListener('click', function(event) {
      // Prevent the event from propagating and triggering other click events
      event.stopPropagation();
  
      // Toggle the display of the dropdown menu
      dropdownMenu.style.display = (dropdownMenu.style.display === 'block') ? 'none' : 'block';
    });
  
    // Close the dropdown if clicked outside the profile
    document.addEventListener('click', function(event) {
      if (!profilePic.contains(event.target)) {
        dropdownMenu.style.display = 'none';
      }
    });
});
  