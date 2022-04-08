package com.example.huiswerkspring1.controller;

import com.example.huiswerkspring1.models.Author;
import com.example.huiswerkspring1.models.Book;
import com.example.huiswerkspring1.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*Aangezien Spring boot met MVC werkt hebben we controllers nodig.
* Wij duiden hier aan dat dit een controller is, namelijk een REST controller
* Wat is rest? https://www.youtube.com/watch?v=lsMQRaeKNDk
* Spring boot basic REST https://spring.io/guides/gs/rest-service/

* Annotations worden gebruikt door spring boot om bepaalde rechten te geven aan objects/classes/etc.
* Handige annotations: https://www.upgrad.com/blog/spring-boot-annotations/
* Uitleg annotations deze bestand:
* @RestController: Dit is een specifiekere variant van @Controller, het geeft dan ook dezelfde rechten als @Controller,
*   en voegt meteen @ResponseBody toe, waardoor het implementer van de Controller simpler is.
*   https://www.baeldung.com/spring-controller-vs-restcontroller
*
* @Autowired: Dit zorgt ervoor dat dependencies (benodigdheden) automatisch worden "Geinjecteerd"
*   Hierdoor kan je automatisch relaties tussen verschillende "Beans" leggen.
*   https://www.baeldung.com/spring-autowire
*
* @|...|Mapping("/url"): Een mapping vertelt in feite, op deze endpoint kan ik een request krijgen, als ik die krijg voer dan deze methode uit.
*   Er zijn meerdere mappings beschikbaar, namelijk:
*    @GetMapping
*    @PostMapping
*    @PutMapping
*    @DeleteMapping
*    @PatchMapping
*   https://www.baeldung.com/spring-new-requestmapping-shortcuts
*
* @PathVariable: Dit geeft aan dat er in de URL die we opvangen iets komt, waar we niet zeker van weten, maar wel nodig hebben.
*   In "Book/{id} is {id} de @PathVariable, wij kunnen deze hierdoor opvangen, in bijvoorbeeld een int.
*   https://www.baeldung.com/spring-pathvariable
*       Een soort gelijk variant hiervan is de @RequestParam
*       https://www.baeldung.com/spring-requestparam-vs-pathvariable
*
*   Een bean is overigens niet iets compleet duidelijks, je zou het kunnen zien als een soort object.
*   https://www.baeldung.com/spring-bean
 */

@RestController
public class AppController {

    //We hebben een Service class nodig, die hebben we atm een appService genoemd.
    //Met een @Autowired, legt spring automatisch voor ons de relatie.

    @Autowired
    AppService appService;

    //Een voorbeel van de endpoints voor iedere soort is hier verwerkt.
    //Wij zeggen nu als er op de url (in mijn geval) "localhost:8080/books" een GET request wordt gedaan
    //Dan wil ik dat deze functie wordt uitgevoerd
    @GetMapping("/books")
    private List<Book> getAllBooks()
    {
        return appService.getAllBooks();
    }

    //Hier zorgen wij dat we een POST request kunnen afhandelen
    //de {id} geeft aan dat de link een variabel iets kan bevatten.
    //in dit geval zal het een nummer zijn, omdat wij met de @PathVariable een int verwachten.
    //De @RequestBody probeert om de body op te vangen die we meesturen in een Book object.
    //Deze body kan bijvoorbeeld uit JSON bestaan

    /* VOORBEELD BODY (JSON)
        {
            "name": "Het diner",
            "price": "4.99",
            "qty_in_stock": "3"
        }
     */
    @PostMapping("book/{id}")
    private void newBook(@RequestBody Book newBook, @PathVariable("id") int ISBN)
    {
        appService.newBook(newBook, ISBN);
    }

    @DeleteMapping("/books/{ISBN}")
    private void deleteBook(@PathVariable("ISBN") int ISBN)
    {
        appService.deleteBook(ISBN);
    }

    @PutMapping("/author/{authorID}")
    private void editAuthor(@RequestBody Author editAuthor, @PathVariable("authorID") int authorID)
    {
        appService.editAuthor(authorID, editAuthor);
    }
    @PostMapping("/author/{authorID}")
    private void newAuthor(@RequestBody Author newAuthor, @PathVariable("authorID") int authorID)
    {
        appService.newAuthor(newAuthor, authorID);
    }

    /* Deze end-points missen nog van het huiswerk! Probeer deze zelf!

    /authors        GET
    /author/{id}    GET
    /book/{id}      GET
    /author/{id}    DELETE
    /book/{id}      PUT

     */




}
