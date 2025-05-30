package com.travelvn.tourbookingsytem.repository;

import com.travelvn.tourbookingsytem.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
