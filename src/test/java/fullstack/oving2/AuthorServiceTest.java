package fullstack.oving2;

import fullstack.oving2.repo.AuthorRepo;
import fullstack.oving2.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService service;

    @Mock
    private AuthorRepo repo;

    /*@BeforeEach
    public void setUp() {
        Meme neededForCreate = new Meme("fake memeText", "fake memePic");

        Mockito.lenient().when(repo.saySomething()).thenReturn(neededForCreate);
        lenient().doNothing().when(repo).create(neededForCreate);
    }

    @Test
    void create() {
        try {
            repo.create(new Meme("", ""));
        } catch (IllegalArgumentException iae) {
            System.out.println("We should never have reached this point.");
            System.out.println("Did we somehow call the real method in stead of the mocked one?");
            fail();
        }
    }

    @Test
    void lesson4Message() {
        List<Meme> memes = service.lesson4Message();

        assertThat(memes.get(0).getMemeText()).isEqualTo("fake memeText");
        assertThat(memes.get(0).getPic()).isEqualTo("fake memePic");
    }*/
}
