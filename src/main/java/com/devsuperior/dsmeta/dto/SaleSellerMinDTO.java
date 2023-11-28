package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleSellerMinDTO {

	private Long id;
	private LocalDate date;
	private Double amount;
	private String sellerName;
	
	
	public SaleSellerMinDTO(Long id, Double amount, LocalDate date, String sellerName) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sellerName = sellerName;
	}
	
	public SaleSellerMinDTO(Sale entity) {
		this.id = entity.getId();
		this.amount = entity.getAmount();
		this.date = entity.getDate();
		this.sellerName = entity.getSeller().getName();
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSellerName() {
		return sellerName;
	}
	
	
}
