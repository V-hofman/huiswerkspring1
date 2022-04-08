package com.example.huiswerkspring1.models;


import javax.persistence.*;

/* We koppelen deze data aan een data-base (in dit voorbeeld is het een H2 database in memory)
* Wij hebben dus modellen nodig (denk MVC MODEL-VIEW-CONTROLLER)
* Dit is het model voor een Author.
*
* Annotations worden gebruikt door spring boot om bepaalde rechten te geven aan objects/classes/etc.
* Handige annotations: https://www.upgrad.com/blog/spring-boot-annotations/
* Uitleg annotations deze bestand:
* @Entity: Wij gebruiken Spring Data JPA, dit zorgt ervoor dat we data kunnen stoppen in en data halen vanuit (relationele) databases.
* Met een @Entity geef je aan dat er een JPA-entiteit is die gekoppeld is met een database table.
*   https://stackoverflow.com/questions/63414381/what-is-entity-in-spring-jpa
*
* @Table: Elke @Entity heeft een table nodig waar die aan gelinkt is, bij default zit deze in @Entity en in deze voorbeeld class is deze dus niet nodig.
*   Mocht de naam van de class anders zijn dan de database table, kan je deze gebruiken om het aan te passen. @Table(name = "tableName")
*   https://www.baeldung.com/jpa-entity-table-names
*
* @Id: Hiermee geef je aan dat de kolom een Primary Key is
*   Primary Key: https://www.techopedia.com/definition/5547/primary-key
*   Kolom:       https://www.techopedia.com/definition/8/database-colum
*   https://spring.io/guides/gs/accessing-data-jpa/n
*
* @GeneratedValue: Dit is een manier om te vertellen dat als er een nieuwe kolom wordt aangemaakt, dat deze waarde automatisch omhoog mag.
*   Er zijn meerdere strategieën die je kan gebruiken namelijk 4. Zie link hieronder.
*   https://stackoverflow.com/questions/47676403/spring-generatedvalue-annotation-usage
*
* @Column: Hiermee geef je aan dat deze variabel ook een kolom is die waardes kan ontvangen in je table, hier zijn veel argumenten die je kan meegeven
*   https://www.w3schools.blog/jpa-column-annotation
*
* Een model class heeft altijd Getters/Setters nodig !!!DEZE MOET OOK HETZELFDE NAAM HEBBEN ALS JE VARIABELEN!!! er zijn manieren omheen, maar hou het makkelijk.
*
*
 */

@Entity
@Table
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name="authorID")
    private int authorID;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private char gender;

    //region Constructors
    public Author(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public Author() {

    }
    //endregion

    //region getter/setters
    public int getAuthorID()
    {
        return authorID;
    }

    public void setAuthorID(int authorID)
    {
        this.authorID = authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
    //endregion

    public String toString()
    {
        return "iets";
    }
}
