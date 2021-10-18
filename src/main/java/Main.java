import me.dilley.MineStat;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static void main(String[] args) {
        String token = "ODk5+NDMxODE1NTQ++xMDU1NTA4.YWyrJw.+U3-tov4FPjdhB+5hcTo_ZIf4P+fWY";
        token = token.replaceAll("\\+", "");

        // Log the bot in
        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            } else if (event.getMessageContent().equalsIgnoreCase("!check")) {
                MineStat stats = new MineStat("LittleL.minehut.gg", 25575);
                if (stats.isServerUp()) {
                    event.getChannel().sendMessage("Server is online with " + stats.getCurrentPlayers() + "/" + stats.getMaximumPlayers());
                } else {
                    event.getChannel().sendMessage("Server is offline.");
                }
            } else if (event.getMessageContent().equalsIgnoreCase("!shutdown")) {
                api.disconnect();
                System.exit(0);
            }
        });
    }
}
