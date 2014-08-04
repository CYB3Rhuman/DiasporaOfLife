import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Page {

	String title;
	Map<Integer, String> posts = new HashMap<>();

	public Page(Robot owner) {
		title = owner.getFirstName() + " " + owner.getLastName();
	}

	public void addPost(int date, String post) {
		posts.put(date, post);
	}

	public String getHTML() {
		String html = "<h1>" + title + "</h1>\n";

		for (Entry<Integer, String> post : posts.entrySet()) {
			html += "<p>" + post.getKey() + ": " + post.getValue() + "</p>\n";
		}

		html += "\n";

		return html;
	}
}
