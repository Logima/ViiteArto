package ohtu.viitearto;

import java.io.IOException;
import java.util.Properties;


public class GitState {

    private final String commitId;
    private final String buildTime;
    private final String commitUserName;
    private final String commitMessageFull;
    private final String commitTime;
    
    
    public GitState() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("git.properties"));
        this.commitId = properties.get("git.commit.id").toString();
        this.buildTime = properties.get("git.build.time").toString();
        this.commitUserName = properties.get("git.commit.user.name").toString();
        this.commitMessageFull = properties.get("git.commit.message.full").toString();
        this.commitTime = properties.get("git.commit.time").toString();
    }
    
    public String getBuildTime() {
        return buildTime;
    }

    public String getCommitId() {
        return commitId;
    }

    public String getCommitMessageFull() {
        return commitMessageFull;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public String getCommitUserName() {
        return commitUserName;
    }
}
