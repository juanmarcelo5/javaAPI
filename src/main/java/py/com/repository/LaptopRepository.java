package py.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.entities.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop,Long> {

}
