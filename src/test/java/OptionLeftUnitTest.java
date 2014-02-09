import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

@RunWith(Parameterized.class)
public class OptionLeftUnitTest {

	private final String x;
	private final Integer fx;
	private final Function<String, Option<Integer>> f;

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{null, null}, {null, 1}, {"s", null}, {"s", 1}
		});
	}

	public OptionLeftUnitTest(String x, Integer fx) {
		this.x = x;
		this.fx = fx;
		this.f = new Function<String, Option<Integer>>() {
			@Override
			public Option<Integer> apply(String s) {
				if (x == null) return Option.unit(null);
				return Option.unit(fx);
			}
		};
	}

	@Test
	public void test1() {
		Assert.assertEquals("x=" + x + ", fx=" + fx, f.apply(x), Option.unit(x).bind(f));
	}

	@Test
	public void test2() {
		Assert.assertEquals("x=" + x + ", fx=" + fx, f.apply(x), Option.unit(x).bind(f));
	}
}
