import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.function.Function;

public abstract class Option<T> implements Monad<T> {

	public abstract T getOrElse(T elseValue);

	private static final Option NONE = new None();

	private Option() {}

	private static final class None<T> extends Option<T> {

		@Override
		public <R> Option<R> bind(Function<T, Monad<R>> f) {
			return NONE;
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
		public <R> Option<R> bind(Function<T, Monad<R>> f) {
			throw new NotImplementedException();
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