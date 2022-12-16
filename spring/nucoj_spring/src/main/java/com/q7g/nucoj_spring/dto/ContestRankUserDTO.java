package com.q7g.nucoj_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestRankUserDTO implements Comparable<ContestRankUserDTO>{
    private String userId;
    private String name;
    private String number;
    private List<ContestRankUserProblemDTO> states;


    @Override
    public int compareTo(ContestRankUserDTO o) {
        int a = this.states.stream().filter(item -> item.getAccept()).collect(Collectors.toList()).size();
        int b = o.states.stream().filter(item -> item.getAccept()).collect(Collectors.toList()).size();
        if (a != b) return a - b; // 以过题数为先， 从大到小
        AtomicInteger x = new AtomicInteger(0);
        AtomicInteger y = new AtomicInteger(0);
        this.states.stream().forEach(item -> x.addAndGet(Math.toIntExact(item.getPunish())));
        o.states.stream().forEach(item -> y.addAndGet(Math.toIntExact(item.getPunish())));
        return y.get() - x.get();
    }
}
