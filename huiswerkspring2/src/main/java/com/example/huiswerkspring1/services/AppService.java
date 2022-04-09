package com.example.huiswerkspring1.services;

import com.example.huiswerkspring1.models.Author;
import com.example.huiswerkspring1.models.Book;
import com.example.huiswerkspring1.repos.AuthorRepository;
import com.example.huiswerkspring1.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


/* Service classes zijn bedoeld voor classes die business functionalities hebben.
*   Dit zijn groter "Controllers" die eigenlijk je logica afhandelen, De reden waarom deze in een service staan
*   en niet in een controller is omdat je deze logica vanuit meerdere controllers wilt kunnen oproepen.
*   als je een methode met hetzelfde functie in meerdere controllers nodig hebt, kan je deze in een service stoppen.
*       https://www.journaldev.com/21435/spring-service-annotation
*
* @Service: Dit geeft aan spring boot door dat dit een Service component is.
*   Zie link hierboven.
*
* @Autowired: Dit zorgt ervoor dat dependencies (benodigdheden) automatisch worden "Geinjecteerd"
*   Hierdoor kan je automatisch relaties tussen verschillende "Beans" leggen.
*   https://www.baeldung.com/spring-autowire
*
 */

@Service
public class AppService {

    //Elke entity heeft zijn eigen repository nodig.

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    EntityManager entityManager;

    //Wij gebruiken de ingebouwde zoekfunctie van JpaRepository om alle waardes van de BOOK table te pakken.
    public List<Book> getAllBooks()
    {
        List<Book> boekenLijst;
        boekenLijst = bookRepository.findAll();
        return boekenLijst;
    }


    //Hier komt de body die we in de POST hebben gestuurd, samen met de ISBN nummer die uit de URL kwam.
    //Eerst voegen we die ISBN toe aan het nieuwe book object, dan slaan we die op in de database.
    public Book newBook(Book newBook, long ISBN)
    {
        newBook.setISBN(ISBN);
        return bookRepository.save(newBook);
    }

    //wanneer we meerdere boeken sturen, kan er een author mee worden gestuurd
    //Indien dat zo is, moeten we kijken of de author al bestaat
    public void multiNewBook(List<Book> books)
    {
       books.stream().forEach(c -> {
           //Sturen we een author mee?
           if(c.getAuthor() != null)
           {
               //Als we er eentje meesturen dan zoeken we of er een bestaat met hetzelfde naam
               if(authorRepository.findByName(c.getAuthor().getName()) != null)
               {
                   //Als die bestaat zeggen we dat de author die we meesturen gelijk is aan de author die we hebben gevonden.
                   c.setAuthor(authorRepository.findByName(c.getAuthor().getName()));
               }
           }
           //Nu slaan we het boek op
           bookRepository.save(c);
       });
    }

    //Hier zoeken we voor een Book in onze database met het ISBN nummer die we doorstuurde vanuit de URL.
    public void deleteBook(long ISBN)
    {
        bookRepository.deleteById(ISBN);
    }

    //Deze is wat lastiger om te lezen, maar in principe simpel
    public Author editAuthor(int authorID, Author editAuthor)
    {
        //Hier zeggen we stuur iets terug, namelijk zoek een author met een ID
        return authorRepository.findById(authorID)
                //Kan je hem vinden? dan doen we een .map, dit overschrijft de author die we vonden
                .map(
                author -> { //Voor een functie body { } uit en ik verwacht een author terug.
                    //setters van de author die is opgeslagen, die nu de waarde krijgen van de BODY die we in het PUT hadden geplaatst.
                    author.setName(editAuthor.getName());
                    author.setEmail(editAuthor.getEmail());
                    author.setGender(editAuthor.getGender());
                    //Gegevens veranderd? dan slaan we hem op.
                    return authorRepository.save(author);
                }).orElseGet(() -> { //Kan je hem niet vinden? Dan slaan we in dit geval gewoon een nieuwe op.
                    editAuthor.setAuthorID(authorID);
                    return authorRepository.save(editAuthor);
                });
    }

    //Hier slaan we de body die we in de POST request in het begin hadden doorgestuurd op in onze database.
    public Author newAuthor(Author newAuthor, int authorID)
    {

        newAuthor.setAuthorID(authorID);
        return authorRepository.save(newAuthor);
    }

    //Barebones niet getest nog, maar als de author niet bestaat slaan we het op, anders niet
    public void multiNewAuthor(List<Author> authors)
    {

        authors.stream().forEach(c -> {
            if(authorRepository.findByName(c.getName()) == null)
            {
                authorRepository.save(c);
            }

        });
    }



}
