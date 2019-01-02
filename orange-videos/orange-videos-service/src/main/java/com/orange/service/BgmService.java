package com.orange.service;

import com.orange.pojo.Bgm;

import java.util.List;

public interface BgmService {
    List<Bgm> findBgmList();
    Bgm findBgmById(String id);
}
