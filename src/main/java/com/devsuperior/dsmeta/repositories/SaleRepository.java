package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT obj FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate "
			+ "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%',:name,'%'))")
	Page<Sale> searchReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(MAX(s.seller.name),SUM(s.amount))" +
		       "FROM Sale s " +
		       "WHERE s.date BETWEEN :minDate AND :maxDate " +
		       "GROUP BY s.seller " + 
			   "ORDER BY s.seller.name ")
	List<SaleSummaryDTO> searchSummary(LocalDate minDate, LocalDate maxDate);
}
