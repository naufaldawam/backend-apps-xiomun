package co.id.xiaomun.serviceappsxiaomun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.xiaomun.serviceappsxiaomun.entity.menu.Offering;

@Repository
public interface OfferingRepository extends JpaRepository<Offering, Long> {

}
