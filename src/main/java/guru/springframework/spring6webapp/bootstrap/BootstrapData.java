package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        Author rod = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("978-0321125217");
        Book noEJB = new Book();
        ddd.setTitle("J2EE Development without EJB");
        ddd.setIsbn("978-0764558313");

        Publisher addisonWesley = new Publisher();
        addisonWesley.setPublisherName("Addison-Wesley Professional");
        addisonWesley.setAddress("123 Main");
        addisonWesley.setCity("Boston");
        addisonWesley.setProvince("Massachusetts");
        addisonWesley.setPostalCode("9410");

        // good practice to assign the object returned by .save()
        Author ericSaved = authorRepository.save(eric);
        Author rodSaved = authorRepository.save(rod);
        Book dddSaved = bookRepository.save(ddd);
        Book noEJBSaved = bookRepository.save(noEJB);
        Publisher addisonSaved = publisherRepository.save(addisonWesley);

        ddd.setPublisher(addisonSaved);
        noEJB.setPublisher(addisonSaved);
        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(ddd);
        bookRepository.save(noEJBSaved);


        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }


}
