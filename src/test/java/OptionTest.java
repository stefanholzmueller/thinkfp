import org.junit.Assert;
import org.junit.Test;

public class OptionTest {

	@Test
	public void getOrElse_null_elseValue() {
		Assert.assertEquals("else", Option.unit(null).getOrElse("else"));
	}

	@Test
	public void getOrElse_some_someValue() {
		Assert.assertEquals("some", Option.unit("some").getOrElse("else"));
	}


}
