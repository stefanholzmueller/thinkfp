import org.junit.Test;

import java.util.function.Function;

import static junit.framework.Assert.assertEquals;

public class OptionLawsTest {

	private String s = " test ";
	private Option<String> m = Option.unit(s);

	@Test
	public void associativity() {
		Function<String, Option<String>> f = s -> Option.unit(s.trim());
		Function<String, Option<Integer>> g = s -> Option.unit(s.length());
		assertEquals(m.bind(f).bind(g), m.bind(x -> f.apply(x).bind(g)));
	}

	@Test
	public void leftIdentity() {
		Function<String, Option<Integer>> f = s -> Option.unit(s.length());
		assertEquals(f.apply(s), Option.unit(s).bind(f));
	}

	@Test
	public void rightIdentity() {
		assertEquals(m, m.bind(Option::unit));
	}

}
