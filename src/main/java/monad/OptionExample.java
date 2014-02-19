package monad;

import org.w3c.dom.Document;

class OptionExample {

	public static void main(String[] args) {
		OptionExample o = new OptionExample();
		System.out.println(o.nullSafeChain3());
	}

	private String loadCacheKeyFromDatabase() { /* ... */
		return null;
	}

	private Document getDocumentFromCache(String key) { /* ... */
		return null;
	}

	private String findRelevantValue(Document doc) { /* ... */
		return null;
	}

	private String nullSafeChain() {
		Option<String> optionized = Option.unit(loadCacheKeyFromDatabase());
		Option<String> chained = optionized.map(key -> getDocumentFromCache(key)).map(doc -> findRelevantValue(doc));
		return chained.getOrElse("N/A");
	}

	private String nullSafeChain2() {
		return Option.unit(loadCacheKeyFromDatabase()).map(this::getDocumentFromCache).map(this::findRelevantValue).getOrElse("N/A");
	}

	private String nullSafeChain3() {
		return Option.unit(new A()).map(A::getB).map(B::getC).getOrElse("N/A");
	}

	private String nullSafe4(A a) {
		if (a != null) {
			B b = a.getB();
			if (b != null) {
				String c = b.getC();
				if (c != null) {
					return c;
				}
			}
		}
		return "N/A";
	}
	private String nullSafe5(A a) {
		if (a != null && a.getB() != null && a.getB().getC() != null) {
			return a.getB().getC();
		}
		return "N/A";
	}

	class A {
		B getB() {
			return new B();
		}
	}

	class B {
		String getC() {
			return "c";
		}
	}
}