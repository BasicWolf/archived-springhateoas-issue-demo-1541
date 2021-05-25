package org.example.demo;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class BookModelAssmbler
        extends RepresentationModelAssemblerSupport<BookEntity, BookDto> {


    public BookModelAssmbler() {
        super(BookController.class, BookDto.class);
    }

    @Override
    public BookDto toModel(BookEntity entity) {
        return new BookDto(entity.getName());
    }
}
