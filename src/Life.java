import java.io.IOException;

public class Life {

	public static void main(String[] args) throws IOException {

		RobotFactory factory = new RobotFactory();

		factory.init();

		while (!factory.isStopped()) {
			factory.turn();
			System.out.println(factory.listRobots());
		}
	}

}
