package pl.seb.czech.ilegal.back.mappers;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.act.IsapActSearchQuery;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActKeywordDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchLogDto;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDifference;
import pl.seb.czech.ilegal.back.domain.act.entity.ActKeyword;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;

@Component
public class ActMapper {

    public ActSearchLogDto mapToActSearchLogDto(IsapActSearchQuery query) {
        return new ActSearchLogDto(query);
    }

    public ActSearchLog mapToActSearchLog(ActSearchLogDto asld) {
        return new ActSearchLog(asld.getId(), asld.getCreatedOn(), asld.getResponseTime(), asld.getResultCount(), 
                asld.getOnlyActInForce(), asld.getTitle(), asld.getKeyWord(), asld.getProperName(), asld.getPublisher(), 
                asld.getYear(), asld.getPosition());
    }

    public ActSearchLogDto mapToActSearchLogDto(ActSearchLog asl) {
        return new ActSearchLogDto(asl.getId(), asl.getCreatedOn(), asl.getResponseTime(), asl.getResultCount(), 
                asl.getOnlyActInForce(), asl.getTitle(), asl.getKeyWord(), asl.getProperName(), asl.getPublisher(), 
                asl.getYear(), asl.getPosition());
    }

    public ActKeyword mapToActKeyword(ActKeywordDto akd) {
        return new ActKeyword(akd.getName());
    }

    public ActKeywordDto mapToActKeywordDto(ActKeyword ak) {
        return new ActKeywordDto(ak.getName());
    }

    public ActDifference mapToActDifference(ActDifferenceDto add) {
        return new ActDifference(add.getId(), add.getTitle(), add.getStatusBefore(), add.getStatusAfter(),
                add.getLastChangeBefore(), add.getLastChangeAfter(), add.getCreatedOn(), add.getUnifiedTxtUrlBefore(),
                add.getUnifiedTxtUrlAfter());
    }

    public ActDifferenceDto mapToActDifferenceDto(ActDifference ad) {
        return new ActDifferenceDto(ad.getId(), ad.getTitle(), ad.getStatusBefore(), ad.getStatusAfter(),
                ad.getLastChangeBefore(), ad.getLastChangeAfter(), ad.getCreatedOn(), ad.getUnifiedTxtUrlBefore(), 
                ad.getUnifiedTxtUrlAfter());
    }

    public Act mapToAct(ActDto ad) {
        return new Act(ad.getId(), ad.getIsapId(), ad.getPublisher(), ad.getYear(), ad.getVolume(),
                ad.getPosition(), ad.getTitle(), ad.getStatus(), ad.getPromulgationDate(), ad.getChangeDate(),
                ad.getPublishedTextUrl(), ad.getUnifiedTextUrl());
    }

    public ActDto mapToActDto(Act act) {
        return new ActDto(act.getId(), act.getIsapId(), act.getPublisher(), act.getYear(), act.getVolume(),
                act.getPosition(), act.getTitle(), act.getStatus(), act.getPromulgationDate(), act.getChangeDate(),
                act.getPublishedTextUrl(), act.getUnifiedTextUrl());
    }
}
