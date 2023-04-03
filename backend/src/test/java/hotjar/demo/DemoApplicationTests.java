package hotjar.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest
class DemoApplicationTests {

	Calc underTest = new Calc();

	@Test
	void itAddsNums() {
		// given
		int i = 1;
		int j = 4;
		int x = 5;
		int y = 10;

		// when
		int sum = underTest.add(underTest.add(i, j), underTest.add(x, y));

		//
		assertThat(sum).isEqualTo(20);
	}

	@Test
	void itMultiplies() {
		int x = 5;
		int y = 10;
		
		// when
		int sum  = underTest.multiply(x, y);

		// then
		assertThat(sum).isEqualTo(50);
	}

	class Calc {
		public int add(int a, int b) {
			return a + b;
		}

		public int multiply(int a, int b) {
			return a * b;
		}
	}

}
