import junit.framework.Assert;
import org.junit.Test;

import java.util.function.Function;

import static junit.framework.Assert.assertEquals;

public class OptionLawsTest {

	@Test
	public void associativity() {
		Option<String> m = Option.unit("s");
		Function<String, Option<String>> f = s -> Option.unit(s.toUpperCase());
		Function<String, Option<String>> g = s -> Option.unit(s + " " + s);
		assertEquals(m.bind(f).bind(g), m.bind((String x) -> f.apply(x).bind(g)));
	}

	@Test
	public void leftUnit() {
		String x = "s";
		Function<String, Option<Integer>> f = s -> Option.unit(s.length());
		assertEquals(f.apply(x), Option.unit(x).bind(f));
	}

	@Test
	public void rightUnit() {
		Option<String> m = Option.unit("s");
		assertEquals(m, m.bind(Option::unit));
	}

}
