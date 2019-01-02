package com.orange.mapper;

import com.orange.pojo.SearchRecords;
import com.orange.utils.MyMapper;

import java.util.List;

public interface SearchRecordsMapper extends MyMapper<SearchRecords> {
    void saveRecord(SearchRecords record);
    List<String> selectHotSearch();
}