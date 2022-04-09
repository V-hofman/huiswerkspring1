package com.example.huiswerkspring1.repos;


import com.example.huiswerkspring1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/* Aangezien we een connectie moeten leggen met een database gebruiken we een Repository om deze te linken met je Java applicatie.
* JpaRepository geeft heel veel methodes cadeau, vandaar dat hier op dit moment geen Queries staan geschreven.
*   https://www.educba.com/java-repository/
*
* @Query: dit laat weten dat als de onderstaande methode wordt aangeroepen dat deze Query moet worden uitgevoerd op je database.
*   https://www.baeldung.com/spring-data-jpa-query
*
 */

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    //Custom query om authors te zoeken
    @Query("SELECT u FROM Author u WHERE u.name =?1")
    Author findByName(String name);


}
