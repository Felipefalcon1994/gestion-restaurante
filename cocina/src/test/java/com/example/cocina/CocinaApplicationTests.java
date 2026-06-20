package com.example.cocina;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled("Requiere conexión activa a MySQL — ejecutar solo con Docker corriendo")
@SpringBootTest
class CocinaApplicationTests {

	@Test
	void contextLoads() {
	}

}
