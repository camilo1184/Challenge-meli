package com.meli.challenge.repository;

import com.meli.challenge.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * StatsRepository performs operations on the DnaVerified entity Stats
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
public interface StatsRepository extends JpaRepository<Stats, Long> {

  @Modifying
  @Query("update Stats sta SET sta.isMutant = sta.isMutant + :isMutant, sta.notMutant =  sta.notMutant + :notMutant")
  void saveStatics(@Param("isMutant") long isMutant, @Param("notMutant") long notMutant);
}
