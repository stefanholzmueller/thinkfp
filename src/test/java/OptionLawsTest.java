import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

@RunWith(Parameterized.class)
public class OptionLawsTest {

	private final String x;
	private final Option<String> m;

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{null}, {"s"}
		});
	}

	public OptionLawsTest(String string) {
		this.x = string;
		this.m = Option.unit(string);
	}

	@Test
	public void associativity() {
		Function<String, Option<String>> f = nullSafeBindable(String::toUpperCase);
		Function<String, Option<String>> g = nullSafeBindable(s -> s + s);

		Assert.assertEquals(m.bind(f).bind(g), m.bind((String x) -> f.apply(x).bind(g)));
	}

	@Test
	public void leftUnit() {
		Function<String, Option<Integer>> f = nullSafeBindable(String::length);
		
		Assert.assertEquals(f.apply(x), Option.unit(x).bind(f));
	}

	@Test
	public void rightUnit() {
		Assert.assertEquals(m, m.bind(Option::unit));
	}

	private <R> Function<String, Option<R>> nullSafeBindable(Function<String, R> f) {
		return s -> {
			if (s == null) return Option.unit(null);
			return Option.unit(f.apply(s));
		};
	}

}
