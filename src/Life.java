import java.io.IOException;

public class Life {

	public static void main(String[] args) throws IOException {

		RobotFactory factory = new RobotFactory();
		
		factory.init();
		
		while (true) {
			factory.turn();
			
			System.out.println(factory.listRobots());

			System.in.read();
		}		
	}

}
