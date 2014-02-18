import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OptionTest {

	public static final Option<Object> NONE = Option.unit(null);

	@Test
	public void isEmpty_none_true() {
		assertTrue(NONE.isEmpty());
	}

	@Test
	public void isEmpty_some_false() {
		assertFalse(Option.unit("some").isEmpty());
	}

	@Test
	public void getOrElse_none_elseValue() {
		assertEquals("else", NONE.getOrElse("else"));
	}

	@Test
	public void getOrElse_some_someValue() {
		assertEquals("some", Option.unit("some").getOrElse("else"));
	}

	@Test
	public void bind_noneAndNone_none() {
		Option<String> noneStr = Option.unit((String) null);
		Function<String, Option<Integer>> f = (String s) -> Option.unit(null);
		Option<Integer> optInt = noneStr.bind(f);
		assertTrue(optInt.isEmpty());
	}

	@Test
	public void bind_noneAndSome_none() {
		Option<String> noneStr = Option.unit((String) null);
		Function<String, Option<Integer>> f = (String s) -> Option.unit(s.length());
		Option<Integer> optInt = noneStr.bind(f);
		assertTrue(optInt.isEmpty());
	}

	@Test
	public void bind_someAndNone_none() {
		Option<String> noneStr = Option.unit("some");
		Function<String, Option<Integer>> f = (String s) -> Option.unit(null);
		Option<Integer> optInt = noneStr.bind(f);
		assertTrue(optInt.isEmpty());
	}

	@Test
	public void bind_someAndSome_Some() {
		Option<String> noneStr = Option.unit("some");
		Function<String, Option<Integer>> f = (String s) -> Option.unit(s.length());
		Option<Integer> optInt = noneStr.bind(f);
		assertFalse(optInt.isEmpty());
		assertEquals(4, (int) optInt.getOrElse(0));
	}

	@Test
	public void map_noneAndNone_none() {
		Option<String> noneStr = Option.unit((String) null);
		Function<String, Integer> f = (String s) -> null;
		Option<Integer> optInt = noneStr.map(f);
		assertTrue(optInt.isEmpty());
	}

	@Test
	public void map_noneAndSome_none() {
		Option<String> noneStr = Option.unit((String) null);
		Function<String, Integer> f = (String s) -> s.length();
		Option<Integer> optInt = noneStr.map(f);
		assertTrue(optInt.isEmpty());
	}

	@Test
	public void map_someAndNone_none() {
		Option<String> noneStr = Option.unit("some");
		Function<String, Integer> f = (String s) -> null;
		Option<Integer> optInt = noneStr.map(f);
		assertTrue(optInt.isEmpty());
	}

	@Test
	public void map_someAndSome_Some() {
		Option<String> noneStr = Option.unit("some");
		Function<String, Integer> f = (String s) -> s.length();
		Option<Integer> optInt = noneStr.map(f);
		assertFalse(optInt.isEmpty());
		assertEquals(4, (int) optInt.getOrElse(0));
	}

}
