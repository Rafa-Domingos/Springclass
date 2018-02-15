package com.rafael.springclass;

import com.rafael.springclass.domain.*;
import com.rafael.springclass.domain.enums.CustomerType;
import com.rafael.springclass.domain.enums.PaymentStatus;
import com.rafael.springclass.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpringclassApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringclassApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
	    final Category category1 = new Category("Computing");
        final Category category2 = new Category("Office");
        final Category category3 = new Category("Cat3");
        final Category category4 = new Category("Cat4");
        final Category category5 = new Category("Cat6");
        final Category category6 = new Category("Cat7");
        final Category category7 = new Category("Cat8");
        final Category category8 = new Category("Cat9");

        final Product product1 = new Product("Laptop", new BigDecimal("100.00"));
        final Product product2 = new Product("Mouse", new BigDecimal("200.00"));
        final Product product3 = new Product("Pen", new BigDecimal("300.00"));

        category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
        category2.getProducts().add(product2);

        product1.getCategories().add(category1);
        product1.getCategories().addAll(Arrays.asList(category1, category2));
        product3.getCategories().add(category1);

		this.categoryRepository.save(Arrays.asList(category1, category2, category3, category4, category5, category6, category7, category8));
		this.productRepository.save(Arrays.asList(product1, product2, product3));

		final State state1 = new State("Minas Gerais");
        final State state2 = new State("São Paulo");

        final City city1 = new City("Uberlândia", state1);
        final City city2 = new City("São Paulo", state2);
        final City city3 = new City("Campinas", state2);

        state1.getCities().add(city1);
        state2.getCities().addAll(Arrays.asList(city2, city3));

        this.stateRepository.save(Arrays.asList(state1, state2));
        this.cityRepository.save(Arrays.asList(city1, city2, city3));

        final Customer customer1 = new Customer("Maria", "maria@gmail.com", "3661666188", CustomerType.NATURAL);
        customer1.getPhoneNumbers().addAll(Arrays.asList("26754432", "943760008"));

        final Address address1 = new Address("Rua Flores", "300", "Apto 2", "Jardim", "08765-321", customer1, city1);
        final Address address2 = new Address("Av. Matos", "99", null, "Centro", "07532-921", customer1, city2);

        customer1.getAddresses().addAll(Arrays.asList(address1, address2));

        this.customerRepository.save(customer1);
        this.addressRepository.save(Arrays.asList(address1, address2));

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        final PurchaseOrder purchaseOrder1 = new PurchaseOrder(sdf.parse("30/09/2017 12:30"), customer1, address1);
        final PurchaseOrder purchaseOrder2 = new PurchaseOrder(sdf.parse("10/10/2017 12:30"), customer1, address2);

        final Payment payment1 = new CardPayment(PaymentStatus.SETTLED, purchaseOrder1, 6);
        purchaseOrder1.setPayment(payment1);

        final Payment payment2 = new TicketPayment(PaymentStatus.PENDING, purchaseOrder2, sdf.parse("20/10/2017 00:00"), null);
        purchaseOrder2.setPayment(payment2);

        this.orderRepository.save(Arrays.asList(purchaseOrder1, purchaseOrder2));
        this.paymentRepository.save(Arrays.asList(payment1, payment2));

        final OrderItem orderItem1 = new OrderItem(purchaseOrder1, product1, BigDecimal.ZERO, 1, new BigDecimal(1000));
        final OrderItem orderItem2 = new OrderItem(purchaseOrder1, product3, BigDecimal.ZERO, 2, new BigDecimal(80));
        final OrderItem orderItem3 = new OrderItem(purchaseOrder2, product2, new BigDecimal(100), 1, new BigDecimal(800));

        purchaseOrder1.getItems().addAll(Arrays.asList(orderItem1, orderItem2));
        purchaseOrder2.getItems().add(orderItem3);

        product1.getItems().add(orderItem1);
        product2.getItems().add(orderItem3);
        product3.getItems().add(orderItem2);

        orderItemRepository.save(Arrays.asList(orderItem1, orderItem2, orderItem3));
	}
}
