package com.moodle.sevsu.webdb.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ExportExcelService {

    void fileDownload(String filePath, HttpServletResponse response, String fileName);

    boolean createExcel (List list, ServletContext servletContext);

}
