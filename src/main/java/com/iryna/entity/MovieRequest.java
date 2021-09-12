package com.iryna.entity;

import com.iryna.entity.sort.SortType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Service
public class MovieRequest {

    private List<SortType> sortTypeList;
}
