package pl.seb.czech.ilegal.back.clients.act;

import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.seb.czech.ilegal.back.clients.SearchQuery;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IsapActSearchQuery extends SearchQuery {
    public static final String IN_FORCE_ACTS = "Obowiązujące";
    public static final String ALL_ACTS = "Wszystkie";

    private String onlyActInForce;
    private String title;
    private String keyWord;
    private String properName;
    private ActPublisher publisher;
    private Integer year;
    private Integer position;

    private Integer offset = 0;

    @Override
    public MultiValueMap<String, String> getQueryParams() {
        queryParams = new LinkedMultiValueMap<>();
        if (onlyActInForce != null) {
            if (onlyActInForce.equals(IN_FORCE_ACTS)) {
                queryParams.add("inForce", "1");
            }
        }

        addToQueryParamsNotNull("title", title);
        addToQueryParamsNotNull("keyword", keyWord);
        addToQueryParamsNotNull("keywordName", properName);

        if (publisher != null) {
            if (!publisher.equals(ActPublisher.ALL)) {
                queryParams.add("publisher", publisher.name());
            }
        }

        addToQueryParamsNotNull("year", year);
        addToQueryParamsNotNull("position", position);
        addToQueryParamsNotNull("limit", resultLimitPerPage);
        addToQueryParamsNotNull("offset", offset);
        return queryParams;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber > 0) {
            offset = ((pageNumber - 1) * resultLimitPerPage);
        }
    }

    public int getCurrentPageNumber() {
        return (offset / resultLimitPerPage) + 1;
    }

}
