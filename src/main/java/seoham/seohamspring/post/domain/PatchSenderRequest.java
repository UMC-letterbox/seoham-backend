package seoham.seohamspring.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchSenderRequest {

    private String changedSender;
    private int userIdx;

    public PatchSenderRequest(){}
}
