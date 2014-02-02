import java.util.Random;

public class Robot {

	public Robot(int id, boolean isMale, String firstName, String lastName) {
		rnd = new Random();

		this.id = id;
		this.isMale = isMale;
		this.firstName = firstName;
		this.lastName = lastName;

		age = 16;
		fatherId = 0;
		motherId = 0;
		spouseId = 0;

		isDead = false;
		isMarried = false;
		isPregnant = false;
	}

	public Robot(int id, boolean isMale, String firstName, Robot father,
			Robot mother) {
		rnd = new Random();

		this.id = id;
		this.isMale = isMale;
		this.firstName = firstName;
		this.lastName = father.getLastName();

		age = 0;
		fatherId = father.getId();
		motherId = mother.getId();
		spouseId = 0;

		isDead = false;
		isMarried = false;
		isPregnant = false;
	}

	private Random rnd;

	private int id;
	private String firstName, lastName;
	private int age;

	private boolean isMale;

	private int fatherId, motherId;
	private int spouseId;

	private boolean isDead, isMarried, isPregnant;

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
		return isMale;
	}

	public boolean isDead() {
		return isDead;
	}

	public boolean isPregnant() {
		return isPregnant;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public void incrementAge() {
		if (!isDead) {
			age++;

			if (age > 90) {
				if (rnd.nextInt(100) > 50) { // 50%
					die();
				}
			} else if (age > 60) {
				if (rnd.nextInt(100) < 2) {  // 3%
					die();
				}
			}
		}
	}

	public void die() {
		isDead = true;
		System.out.println(firstName + " " + lastName + " died (age " + age
				+ ").");
	}

	public void proposeMarriage(Robot f) {
		if (!f.isMarried() && rnd.nextInt(100) > 50) { // 50%
			System.out.println(f.firstName + " " + f.lastName
					+ " agreed to marry " + getFirstName() + " "
					+ getLastName() + ".");

			isMarried = true;
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
		isMarried = true;
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
			isPregnant = true;

			System.out.println(m.firstName + " " + m.lastName + " inpregnated "
					+ getFirstName() + " " + getLastName() + ".");
		} else {
			System.out.println(m.firstName + " " + m.lastName
					+ " failed to inpregnate " + getFirstName() + " "
					+ getLastName() + ".");
		}
	}

	public boolean getChildGender() {
		isPregnant = false;

		if (rnd.nextInt(100) > 50) { // 50%
			return true;
		} else {
			return false;
		}
	}
}