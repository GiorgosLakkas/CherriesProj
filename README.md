<<<<<<< HEAD
# UniMeets
**Βρες το cherry σου!** 🍒


Το **UniMeets** είναι μια πλατφόρμα δικτύωσης που συνδέει φοιτητές με βάση κοινά ακαδημαϊκά, επαγγελματικά και προσωπικά ενδιαφέροντα. 
Στόχος είναι η δημιουργία ουσιαστικών γνωριμιών, είτε για συνεργασία, είτε για κοινωνικοποίηση, είτε για καθοδήγηση.


## Οδηγίες εκτέλεσης και μεταγλώττισης
Οδηγίες εκτέλεσης και μεταγλώττισης  
Μεταγλώττιση του έργου
- Εκτελέστε την εντολή:
`mvn clean install`

Αυτή η εντολή θα δημιουργήσει το αρχείο JAR στην τοποθεσία target/.

- Εκτέλεση του JAR
Πριν εκτελέσετε το αρχείο JAR, πρέπει να μεταβείτε στον φάκελο που το περιέχει. Ακολουθήστε τα παρακάτω βήματα:

- Ανοίξτε ένα τερματικό (ή command prompt).

- Μεταβείτε στον φάκελο target που περιέχει το αρχείο JAR.
Για παράδειγμα, αν το αρχείο JAR βρίσκεται στον φάκελο Desktop/Cherry/target:

Linux/Mac:
`cd ~/Desktop/Cherry/target`

Windows:
`cd C:\Users\<ΤοΌνομαΧρήστηΣας>\Desktop\Cherry\target`

(Αντικαταστήστε το <ΤοΌνομαΧρήστηΣας> με το όνομα χρήστη του υπολογιστή σας.)

- Επιβεβαιώστε ότι βρίσκεστε στον σωστό φάκελο.

- Χρησιμοποιήστε την εντολή:

Linux/Mac:
`ls`

Windows:
`dir`

Θα πρέπει να δείτε το αρχείο junit5-jupiter-starter-maven-1.0-SNAPSHOT.jar στη λίστα.

- Εκτελέστε το αρχείο JAR:

`java -jar junit5-jupiter-starter-maven-1.0-SNAPSHOT.jar`

## Οδηγίες Χρήσης
- Εγγραφή στην εφαρμογή (ονοματεπώνυμο, όνομα χρήστη, κωδικός πρόσβασης, email)
- Συμπλήρωση προφίλ με τα στοιχεία του χρήστη (Υποχρεωτικά πεδία: ηλικία, φύλο, πανεπιστήμιο, τμήμα, έτος φοίτησης  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Προαιρετικά πεδία: ενδιαφέροντα (μέχρι 5), εθελοντικές δράσεις)
- Επιλογή σκοπού αναζήτησης ατόμου (κοινωνικοποίηση, εργασία, event)
- Aν επιλεχθεί η κοινωνικοποίηση, γίνεται αυτόματα η αναζήτηση
- Αν επιλεχθεί η εργασία, συμπληρώνει ο χρήστης τμήμα, εξάμηνο, μάθημα, αριθμό συμμετεχόντων
- Αν επιλεχθεί το event, συμπηρώνει το όνομα του event
- Εύρεση ατόμων και εμφάνιση στο χρήστη με ποσοστά

## Project Structure

UniMeets/
│
├── pom.xml &nbsp;&nbsp;&nbsp;# Maven dependencies and configurations        
├── README.md &nbsp;&nbsp;&nbsp;# Project documentation  
├── src/  
│   ├── main/  
│   │   ├── java/  
│   │   │   └── com/example/unimeets/  
│   │   │       ├── DemoApplication.java &nbsp;&nbsp;&nbsp;# Main application class  
│   │   │       
│   │   │             
│   │   │       
│   │   └── resources/  
│   │       ├── application.properties &nbsp;&nbsp;&nbsp;# Spring Boot configuration  
│   │       └── static/  
│   │           └── style.css &nbsp;&nbsp;&nbsp;# Application styles  
│   │
│   ├── test/  
│   │   └── java/  
│   │       └── com/example/unimeets/ &nbsp;&nbsp;&nbsp;# Unit and integration tests  
│
├── target/ &nbsp;&nbsp;&nbsp;# Compiled files after build (auto-generated)  
│   └── junit5-jupiter-starter-maven-1.0-SNAPSHOT.jar  

## UML
![\UML Diagram]()


## Επισκόπηση Δομών Δεδομένων και Αλγορίθμων
- **Δομές Δεδομένων:**
  - `MyAppUser`: Κλάση που αναπαριστά έναν χρήστη με στοιχεία όπως username, όνομα, email.
  - `UserProfile`: Κλάση που περιέχει προφίλ χρήστη όπως πανεπιστήμιο, ηλικία, ενδιαφέροντα, κ.λπ.
  - `Event` και `Assignment`: Αντικείμενα που σχετίζονται με εκδηλώσεις και εργασίες αντίστοιχα.

- **Αλγόριθμοι:**
  - **MatchAlgorithm:** Αφηρημένη κλάση για αναζήτηση αντιστοιχιών με εξειδικεύσεις `MatchAlgoFriend`, `MatchAlgoEvent`, και `MatchAlgoProject`.
  - **EventNameMatcher:** Αλγόριθμος για σύγκριση ονομάτων εκδηλώσεων και εργασιών.


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

