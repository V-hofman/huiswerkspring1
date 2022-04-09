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
 * @JoinColumn: Deze geeft aan dat de table een FOREIGN KEY bevat, dit is nodig om relaties tussen 2 tabellen te leggen.
 *  Deze moet dan ook altijd wijzen naar een PRIMARY KEY van een ander tabel. OOK MOET JE DE RELATIE TUSSEN DE TABELLEN MET EEN ANNOTATION ERBIJ ZETTEN.
 *  https://www.baeldung.com/jpa-join-column
 *
 * @ManyToOne: Deze geeft een "Many To One" Relatie weer in een database. ZIE LINK HIERBOVEN BIJ @JoinColumn
 *
 * Een model class heeft altijd Getters/Setters nodig !!!DEZE MOET OOK HETZELFDE NAAM HEBBEN ALS JE VARIABELEN!!! er zijn manieren omheen, maar hou het makkelijk.
 *
 *
 */

@Entity
@Table
public class Book {

    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long ISBN;
    @Column(nullable = false)
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="authorID")
    private Author author;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int qtyInStock = 0;

    //region Constructors
    public Book(String name, Author author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.qtyInStock = qtyInStock;
    }

    public Book(String name, Author author, double price, int qtyInStock) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.qtyInStock = qtyInStock;
    }

    public Book() {

    }
    //endregion

    //region Getters/Setters
    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQtyInStock() {
        return qtyInStock;
    }

    public void setQtyInStock(int qtyInStock) {
        this.qtyInStock = qtyInStock;
    }
    //endregion
}
