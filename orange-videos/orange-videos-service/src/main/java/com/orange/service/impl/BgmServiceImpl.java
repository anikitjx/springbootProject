package com.orange.service.impl;

import com.orange.mapper.BgmMapper;
import com.orange.pojo.Bgm;
import com.orange.service.BgmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BgmServiceImpl implements BgmService {
    @Resource
    private BgmMapper bgmMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Bgm> findBgmList() {
        List<Bgm> list = bgmMapper.findAllBgm();
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Bgm findBgmById(String id) {
        return bgmMapper.findBgmById(id);
    }
}
