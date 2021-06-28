package com.meli.challenge.delegate;

import org.springframework.http.ResponseEntity;

/**
 * IStatsDelegate interface that exposes the contract for statistics
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
public interface IStatsDelegate {

  ResponseEntity getStats();

}
