package com.meli.challenge.controller;

import com.meli.challenge.constant.ResourceEndpoint;
import com.meli.challenge.delegate.IMutantDelegate;
import com.meli.challenge.dto.DnaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mutant Controller determines if a DNA strand is from a mutant or a human
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
@RestController
@RequestMapping(value = ResourceEndpoint.MUTANT)
@Slf4j
public class MutantController {

  IMutantDelegate mutantDelegate;

  @Autowired
  public MutantController(IMutantDelegate mutantDelegate) {
    this.mutantDelegate = mutantDelegate;
  }

  @PostMapping
  public ResponseEntity isMutant(@RequestBody DnaDto dnaInput) {
    log.info("Validate is mutant");
    return mutantDelegate.isMutant(dnaInput);
  }

}
