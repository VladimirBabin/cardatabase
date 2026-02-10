package com.github.vladimirbabin.cardatabase;

import com.github.vladimirbabin.cardatabase.domain.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class CardatabaseApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) {
		Owner owner1 = new Owner("John" , "Johnson");
		Owner owner2 = new Owner("Mary" , "Robinson");
		ownerRepository.saveAll(Arrays.asList(owner1, owner2));

		carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, owner1));
		carRepository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2019, 29000, owner2));
		carRepository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2020, 39000, owner2));

		for (Car car : carRepository.findAll()) {
            logger.info("{} {} {}", car.getBrand(), car.getModel(), car.getOwner().getFirstname());
		}

//		carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, Set.of(owner1)));
//		carRepository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2019, 29000, Set.of(owner1, owner2)));
//		carRepository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2020, 39000, Set.of(owner2)));
//
//		for (Car car : carRepository.findAll()) {
//			logger.info("{} {} {}", car.getBrand(), car.getModel(), getOwnersNames(car));
//		}

		userRepository.saveAll(Arrays.asList(
				new User("user", "$2a$12$toLJxJ1.lYjSdV.Pxk6QQeB4gjkC/sZ/GnT6jcZDDbIzv1XMwtqwu", "USER"),
				new User("admin", "$2a$12$prqwI/ZK0EwMSaP0JkvOju7kcePdEIlCyhJBLVpgwthAJ/i7vfABS", "ADMIN")
		));
	}


//
//	private static String getOwnersNames(Car car) {
//		return car.getOwners().stream()
//				.map(Owner::getFirstname)
//				.collect(Collectors.joining(" "));
//	}

}
