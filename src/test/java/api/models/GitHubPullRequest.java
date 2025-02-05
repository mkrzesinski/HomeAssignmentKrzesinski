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
public class GitHubPullRequest {
    private String title;
    private String head;
    private String base;
    @JsonProperty("body")
    private String description;
}
