package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleSellerMinDTO> searchReport(String minDateString, String maxDateString, String name, Pageable pageable) {
		LocalDate maxDate = !minDateString.equals("") ? LocalDate.parse(maxDateString) : LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minDate = !minDateString.equals("") ? LocalDate.parse(minDateString) : maxDate.minusYears(1L);
		Page<Sale> pages = repository.searchReport(minDate,maxDate,name, pageable);
		return pages.map(x -> new SaleSellerMinDTO(x));
	}

	public List<SaleSummaryDTO> searchSummary(String minDateString, String maxDateString) {
		LocalDate maxDate = !minDateString.equals("") ? LocalDate.parse(maxDateString) : LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minDate = !minDateString.equals("") ? LocalDate.parse(minDateString) : maxDate.minusYears(1L);
		return repository.searchSummary(minDate, maxDate);
	}
}
