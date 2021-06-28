package com.meli.challenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.meli.challenge.entity.DnaVerified;
import com.meli.challenge.repository.DnaVerifiedRepository;
import com.meli.challenge.service.IMutantService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class MutantServiceTest {

    private IMutantService mutantService;

    @Mock
    private DnaVerifiedRepository dnaVerifiedRepository;

    @BeforeEach
    void setUp(){
        mutantService = new MutantService(dnaVerifiedRepository);
        ReflectionTestUtils.setField(mutantService, "dnaMutantValid", "AAAA,CCCC,TTTT,GGGG");
    }

    @Test
    void given_listValidDnaInputHorizontally_thenReturnsTrue(){
        String[] dnaEntry = {"TTTTAA", "CAGTAC", "TTATGT", "ATAATG", "CTACTA", "TCCCTG"};
        assertTrue(mutantService.isMutant(dnaEntry));
    }

    @Test
    void given_listValidDnaInputVertically_thenReturnsTrue(){
        String[] dnaEntry = {"CTTTAA", "CAGTAC", "CTATGT", "CTAATG", "CTACTA", "TCCCTG"};
        assertTrue(mutantService.isMutant(dnaEntry));
    }


    @Test
    void given_listInvalidDnaInputRunsThrougAllOptions_thenReturnsFalse(){
        String[] dnaEntry = {"TTGCAA", "CAGTAC", "TTATGT", "ATAATG", "CTACTA", "TCCCTG"};
        assertFalse(mutantService.isMutant(dnaEntry));
    }

    @Test
    void given_listValidDnaInputTraversesDiagonals_thenreturnTrue(){
        String[] dnaEntry = {"ATGCAA", "CAGTAC", "TTATGT", "ATTATG", "CTACTA", "TCCCTG"};
        assertTrue(mutantService.isMutant(dnaEntry));
    }

    @Test
    void given_dnaValidAndNotExistsBd_thenSaveInBD (){
        DnaVerified dnaVerified = DnaVerified.builder().dna("AAAA").id(1L).build();
        when(dnaVerifiedRepository.findDna(any())).thenReturn(Optional.empty());
        when(dnaVerifiedRepository.save(any())).thenReturn(dnaVerified);
        mutantService.saveValidDna("CTTTAA,CAGTAC,CTATGT,CTAATG,CTACTA,TCCCTG");
        verify(dnaVerifiedRepository, times(1)).save(any());
    }

    @Test
    void given_dnaValidAndExistsBd_thenNotSaveInBD (){
        when(dnaVerifiedRepository.findDna(any())).thenReturn(Optional.of(new DnaVerified()));
        mutantService.saveValidDna("CTTTAA,CAGTAC,CTATGT,CTAATG,CTACTA,TCCCTG");
        verify(dnaVerifiedRepository, times(1)).findDna(any());
    }

}