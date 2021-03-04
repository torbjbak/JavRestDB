package fullstack.oving2;

import fullstack.oving2.model.Author;
import fullstack.oving2.repo.AuthorRepo;
import fullstack.oving2.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService service;

    @Mock
    private AuthorRepo repo;

    @BeforeEach
    public void setUp() {
        Author author =
                new Author("Test", "Testson", null, null);

        Mockito.lenient().when(repo.findById(1L))
                .thenReturn(Optional.of(author));
        lenient().when(repo.save(author))
                .thenReturn(null);
    }

    @Test
    void create() {
        try {
            repo.save(new Author("", "", null, null));
        } catch (IllegalArgumentException iae) {
            System.out.println("We should never have reached this point.");
            System.out.println("Did we somehow call the real method instead of the mocked one?");
            fail();
        }
    }

    @Test
    void getById() {
        Author author = service.getAuthor(1L);

        assertThat(author.getPersName()).isEqualTo("Test");
        assertThat(author.getFamName()).isEqualTo("Testson");
    }
}
