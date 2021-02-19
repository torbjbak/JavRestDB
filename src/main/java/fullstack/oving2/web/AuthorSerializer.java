package fullstack.oving2.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fullstack.oving2.model.Author;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthorSerializer extends StdSerializer<Set<Author>> {


    protected AuthorSerializer(Class<Set<Author>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<Author> authors, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        List<Long> ids = new ArrayList<>();
        for (Author a : authors) {
            ids.add(a.getId());
        }
        jsonGenerator.writeObject(ids);
    }
}