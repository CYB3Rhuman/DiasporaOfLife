import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RobotFactory {

	public RobotFactory() {
		rnd = new Random();
	}

	Random rnd;

	NameGenerator generator = new NameGenerator();

	List<Robot> robots = new ArrayList<Robot>();

	public void init() {
		robots.add(new Robot(1, true, generator.nextMaleName(), generator
				.nextSurname()));
		robots.add(new Robot(2, true, generator.nextMaleName(), generator
				.nextSurname()));
		robots.add(new Robot(3, true, generator.nextMaleName(), generator
				.nextSurname()));
		robots.add(new Robot(4, false, generator.nextFemaleName(), generator
				.nextSurname()));
		robots.add(new Robot(5, false, generator.nextFemaleName(), generator
				.nextSurname()));
	}

	public void turn() {
		List<Robot> children = new ArrayList<Robot>();

		for (Robot r : robots) {
			r.incrementAge();

			if (!r.isDead() && r.getAge() >= 18) {
				if (!r.isMarried() && r.isMale()) {
					findPartner(r);
				}

				if (r.isMarried() && r.isMale()) {
					Robot wife = findById(r.getSpouseId());

					if (!wife.isDead()) {
						r.proposeSex(wife);
					}
				}

				if (r.isPregnant()) {
					boolean gender = r.getChildGender();
					Robot father = findById(r.getSpouseId());

					Robot child = new Robot(
							robots.size() + children.size() + 1, gender,
							gender ? generator.nextMaleName() : generator
									.nextFemaleName(), father, r);

					children.add(child);
				}
			}
		}
		robots.addAll(children);
	}

	private void findPartner(Robot r1) {

		boolean proposal = false;

		while (!proposal) {

			int myid = rnd.nextInt(robots.size()) + 1;

			Robot r = findById(myid);

			if (!r.isDead() && r.getAge() >= 17 && r.isMale() != r1.isMale()) {
				r1.proposeMarriage(r);

				proposal = true;
			}
		}
	}

	private Robot findById(int id) {
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
}
