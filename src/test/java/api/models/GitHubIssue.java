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
public class GitHubIssue {
    private String title;
    private String body;

    @JsonProperty("state")
    private String state;
}
