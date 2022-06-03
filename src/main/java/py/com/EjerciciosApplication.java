package py.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import py.com.entities.Laptop;
import py.com.repository.LaptopRepository;

@SpringBootApplication
public class EjerciciosApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(EjerciciosApplication.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		Laptop l1 = new Laptop(null,"Acer","e5555",1500000D);
		Laptop l2 = new Laptop(null,"HP","e5555",1500000D);
		laptopRepository.save(l1);
		laptopRepository.save(l2);
	}

}
