import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RobotWorld {

	public RobotWorld() {
		rnd = new Random();
	}

	Random rnd;

	NameGenerator generator = new NameGenerator();

	List<Robot> robots = new ArrayList<Robot>();
	List<Robot> children = new ArrayList<Robot>();

	private int date = 2000;

	public int getDate() {
		return date;
	}

	public Random getRandom() {
		return rnd;
	}

	public void init() {
		robots.add(new Robot(1, Gender.MALE, generator.nextMaleName(),
				generator.nextSurname(), this));
		robots.add(new Robot(2, Gender.MALE, generator.nextMaleName(),
				generator.nextSurname(), this));
		robots.add(new Robot(3, Gender.MALE, generator.nextMaleName(),
				generator.nextSurname(), this));
		robots.add(new Robot(4, Gender.FEMALE, generator.nextFemaleName(),
				generator.nextSurname(), this));
		robots.add(new Robot(5, Gender.FEMALE, generator.nextFemaleName(),
				generator.nextSurname(), this));
	}

	public void turn() {
		date++;

		children = new ArrayList<Robot>();

		for (Robot r : robots) {
			r.turn();
		}

		robots.addAll(children);
	}

	public void addChild(Gender gender, Robot father, Robot mother) {
		children.add(new Robot(robots.size() + children.size() + 1, gender,
				gender == Gender.MALE ? generator.nextMaleName() : generator
						.nextFemaleName(), father, mother, this));
	}

	public boolean isStopped() {
		for (Robot r : robots) {
			if (!r.isDead()) {
				return false;
			}
		}
		return true;
	}

	public void findPartner(Robot r1) {

		boolean proposal = false;
		int iteration = 0;

		while (!proposal && iteration < 50) {

			int myid = rnd.nextInt(robots.size()) + 1;

			Robot r = getRobot(myid);

			if (!r.isDead() && r.getAge() >= 17
					&& r.getGender() != r1.getGender()) {
				r1.proposeMarriage(r);

				proposal = true;
			}

			iteration++;
		}
	}

	public Robot getRobot(int id) {
		for (Robot r : robots) {
			if (r.getId() == id) {
				return r;
			}
		}

		return null;
	}

	public String listRobots() {
		String robotsStr = "";

		for (Robot r : robots) {
			robotsStr += r.toString() + "\n";
		}

		return robotsStr;
	}

	public String generateHTML() {
		String html = "<!DOCTYPE html>\n";

		html += "<html>\n";
		html += "<head><title>Blogs</title></head>\n";
		html += "<body>\n\n";

		for (Robot r : robots) {
			html += r.getBlog().getHTML();
		}

		html += "</body>\n</html>\n";

		return html;
	}
}
