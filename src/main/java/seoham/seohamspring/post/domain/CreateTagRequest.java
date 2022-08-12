package seoham.seohamspring.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTagRequest {

    private String tagName;
    private String tagColor;
    private int userIdx;

    public CreateTagRequest() {

    }
}
