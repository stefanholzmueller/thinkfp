package monad;

import java.util.function.Function;

public abstract class Option<T> {

	public <R> Option<R> map(Function<T, R> f) {
		return bind(f.andThen(Option::unit));
	}

	public abstract <R> Option<R> bind(Function<T, Option<R>> f);
	public abstract boolean isEmpty();
	public abstract T getOrThrow();
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
		@Override
		public T getOrThrow() {
			throw new RuntimeException();
		}
	}

	private static final class Some<T> extends Option<T> {
		private final T value; // never null

		private Some(T value) {
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
		@Override
		public T getOrThrow() {
			return this.value;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			if (!value.equals(((Some) o).value)) return false;
			return true;
		}

		@Override
		public int hashCode() {
			return value.hashCode();
		}

		@Override
		public String toString() {
			return "Some{" + "value=" + value + '}';
		}
	}

	public static <T> Option<T> unit(T value) {
		if (value == null) {
			return NONE;
		}
		return new Some<T>(value);
	}

}