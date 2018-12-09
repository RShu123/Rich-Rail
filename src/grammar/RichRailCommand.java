package grammar;


public class RichRailCommand extends RichRailBaseListener {
	
	@Override public void enterCommand(RichRailParser.CommandContext ctx) 
	{ 
		System.out.println("Command entered " + ctx.getText());
	}
	
	@Override public void enterNewtraincommand(RichRailParser.NewtraincommandContext ctx) 
	{
		System.out.println("Now creating new train " + ctx.getText());
	}

	@Override public void enterDelcommand(RichRailParser.DelcommandContext ctx) 
	{
		System.out.println("Now deleting train " + ctx.getText());
	}
	
	@Override public void enterNewwagoncommand(RichRailParser.NewwagoncommandContext ctx) {
		System.out.println("Now creating new wagon " + ctx.getText());
	}
}

