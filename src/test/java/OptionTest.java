import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class OptionTest {

	public static final Option<Object> NONE = Option.unit(null);

	@Test
	public void isEmpty_none_true() {
		Assert.assertTrue(NONE.isEmpty());
	}

	@Test
	public void isEmpty_some_false() {
		Assert.assertFalse(Option.unit("some").isEmpty());
	}

	@Test
	public void getOrElse_none_elseValue() {
		Assert.assertEquals("else", NONE.getOrElse("else"));
	}

	@Test
	public void getOrElse_some_someValue() {
		Assert.assertEquals("some", Option.unit("some").getOrElse("else"));
	}

	@Test
	public void bind_noneAndNone_none() {
		Option<String> noneStr = Option.unit((String) null);
		Function<String, Option<Integer>> f = (String s) -> Option.unit(null);
		Option<Integer> optInt = noneStr.bind(f);
		Assert.assertTrue(optInt.isEmpty());
	}

	@Test
	public void bind_noneAndSome_none() {
		Option<String> noneStr = Option.unit((String) null);
		Function<String, Option<Integer>> f = (String s) -> Option.unit(s.length());
		Option<Integer> optInt = noneStr.bind(f);
		Assert.assertTrue(optInt.isEmpty());
	}

	@Test
	public void bind_someAndNone_none() {
		Option<String> noneStr = Option.unit("some");
		Function<String, Option<Integer>> f = (String s) -> Option.unit(null);
		Option<Integer> optInt = noneStr.bind(f);
		Assert.assertTrue(optInt.isEmpty());
	}

	@Test
	public void bind_someAndSome_Some() {
		Option<String> noneStr = Option.unit("some");
		Function<String, Option<Integer>> f = (String s) -> Option.unit(s.length());
		Option<Integer> optInt = noneStr.bind(f);
		Assert.assertFalse(optInt.isEmpty());
		Assert.assertEquals(4, (int) optInt.getOrElse(0));
	}

}
