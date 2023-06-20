package project.movies.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import project.movies.models.Movie;
import project.movies.models.Person;

import java.util.List;
import java.util.Set;

public interface PersonRepository extends Neo4jRepository<Person, String> {
    @Query("MATCH (p:Person) RETURN p")
    Set<Person> getAllPeople();

    Person findOneByName(String name);

    @Query("""
            MATCH (a:Person {name: $name}), (m:Movie {title: $title})
            MERGE (a)-[r:ACTED_IN]->(m)""")
    void addActedInRelation(String name, String title);

    @Query("""
            MATCH (d:Person {name: $name}), (m:Movie {title: $title})
            MERGE (d)-[r:DIRECTED]->(m)""")
    void addDirectedRelation(String name, String title);

    @Query("""
            MATCH (a:Person {name: $name})-[r:ACTED_IN]->(m:Movie {title: $title})
            DELETE r""")
    void deleteActedInRelation(String name, String title);

    @Query("""
            MATCH (d:Person {name: $name})-[r:DIRECTED]->(m:Movie {title: $title})
            DELETE r""")
    void deleteDirectedRelation(String name, String title);

    @Query("""
            MATCH (a:Person)-[r:ACTED_IN]->(m:Movie {title: $title})
            RETURN a""")
    Set<Person> getMovieActors(String title);

    @Query("""
            MATCH (d:Person)-[r:DIRECTED]->(m:Movie {title: $title})
            RETURN d""")
    Set<Person> getMovieDirectors(String title);


    @Query("""
            MATCH (a1:Person {name: name})-[r:ACTED_IN]->(m:Movie),
                  (a2:Person)-[r:ACTED_IN]->(m:Movie),
            WHERE a1 != a2
            RETURN a2""")
    Set<Person> getCoActors(String name);
}
