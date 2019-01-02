package com.orange.mapper;

import com.orange.pojo.Bgm;
import com.orange.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BgmMapper extends MyMapper<Bgm> {
    List<Bgm> findAllBgm();
    Bgm findBgmById(@Param(value="id") String id);
}