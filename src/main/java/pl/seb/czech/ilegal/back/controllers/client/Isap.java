package pl.seb.czech.ilegal.back.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.seb.czech.ilegal.back.clients.act.IsapActSearchQuery;
import pl.seb.czech.ilegal.back.clients.act.IsapClient;
import pl.seb.czech.ilegal.back.mappers.act.IsapMapper;

@RestController
@RequestMapping(value = "${url.base.search}")
public class Isap {
    @Autowired
    private IsapClient isapClient;
    @Autowired
    private IsapMapper isapMapper;
    
    @GetMapping
    public void performSearch(){
        isapClient.performSearchQuery(new IsapActSearchQuery());
    }
}
