import junit.framework.Assert;
import org.junit.Test;

import java.util.function.Function;

public class OptionLawsTest {

	@Test
	public void associativity() {
		Option<String> m = Option.unit("s");
		Function<String, Option<String>> f = s -> Option.unit(s.toUpperCase());
		Function<String, Option<String>> g = s -> Option.unit(s + s);
		Assert.assertEquals(m.bind(f).bind(g), m.bind((String x) -> f.apply(x).bind(g)));
	}

	@Test
	public void leftUnit() {
		String x = "s";
		Function<String, Option<Integer>> f = s -> Option.unit(s.length());
		Assert.assertEquals(f.apply(x), Option.unit(x).bind(f));
	}

	@Test
	public void rightUnit() {
		Option<String> m = Option.unit("s");
		Assert.assertEquals(m, m.bind(Option::unit));
	}

}
