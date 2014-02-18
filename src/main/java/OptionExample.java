import org.w3c.dom.Document;

class OptionExample {

	public static void main(String[] args) {
		OptionExample o = new OptionExample();
		System.out.println(o.nullSafeChain());
	}

	private String loadXmlStringFromDatabase() { /* ... */
		return null;
	}
	private Document parseXmlToDom(String xml) { /* ... */
		return null;
	}
	private String findRelevantValue(Document doc) { /* ... */
		return null;
	}

	private String nullSafeChain() {
		Option<String> optionized = Option.unit(loadXmlStringFromDatabase());
		Option<String> chained = optionized.map(xml -> parseXmlToDom(xml)).map(doc -> findRelevantValue(doc));
		return chained.getOrElse("default value");
	}
	private String nullSafeChain2() {
		return Option.unit(loadXmlStringFromDatabase()).map(this::parseXmlToDom).map(this::findRelevantValue).getOrElse("default value");
	}
}