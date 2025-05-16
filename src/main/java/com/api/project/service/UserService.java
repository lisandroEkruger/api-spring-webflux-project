package com.api.project.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class UserService {

    public UserService() {
    }

    public Flux<String> getNames(){
        return Flux.just("David","Alberto","Francisco","Esperanza");
    }

    public Flux<String> getLastnames(){
        List<String> lastnamesList = List.of("Jiménez","Villajos","Espinosa","Rodriguez");
        return Flux.fromIterable(lastnamesList);
    }

    public Flux<Void> getEmptyData(){
        return Flux.empty();
    }

    public Flux<String> getCharsFromNames(){
        return getNames().flatMap(x -> Flux.just(x.split("")));
    }

}
