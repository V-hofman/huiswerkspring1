package com.example.huiswerkspring1.repos;


import com.example.huiswerkspring1.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/* Aangezien we een connectie moeten leggen met een database gebruiken we een Repository om deze te linken met je Java applicatie.
 * JpaRepository geeft heel veel methodes cadeau, vandaar dat hier op dit moment geen Queries staan geschreven.
 *   https://www.educba.com/java-repository/
 *
 * @Query: dit laat weten dat als de onderstaande methode wordt aangeroepen dat deze Query moet worden uitgevoerd op je database.
 *   https://www.baeldung.com/spring-data-jpa-query
 *
 */
public interface BookRepository extends JpaRepository<Book, Long> {

        /*
    * Voorbeeld van een Query die je zelf kan toevoegen.

        @Query("SELECT u FROM User u WHERE u.Username =?1")
    User findByUsername(String username);

     */
}
