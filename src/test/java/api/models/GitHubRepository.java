package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@lombok.Data
public class GitHubRepository {
    private String name;
    private String description;
    @JsonProperty("private")
    private boolean isPrivate;
    private boolean auto_init;
}
