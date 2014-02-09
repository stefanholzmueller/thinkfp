
class OptionType {



	public static void main(String[] args) {
		OptionType o = new OptionType();
		o.test();
	}

	private void test() {
		String b = safeB();
	}

	private String getA() {
		return "a";
	}

	private String aToB(String a) {
		return "b";
	}

	private String safeB() {
		String a = getA();
		if (a != null) {
			String b = aToB(a);
			if (b != null) {
				return b;
			}
		}
		return "b not found";
	}
}