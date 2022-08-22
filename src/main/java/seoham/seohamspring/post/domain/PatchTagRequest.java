package seoham.seohamspring.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchTagRequest {


    private String tagName;
    private String tagColor;

    public PatchTagRequest() {

    }
}
