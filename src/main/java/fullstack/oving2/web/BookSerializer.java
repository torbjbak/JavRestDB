package fullstack.oving2.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fullstack.oving2.model.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookSerializer extends StdSerializer<Set<Book>> {


    protected BookSerializer(Class<Set<Book>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<Book> books, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {

        List<Long> ids = new ArrayList<>();
        for (Book b : books) {
            ids.add(b.getId());
        }
        jsonGenerator.writeObject(ids);
    }
}
