// 21912083 김대영
package yu.spring.chap5.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByNameAndEmail(String name, String email);
    List<Member> findByName(String name);
    List<Member> findByEmail(String email);
    List<Member> findByNameStartingWith(String firstname);
    List<Member> findByEmailContaining(String site);

 //   List<SummaryDTO1> findSummaryDTO1ByEmailContaining(String site);
    List<SummaryRecord1> findSummaryDTO1ByEmailContaining(String site);

    @Query("SELECT new yu.spring.chap5.model.SummaryRecord2(m.id, m.name, a.id, a.title) " +
            "FROM Article a JOIN a.writer m")
    List<SummaryRecord2> findSummary2_JPQL();

    @Query(value =
            "SELECT m.id, m.name, a.article_no, a.title " +
            "FROM member m " +
            "INNER JOIN article a ON m.id = a.writer",
            nativeQuery = true)
    List<SummaryRecord2> findSummary2_Native();

    @Query("SELECT new yu.spring.chap5.model.SummaryRecord3(m.id, m.name, COUNT(a)) " +
            "FROM Member m LEFT JOIN Article a ON m.id = a.writer.id " +
            "GROUP BY m.id, m.name")
    List<SummaryRecord3> findSummary3_JPQL();

    @Query(value =
            "SELECT m.id, m.name, COUNT(a.article_no) " +
            "FROM member m LEFT JOIN article a ON m.id = a.writer " +
            "GROUP BY m.id, m.name",
            nativeQuery = true)
    List<SummaryRecord3> findSummary3_Native();
}