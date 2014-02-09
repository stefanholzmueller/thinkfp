import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.function.Function;

public abstract class Option<T> {

	public abstract <R> Option<R> bind(Function<T, Option<R>> f);
	public abstract boolean isEmpty();
	public abstract T getOrElse(T elseValue);

	private Option() {}

	private static final Option NONE = new None();
	private static final class None<T> extends Option<T> {

		@Override
		public <R> Option<R> bind(Function<T, Option<R>> f) {
			return NONE;
		}
		@Override
		public boolean isEmpty() {
			return true;
		}
		@Override
		public T getOrElse(T elseValue) {
			return elseValue;
		}
	}

	private static final class Some<T> extends Option<T> {

		private final T value;
		public Some(T value) {
			this.value = value;
		}

		@Override
		public <R> Option<R> bind(Function<T, Option<R>> f) {
			return f.apply(this.value);
		}
		@Override
		public boolean isEmpty() {
			return false;
		}
		@Override
		public T getOrElse(T elseValue) {
			return this.value;
		}
	}

	public static <T> Option<T> unit(T value) {
		if (value == null) {
			return NONE;
		}
		return new Some(value);
	}

}