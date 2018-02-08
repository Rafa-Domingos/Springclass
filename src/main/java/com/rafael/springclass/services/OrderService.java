package com.rafael.springclass.services;

import com.rafael.springclass.domain.PurchaseOrder;
import com.rafael.springclass.repositories.OrderRepository;
import com.rafael.springclass.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    /**
     * {@link OrderRepository} injection.
     */
    @Autowired
    private OrderRepository orderRepository;

    /**
     * Get a {@link PurchaseOrder} by its {@link PurchaseOrder#id}.
     *
     * @param id {@link PurchaseOrder#id} from the {@link PurchaseOrder} that request want to recovery.
     *
     * @return {@link PurchaseOrder} where {@link PurchaseOrder#id} is equals {@param id}
     */
    public PurchaseOrder getById(final Integer id) {
        final PurchaseOrder order = this.orderRepository.findOne(id);

        if (order == null) {
            throw new ObjectNotFoundException("Objeto não encontrado. Id = " + id + ", tipo = " + PurchaseOrder.class.getName());
        }

        return order;
    }
}
