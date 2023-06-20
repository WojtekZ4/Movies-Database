package project.movies.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import project.movies.models.Movie;
import project.movies.models.Person;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, String> {
    @Query("MATCH (m:Movie) RETURN m")
    Set<Movie> getAllMovies();

    Movie findOneByTitle(String title);

    @Query("""
            MATCH (a:Person {name: name})-[r:ACTED_IN]->(m:Movie)
            RETURN m""")
    Set<Movie> getActorsMovies(String name);

    @Query("""
            MATCH (d:Person {name: name})-[r:DIRECTED]->(m:Movie)
            RETURN m""")
    Set<Movie> getDirectorsMovies(String name);
}
