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

	public OptionLawsTest(String s) {
		this.x = s;
		this.m = Option.unit(s);
	}

	@Test
	public void test1() {
		Assert.assertEquals(m, m.bind(Option::unit));
	}

}
