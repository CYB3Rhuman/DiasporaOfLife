import java.util.ArrayList;
import java.util.List;

public class Page {

	String title;
	List<String> posts = new ArrayList<>();
	
	public Page(Robot owner) {
		title = owner.getFirstName() + " " + owner.getLastName();
	}

	public void addPost(String post) {
		posts.add(System.currentTimeMillis() + ": " + post);
	}
	
	public String getHTML() {
		String html = "<h1>" + title + "</h1>\n";
		
		for (String post : posts) {
			html += "<p>" + post + "</p>\n";
		}
		
		html += "\n";
		
		return html;
	}
}
