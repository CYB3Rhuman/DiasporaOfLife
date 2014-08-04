import java.io.IOException;

public class Life {

	public static void main(String[] args) throws IOException {

		RobotWorld world = new RobotWorld();

		world.init();

		while (!world.isStopped()) {
			world.turn();
			System.out.println("Year " + world.getDate() + ":");
			System.out.println(world.listRobots());
		}
	}

}
