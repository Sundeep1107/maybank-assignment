package com.maybank.assignment.util;

import com.maybank.assignment.dto.MetaData;

public class CommonUtil {

    public static MetaData getMetaData(Long totalRecords, Long totalPages, int currentPage, int pageSize){
        MetaData metaData = new MetaData();

        metaData.setTotalRecords(totalRecords);
        metaData.setTotalPages(totalPages);
        metaData.setCurrentPage(currentPage);
        metaData.setRecordsPerPage(pageSize);

        return metaData;
    }
}
