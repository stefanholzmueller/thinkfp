import java.util.ArrayList;
import java.util.function.Function;

public abstract class Option1<T> {

	private Option1() {}

	private static final class None<T> extends Option1<T> {}

	public static final Option1 NONE = new None();

	public static final class Some<T> extends Option1<T> {
		private final T value;

		public Some(T value) {
			this.value = value;
		}

		public T get() {
			return this.value;
		}
	}

	public static void main(String[] args) {
		Option1<String> optStr = new Some<String>("string");

		if (optStr == NONE) {
			System.out.println("there's nothing here");
		} else {
			Some<String> someStr = (Some) optStr;
			String string = someStr.get();
			System.out.println(string);
			new ArrayList<String>().stream().map(x -> x.length());
			new ArrayList<String>().stream().map(new Function<String, Object>() {
				@Override
				public Object apply(String s) {
					return null;
				}
			});
		}
	}

}