
class OptionExample {

	public static void main(String[] args) {
		OptionExample o = new OptionExample();
		o.test();
	}

	private void test() {
		String b = safeB();
		System.out.println(b);
	}

	private Option<String> getA() {
		return Option.unit("a");
	}

	private Option<String> aToB(String a) {
		return Option.unit("b");
	}

	private String safeB() {
		return getA().bind(this::aToB).getOrElse("b not found");
	}
}