import java.util.function.Function;

public interface Monad<T> {
	<U extends T> Monad<U> unit(U value);
	<R extends T> Monad<R> bind(Function<T, Monad<R>> f);

	default <R extends T> Monad<T> map(Function<T, R> f) {
		return bind((Function<T, Monad<T>>) x ->  unit(f.apply(x)));
	}
}
