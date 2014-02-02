import java.util.Random;

public class Robot {

	public Robot(int id, boolean male, String firstName, String lastName) {
		rnd = new Random();

		this.id = id;
		this.male = male;
		this.firstName = firstName;
		this.lastName = lastName;

		age = 16;
		fatherId = 0;
		motherId = 0;
		spouseId = 0;

		dead = false;
		married = false;
		pregnant = false;
	}

	public Robot(int id, boolean male, String firstName, Robot father,
			Robot mother) {
		rnd = new Random();

		this.id = id;
		this.male = male;
		this.firstName = firstName;
		this.lastName = father.getLastName();

		age = 0;
		fatherId = father.getId();
		motherId = mother.getId();
		spouseId = 0;

		dead = false;
		married = false;
		pregnant = false;
	}

	private Random rnd;

	private int id;
	private String firstName, lastName;
	private int age;

	private boolean male;

	private int fatherId, motherId;
	private int spouseId;

	private boolean dead, married, pregnant;

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

	public boolean isMale() {
		return male;
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

	public void incrementAge() {
		if (!dead) {
			age++;

			if (age > 90) {
				if (rnd.nextInt(100) > 90) { // 10%
					die();
				}
			} else if (age > 60) {
				if (rnd.nextInt(100) < 2) { // 2%
					die();
				}
			} else {
				if (rnd.nextInt(1000) == 666) { // 0.1%
					die();
				}
			}
		}
	}

	public void die() {
		dead = true;
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

	public boolean getChildGender() {
		pregnant = false;

		if (rnd.nextInt(100) > 50) { // 50%
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return (dead ? "-" : "<") + id + (dead ? "-" : ">") + ": " + firstName
				+ " " + lastName + " (" + (male ? "m" : "f") + ", " + age + ")";
	}
}