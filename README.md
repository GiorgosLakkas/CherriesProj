<<<<<<< HEAD
# UniMeets
**Βρες το cherry σου!** 🍒



Το **UniMeets** είναι μια πλατφόρμα δικτύωσης που συνδέει φοιτητές με βάση κοινά ακαδημαϊκά, επαγγελματικά και προσωπικά ενδιαφέροντα. 
Στόχος είναι η δημιουργία ουσιαστικών γνωριμιών, είτε για συνεργασία, είτε για κοινωνικοποίηση, είτε για καθοδήγηση.

## Λειτουργίες
Το UniMeets δίνει τη δυνατότητα στους χρήστες να βρουν το κατάλληλο **match** με βάση τους εξής συνδυασμούς:

1. **Πανεπιστήμιο**
2. **Τμήμα**
3. **Έτος**
4. **Κατεύθυνση σπουδών**
5. **Ηλικία**
6. **Ενδιαφέροντα** - Προσδιόρισε τα ενδιαφέροντά σου και βρες άτομα με κοινές προτιμήσεις!
7. **Λόγος αναζήτησης** 
   - Κοινωνικοποίηση
   - Έυρεση ατόμων για ερχασία
   - Έυρεση ατόμων για να συμμετάσχετε μαζί σε εκδηλώσεις και εθελοντικούς συλλόγους

## Οδηγίες εκτέλεσης και μεταγλώττισης
1. mvn clean install
2. java -jar junit5-jupiter-starter-maven-1.0-SNAPSHOT.jar

## Οδηγίες Χρήσης

## Project Structure

UniMeets/
│
├── pom.xml                       # Maven dependencies and configurations        
├── README.md                # Project documentation
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/unimeets/
│   │   │       ├── DemoApplication.java  # Main application class
│   │   │       
│   │   │             
│   │   │       
│   │   └── resources/
│   │       ├── application.properties     # Spring Boot configuration
│   │       └── static/
│   │           └── style.css              # Application styles
│   │
│   ├── test/
│   │   └── java/
│   │       └── com/example/unimeets/      # Unit and integration tests
│
├── target/                       # Compiled files after build (auto-generated)
│   └── junit5-jupiter-starter-maven-1.0-SNAPSHOT.jar

## uml

## Επισκόπηση Δομών Δεδομένων και Αλγορίθμων
- **Δομές Δεδομένων:**
  - `MyAppUser`: Κλάση που αναπαριστά έναν χρήστη με στοιχεία όπως username, όνομα, email.
  - `UserProfile`: Κλάση που περιέχει προφίλ χρήστη όπως πανεπιστήμιο, ηλικία, ενδιαφέροντα, κ.λπ.
  - `Event` και `Assignment`: Αντικείμενα που σχετίζονται με εκδηλώσεις και εργασίες αντίστοιχα.

- **Αλγόριθμοι:**
  - **MatchAlgorithm:** Αφηρημένη κλάση για αναζήτηση αντιστοιχιών με εξειδικεύσεις `MatchAlgoFriend`, `MatchAlgoEvent`, και `MatchAlgoProject`.
  - **EventNameMatcher:** Αλγόριθμος για σύγκριση ονομάτων εκδηλώσεων και εργασιών.


## Συμμετοχή
Εάν θέλεις να συμβάλεις στην ανάπτυξη του UniMeets, ακολούθησε τα παρακάτω βήματα:
1. Κάνε **fork** το repository.
2. Δημιούργησε ένα **branch** για το χαρακτηριστικό ή το bug που θα δουλέψεις.
3. Υπόβαλε ένα **pull request** με τις αλλαγές σου.

## Άδεια Χρήσης

Αυτό το έργο διαθέτει άδεια χρήσης υπό την Άδεια MIT. Δείτε το αρχείο [LICENSE](LICENSE) για περισσότερες λεπτομέρειες.

## Άδειες Χρήσης Κώδικα Τρίτων

Αυτό το έργο χρησιμοποιεί τις παρακάτω βιβλιοθήκες τρίτων και τις αντίστοιχες άδειες χρήσης:

- **Spring Boot Starter Web, Data JPA, Security, Mail** - [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)  
- **JUnit Jupiter (junit-bom, junit-jupiter)** - [Eclipse Public License 2.0](https://www.eclipse.org/legal/epl-2.0/)  
- **Apache Commons Text** - [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)  
- **Jakarta Validation API** - [Eclipse Public License 2.0](https://www.eclipse.org/legal/epl-2.0/)  
- **Jakarta Mail API** - [Eclipse Public License 2.0](https://www.eclipse.org/legal/epl-2.0/)  
- **MySQL Connector Java** - [GNU General Public License v2 with FOSS Exception](https://www.mysql.com/about/legal/licensing/)  
- **MSSQL JDBC Driver** - [MIT License](https://opensource.org/licenses/MIT)  
- **Oracle JDBC (ojdbc11)** - [Oracle Technology Network License](https://www.oracle.com/downloads/licenses/otn-license.html)  
- **jBCrypt** - [ISC License](https://opensource.org/licenses/ISC)  
- **Maven Checkstyle Plugin** - [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)  
- **Maven Jar Plugin** - [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)  

Οι πλήρεις όροι χρήσης βρίσκονται στους αντίστοιχους συνδέσμους.




=======
>>>>>>> ddc24e2277925a8957f6d252698415fae5c4ad1a

