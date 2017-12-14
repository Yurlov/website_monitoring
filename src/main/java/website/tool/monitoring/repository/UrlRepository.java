package website.tool.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.tool.monitoring.domain.Url;
@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {
}
