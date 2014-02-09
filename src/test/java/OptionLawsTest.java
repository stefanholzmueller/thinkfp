import junit.framework.Assert;
import org.junit.Test;

import java.util.function.Function;

import static junit.framework.Assert.assertEquals;

public class OptionLawsTest {

	private String x = "test";
	private Option<String> m = Option.unit(x);

	@Test
	public void associativity() {
		Function<String, Option<String>> f = s -> Option.unit(s.toUpperCase());
		Function<String, Option<String>> g = s -> Option.unit(s + " " + s);
		assertEquals(m.bind(f).bind(g), m.bind((String x) -> f.apply(x).bind(g)));
	}

	@Test
	public void leftUnit() {
		Function<String, Option<Integer>> f = s -> Option.unit(s.length());
		assertEquals(f.apply(x), Option.unit(x).bind(f));
	}

	@Test
	public void rightUnit() {
		assertEquals(m, m.bind(Option::unit));
	}

}
