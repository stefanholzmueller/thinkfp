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
	public void leftUnit() {
		Function<String, Option<Integer>> f = s -> {
			if (s == null) return Option.unit(null);
			return Option.unit(s.length());
		};
		
		Assert.assertEquals(f.apply(x), Option.unit(x).bind(f));
	}

	@Test
	public void rightUnit() {
		Assert.assertEquals(m, m.bind(Option::unit));
	}

}
