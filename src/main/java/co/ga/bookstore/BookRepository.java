package co.ga.bookstore;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Long> {

    @RestResource(path = "byTitle")
    @ApiOperation("find all books that contain the string q in their title, ignoring case")
    List<Book> findByTitleIgnoreCaseContaining(@Param("q") String q);

    @RestResource(path = "deleteByTitle")
    @ApiOperation("delete book by title")
    Long deleteBookByTitle(@Param("title") String title);

    @RestResource(path="/findByPrefix")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied",
                    responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Boolean.class)),
            @ApiResponse(code = 404, message = "Pet not found") })
    Book findBookByTitleStartingWith(@Param("query") String prefix);



}

