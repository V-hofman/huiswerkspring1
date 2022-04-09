package com.example.huiswerkspring1;


import com.example.huiswerkspring1.models.Book;
import com.example.huiswerkspring1.repos.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/* Hier voeren we tests uit op onze database
* @DataJpaTest: hebben we nodig om JPA repositories te testen.
*   https://reflectoring.io/spring-boot-data-jpa-test/
*
* @TestMethodOrder: Met welke volgorde gaan we de tests uitvoeren?
*   https://junit.org/junit5/docs/5.5.0/api/org/junit/jupiter/api/TestMethodOrder.html
*
* @Autowired: Dit zorgt ervoor dat dependencies (benodigdheden) automatisch worden "Geinjecteerd"
*   Hierdoor kan je automatisch relaties tussen verschillende "Beans" leggen.
*   https://www.baeldung.com/spring-autowire
*
* @Test: Spreekt voor zich, zegt tegen Junit dat dit een test is die moet worden uitgevoerd.
*   https://www.vogella.com/tutorials/JUnit/article.html
*
* @Order: Deze is nodig voor @TestMethodOrder met OrderAnnotation.class Hiermee vertel je welke volgorde de code moet worden uitgevoerd.
*
*  !!!!!!HANDIG IN ALGEMEEN: https://www.codejava.net/frameworks/spring-boot/junit-tests-for-spring-data-jpa!!!!!!
*
* TestEntityManager, Deze is gewoon heel handig om een JPA -applicatie/-methode te testen. Wat het precies doet, weet ik ook niet 100%
 */

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookTest {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private TestEntityManager entityManager;

    //Eerst maken we een book aan, deze wordt opgeslagen, dan zoeken we deze boek en die wordt vergeleken met het opgeslagen boek.
    // Dan weten we meteen of het goed wordt opgeslagen.
    @Test
    @Order(1)
    public void testCreateBook()
    {
        Book book = createTestBook();

        Book savedBook = bookRepo.save(book);
        Book findBook = entityManager.find(Book.class, savedBook.getISBN() );

        assertThat(findBook.equals(savedBook));
    }

    //Hier slaan we we weer een book op, en dan verwijderen we die weer, dan kijken we of hem kunnen vinden
    //We hopen dat die null teruggeeft, dan is ie goed verwijdert
    @Test
    @Order(2)
    public void testDeleteUser()
    {
        Book book = createTestBook();
        Book savedBook = bookRepo.save(book);

        bookRepo.deleteById(entityManager.find(Book.class, savedBook.getISBN()).getISBN());
        assertThat(entityManager.find(Book.class, book.getISBN())).isNull();
    }


    //Hiermee kunnen we simpel testen met een boek als we @Rollback(false) zouden doen
    public Book createTestBook()
    {
        Book book = new Book();
        book.setName("TestDiner");
        book.setPrice(4.99);
        book.setQtyInStock(2);
        return book;
    }
}
