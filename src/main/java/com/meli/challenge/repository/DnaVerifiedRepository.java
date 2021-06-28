package com.meli.challenge.repository;

import com.meli.challenge.entity.DnaVerified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/**
 * DnaVerifiedRepository performs operations on the DnaVerified entity DnaVerified
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
public interface DnaVerifiedRepository extends JpaRepository<DnaVerified, Long> {

  DnaVerified save(DnaVerified person);

  @Query("select dnaVerified from DnaVerified dnaVerified where dnaVerified.dna = :dnaInput")
  Optional<DnaVerified> findDna(@Param("dnaInput") String dnaInput);

}
