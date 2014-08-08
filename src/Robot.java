import java.util.Random;

public class Robot {

	public Robot(int id, Gender gender, String firstName, String lastName,
			RobotWorld world) {
		this.id = id;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.world = world;
		rnd = world.getRandom();

		age = rnd.nextInt(10) + 15;
		fatherId = 0;
		motherId = 0;
		spouseId = 0;

		dead = false;
		married = false;
		pregnant = false;

		blog = new Page(this);
		blog.addPost(world.getDate(), "I entered this world as a pioner. And I'm "
				+ (gender == Gender.MALE ? "" : "fe") + "male, " + age + ".");
	}

	public Robot(int id, Gender gender, String firstName, Robot father,
			Robot mother, RobotWorld world) {
		this.id = id;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = father.getLastName();
		this.world = world;
		rnd = world.getRandom();

		age = 0;
		fatherId = father.getId();
		motherId = mother.getId();
		spouseId = 0;

		dead = false;
		married = false;
		pregnant = false;

		blog = new Page(this);
		blog.addPost(world.getDate(), "I was born. And I'm "
				+ (gender == Gender.MALE ? "" : "fe") + "male.");

		System.out.println(mother.getFirstName() + " " + mother.getLastName()
				+ " gave birth to a child of " + father.getFirstName() + " "
				+ father.getLastName() + ": " + firstName + ".");
	}

	private RobotWorld world;
	private Random rnd;

	private int id;
	private String firstName, lastName;
	private int age;

	private Gender gender;

	private int fatherId, motherId;
	private int spouseId;

	private boolean dead, married, pregnant;

	private Page blog;

	public int getId() {
		return id;
	}

	public int getSpouseId() {
		return spouseId;
	}

	public int getAge() {
		return age;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public boolean isDead() {
		return dead;
	}

	public boolean isPregnant() {
		return pregnant;
	}

	public boolean isMarried() {
		return married;
	}
	
	public void turn() {
		incrementAge();

		if (!isDead() && getAge() >= 18) {
			if (!isMarried() && getGender() == Gender.MALE) {
				world.findPartner(this);
			}

			if (isMarried() && getGender() == Gender.MALE) {
				Robot wife = world.getRobot(getSpouseId());

				if (!wife.isDead()) {
					proposeSex(wife);
				}
			}

			if (isPregnant()) {
				Gender gender = getChildGender();
				Robot father = world.getRobot(getSpouseId());
				world.addChild(gender, father, this);
			}
		}

	}

	public void incrementAge() {
		if (!dead) {
			age++;

			if (age > 90) {
				if (rnd.nextInt(100) > 85) { // 15%
					die();
				}
			} else if (age > 60) {
				if (rnd.nextInt(100) < 2) { // 2%
					die();
				}
			} else {
				if (rnd.nextInt(100) == 6) { // 1%
					die();
				}
			}
		}
	}
	
	public void die() {
		dead = true;

		blog.addPost(world.getDate(), "I died. I was " + age + ".");
		System.out.println(firstName + " " + lastName + " died (age " + age
				+ ").");
	}

	public void proposeMarriage(Robot f) {
		if (!f.isMarried() && rnd.nextInt(100) > 50) { // 50%
			System.out.println(f.firstName + " " + f.lastName
					+ " agreed to marry " + getFirstName() + " "
					+ getLastName() + ".");

			married = true;
			spouseId = f.getId();

			f.marry(this);
		} else {
			System.out.println(f.firstName + " " + f.lastName + " said to "
					+ getFirstName() + " " + getLastName()
					+ " to go fuck himself.");
		}
	}

	private void marry(Robot m) {
		lastName = m.getLastName();
		married = true;
		spouseId = m.getId();
	}

	public void proposeSex(Robot f) {
		if (rnd.nextInt(100) > 50) { // 50%
			System.out.println(f.firstName + " " + f.lastName
					+ " had sex with " + getFirstName() + " " + getLastName()
					+ ".");
			f.inpregnate(this);
		} else {
			System.out.println(f.firstName + " " + f.lastName
					+ " rejected to have sex with " + getFirstName() + " "
					+ getLastName() + ".");
		}
	}

	private void inpregnate(Robot m) {
		if (rnd.nextInt(100) > 80) { // 20%
			pregnant = true;

			System.out.println(m.firstName + " " + m.lastName + " inpregnated "
					+ getFirstName() + " " + getLastName() + ".");
		} else {
			System.out.println(m.firstName + " " + m.lastName
					+ " failed to inpregnate " + getFirstName() + " "
					+ getLastName() + ".");
		}
	}

	public Gender getChildGender() {
		pregnant = false;

		blog.addPost(world.getDate(), "I gave birth to a child.");

		if (rnd.nextInt(100) > 50) { // 50%
			return Gender.MALE;
		} else {
			return Gender.FEMALE;
		}
	}

	public Page getBlog() {
		return blog;
	}

	@Override
	public String toString() {
		return (dead ? "-" : "<") + id + (dead ? "-" : ">") + ": " + firstName
				+ " " + lastName + " (" + (gender == Gender.MALE ? "m" : "f")
				+ ", " + age + (married ? ", married" : "") + ")";
	}
}