package org.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @GetMapping(path = "/book", produces= MimeType.BOOKER_v0_5)
    public BookDto getBook()  {
        var bookModelAssembler = new BookModelAssmbler();
        return bookModelAssembler.toModel(new BookEntity("Stranger in Strange Land"));
    }
}
