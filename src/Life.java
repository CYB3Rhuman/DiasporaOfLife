import java.io.IOException;
import java.io.PrintWriter;

public class Life {

	public static void main(String[] args) throws IOException {

		RobotWorld world = new RobotWorld();

		world.init();

		while (!world.isStopped()) {
			world.turn();
			System.out.println("Year " + world.getDate() + ":");
			System.out.println(world.listRobots());
		}
		
		PrintWriter writer = new PrintWriter("blog.html", "UTF-8");
		writer.print(world.generateHTML());
		writer.close();
	}

}
