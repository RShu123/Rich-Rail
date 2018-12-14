package commandpattern;

public class Use {
	
	Command theCommand;
	
	public Use(Command newCommand) {
		theCommand = newCommand;
	}
	
	public void uitvoeren() {
		theCommand.execute();
	}

}
