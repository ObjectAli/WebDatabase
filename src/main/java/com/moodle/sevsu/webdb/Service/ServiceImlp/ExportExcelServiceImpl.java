package com.moodle.sevsu.webdb.Service.ServiceImlp;

import com.moodle.sevsu.webdb.Service.ExportExcelService;
import com.moodle.sevsu.webdb.entity.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined.*;

@Service
public class ExportExcelServiceImpl implements ExportExcelService {

    private HSSFWorkbook workbook = new HSSFWorkbook();

    @Autowired
    private ServletContext servletContext;

    public HSSFCellStyle headStyle() {
        HSSFCellStyle headCellStyle = workbook.createCellStyle();
        headCellStyle.setFillForegroundColor(GREY_25_PERCENT.getIndex());
        headCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headCellStyle.setAlignment(HorizontalAlignment.CENTER);
        return headCellStyle;
    }

    public HSSFCellStyle bodyStyle() {
        HSSFCellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setFillForegroundColor(WHITE.getIndex());
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);
        return bodyStyle;
    }

    @Override
    public boolean createExcel(List list, ServletContext servletContext) {
        String filePath = servletContext.getRealPath("/resources/reports/");
        File file = new File(filePath);
        boolean exists = new File(filePath).exists();

        if (!exists) {
            new File(filePath).mkdirs();
        }

        boolean result = true;
        if (list.get(0).getClass().equals(Institute.class)) result = createInstitutes(list, file);
        else if (list.get(0).getClass().equals(Department.class)) result = createDepartments(list, file);
        else if (list.get(0).getClass().equals(Direction.class)) result = createDirections(list, file);
        else if (list.get(0).getClass().equals(User.class)) result = createUsers(list, file);
        else if (list.get(0).getClass().equals(Course.class)) result = createCourses(list, file);
        else if (list.get(0).getClass().equals(CourseDir.class)) result = createCourseDir(list, file);
        else if(list.get(0).getClass().equals(Developer.class)) result = createDevelopers(list, file);
        return result;
    }

    @Override
    public void fileDownload(String filePath, HttpServletResponse response, String fileName) {
        File file = new File(filePath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = servletContext.getMimeType(filePath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment; filename=" + fileName);

                OutputStream outputStream = response.getOutputStream();
                byte[] byffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(byffer)) != -1) {
                    outputStream.write(byffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();
                file.delete();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean createInstitutes(List<Institute> instituteList, File file) {
        Collections.sort(instituteList,new Comparator<Institute>() {
            public int compare(Institute o1, Institute o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "institutes" + ".xls");

            HSSFSheet workSheet = workbook.createSheet("Institutes");
            workSheet.setDefaultColumnWidth(40);

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell columnId = headerRow.createCell(0);
            columnId.setCellValue("№");
            columnId.setCellStyle(headStyle());

            HSSFCell columnTitle = headerRow.createCell(1);
            columnTitle.setCellValue("Название");
            columnTitle.setCellStyle(headStyle());

            int i = 1;
            for (Institute institute : instituteList) {
                HSSFRow bodyRow = workSheet.createRow(i);

                HSSFCell idValue = bodyRow.createCell(0);
                idValue.setCellValue(i);
                idValue.setCellStyle(bodyStyle());

                HSSFCell titleValue = bodyRow.createCell(1);
                titleValue.setCellValue(institute.getTitle());
                titleValue.setCellStyle(bodyStyle());

                i++;
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean createDepartments(List<Department> departmentList, File file) {
        Collections.sort(departmentList,new Comparator<Department>() {
            public int compare(Department o1, Department o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "departments" + ".xls");

            HSSFSheet workSheet = workbook.createSheet("Departments");
            workSheet.setDefaultColumnWidth(40);

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell columnId = headerRow.createCell(0);
            columnId.setCellValue("№");
            columnId.setCellStyle(headStyle());

            HSSFCell columnTitle = headerRow.createCell(1);
            columnTitle.setCellValue("Название");
            columnTitle.setCellStyle(headStyle());

            HSSFCell columnInstitute = headerRow.createCell(2);
            columnInstitute.setCellValue("Институт");
            columnInstitute.setCellStyle(headStyle());

            int i = 1;
            for (Department department : departmentList) {
                HSSFRow bodyRow = workSheet.createRow(i);

                HSSFCell idValue = bodyRow.createCell(0);
                idValue.setCellValue(i);
                idValue.setCellStyle(bodyStyle());

                HSSFCell titleValue = bodyRow.createCell(1);
                titleValue.setCellValue(department.getTitle());
                titleValue.setCellStyle(bodyStyle());

                HSSFCell instituteValue = bodyRow.createCell(2);
                instituteValue.setCellValue(department.getInstitute().getTitle());
                instituteValue.setCellStyle(bodyStyle());

                i++;
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean createDirections(List<Direction> directionList, File file) {
        Collections.sort(directionList,new Comparator<Direction>() {
            public int compare(Direction o1, Direction o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "directions" + ".xls");

            HSSFSheet workSheet = workbook.createSheet("Directions");
            workSheet.setDefaultColumnWidth(40);

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell columnId = headerRow.createCell(0);
            columnId.setCellValue("№");
            columnId.setCellStyle(headStyle());

            HSSFCell columnTitle = headerRow.createCell(1);
            columnTitle.setCellValue("Название");
            columnTitle.setCellStyle(headStyle());

            HSSFCell columnChiper = headerRow.createCell(2);
            columnChiper.setCellValue("Шифр");
            columnChiper.setCellStyle(headStyle());

            HSSFCell columnInstitute = headerRow.createCell(3);
            columnInstitute.setCellValue("Институт");
            columnInstitute.setCellStyle(headStyle());

            int i = 1;
            for (Direction direction : directionList) {
                HSSFRow bodyRow = workSheet.createRow(i);

                HSSFCell idValue = bodyRow.createCell(0);
                idValue.setCellValue(i);
                idValue.setCellStyle(bodyStyle());

                HSSFCell titleValue = bodyRow.createCell(1);
                titleValue.setCellValue(direction.getTitle());
                titleValue.setCellStyle(bodyStyle());

                HSSFCell chiperValue = bodyRow.createCell(2);
                chiperValue.setCellValue(direction.getCipher());
                chiperValue.setCellStyle(bodyStyle());

                HSSFCell instituteValue = bodyRow.createCell(3);
                instituteValue.setCellValue(direction.getInstitute().getTitle());
                instituteValue.setCellStyle(bodyStyle());

                i++;
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean createUsers(List<User> userList, File file) {
        Collections.sort(userList,new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        });
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "users" + ".xls");

            HSSFSheet workSheet = workbook.createSheet("User");
            workSheet.setDefaultColumnWidth(40);

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell columnId = headerRow.createCell(0);
            columnId.setCellValue("№");
            columnId.setCellStyle(headStyle());

            HSSFCell columnSurname = headerRow.createCell(1);
            columnSurname.setCellValue("Фамилия");
            columnSurname.setCellStyle(headStyle());

            HSSFCell columnName = headerRow.createCell(2);
            columnName.setCellValue("Имя");
            columnName.setCellStyle(headStyle());

            HSSFCell columnSecondName = headerRow.createCell(3);
            columnSecondName.setCellValue("Отчество");
            columnSecondName.setCellStyle(headStyle());

            HSSFCell columnPosition = headerRow.createCell(4);
            columnPosition.setCellValue("Должность");
            columnPosition.setCellStyle(headStyle());

            HSSFCell columnDepartment = headerRow.createCell(5);
            columnDepartment.setCellValue("Кафедра");
            columnDepartment.setCellStyle(headStyle());

            HSSFCell columnPhone = headerRow.createCell(6);
            columnPhone.setCellValue("Номер телефона");
            columnPhone.setCellStyle(headStyle());

            HSSFCell columnEmail = headerRow.createCell(7);
            columnEmail.setCellValue("Email");
            columnEmail.setCellStyle(headStyle());

            int i = 1;
            for (User user : userList) {
                HSSFRow bodyRow = workSheet.createRow(i);

                HSSFCell idValue = bodyRow.createCell(0);
                idValue.setCellValue(i);
                idValue.setCellStyle(bodyStyle());

                HSSFCell surnameValue = bodyRow.createCell(1);
                surnameValue.setCellValue(user.getSurname());
                surnameValue.setCellStyle(bodyStyle());

                HSSFCell nameValue = bodyRow.createCell(2);
                nameValue.setCellValue(user.getName());
                nameValue.setCellStyle(bodyStyle());

                HSSFCell secondNameValue = bodyRow.createCell(3);
                secondNameValue.setCellValue(user.getSecondname());
                secondNameValue.setCellStyle(bodyStyle());

                HSSFCell positionValue = bodyRow.createCell(4);
                positionValue.setCellValue(user.getPosition());
                positionValue.setCellStyle(bodyStyle());

                HSSFCell departmentValue = bodyRow.createCell(5);
                departmentValue.setCellValue(user.getDepartment().getTitle());
                departmentValue.setCellStyle(bodyStyle());

                HSSFCell phoneValue = bodyRow.createCell(6);
                phoneValue.setCellValue(user.getPhone());
                phoneValue.setCellStyle(bodyStyle());

                HSSFCell emailValue = bodyRow.createCell(7);
                emailValue.setCellValue(user.getEmail());
                emailValue.setCellStyle(bodyStyle());

                i++;
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean createCourses(List<Course> coursesList, File file) {
        Collections.sort(coursesList,new Comparator<Course>() {
            public int compare(Course o1, Course o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "courses" + ".xls");

            HSSFSheet workSheet = workbook.createSheet("Course");
            workSheet.setDefaultColumnWidth(40);

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell columnId = headerRow.createCell(0);
            columnId.setCellValue("№");
            columnId.setCellStyle(headStyle());

            HSSFCell columnTitle = headerRow.createCell(1);
            columnTitle.setCellValue("Название");
            columnTitle.setCellStyle(headStyle());

            HSSFCell columnHours = headerRow.createCell(2);
            columnHours.setCellValue("Количество часов");
            columnHours.setCellStyle(headStyle());

            HSSFCell column3E = headerRow.createCell(3);
            column3E.setCellValue("3E");
            column3E.setCellStyle(headStyle());

            HSSFCell columnReadiness = headerRow.createCell(4);
            columnReadiness.setCellValue("Готовность");
            columnReadiness.setCellStyle(headStyle());

            int i = 1;
            for (Course course : coursesList) {
                HSSFRow bodyRow = workSheet.createRow(i);

                HSSFCell idValue = bodyRow.createCell(0);
                idValue.setCellValue(i);
                idValue.setCellStyle(bodyStyle());

                HSSFCell titleValue = bodyRow.createCell(1);
                titleValue.setCellValue(course.getTitle());
                titleValue.setCellStyle(bodyStyle());

                HSSFCell hoursValue = bodyRow.createCell(2);
                hoursValue.setCellValue(course.getHours());
                hoursValue.setCellStyle(bodyStyle());

                HSSFCell eeeValue = bodyRow.createCell(3);
                eeeValue.setCellValue(course.getEee());
                eeeValue.setCellStyle(bodyStyle());

                HSSFCell readinessValue = bodyRow.createCell(4);
                readinessValue.setCellValue(course.getReadiness());
                readinessValue.setCellStyle(bodyStyle());

                i++;
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean createCourseDir(List<CourseDir> courseDirs, File file) {
        Collections.sort(courseDirs,new Comparator<CourseDir>() {
            public int compare(CourseDir o1, CourseDir o2) {
                return o1.getCourse().getTitle().compareTo(o2.getCourse().getTitle());
            }
        });
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "coursedirs" + ".xls");

            HSSFSheet workSheet = workbook.createSheet("Courses directions");
            workSheet.setDefaultColumnWidth(40);

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell columnId = headerRow.createCell(0);
            columnId.setCellValue("№");
            columnId.setCellStyle(headStyle());

            HSSFCell columnCourse = headerRow.createCell(1);
            columnCourse.setCellValue("Название курса");
            columnCourse.setCellStyle(headStyle());

            HSSFCell columnDirection = headerRow.createCell(2);
            columnDirection.setCellValue("Название направления");
            columnDirection.setCellStyle(headStyle());

            int i = 1;
            for (CourseDir courseDir : courseDirs) {
                HSSFRow bodyRow = workSheet.createRow(i);

                HSSFCell idValue = bodyRow.createCell(0);
                idValue.setCellValue(i);
                idValue.setCellStyle(bodyStyle());

                HSSFCell courseValue = bodyRow.createCell(1);
                courseValue.setCellValue(courseDir.getCourse().getTitle());
                courseValue.setCellStyle(bodyStyle());

                HSSFCell directionValue = bodyRow.createCell(2);
                directionValue.setCellValue(courseDir.getDirection().getTitle());
                directionValue.setCellStyle(bodyStyle());

                i++;
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean createDevelopers(List<Developer> developers, File file) {
        Collections.sort(developers,new Comparator<Developer>() {
            public int compare(Developer o1, Developer o2) {
                return o1.getCourse().getTitle().compareTo(o2.getCourse().getTitle());
            }
        });
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "developers" + ".xls");

            HSSFSheet workSheet = workbook.createSheet("Developer");
            workSheet.setDefaultColumnWidth(40);

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell columnId = headerRow.createCell(0);
            columnId.setCellValue("№");
            columnId.setCellStyle(headStyle());

            HSSFCell columnCourse = headerRow.createCell(1);
            columnCourse.setCellValue("Название курса");
            columnCourse.setCellStyle(headStyle());

            HSSFCell columnSurname = headerRow.createCell(2);
            columnSurname.setCellValue("Фамилия");
            columnSurname.setCellStyle(headStyle());

            HSSFCell columnName = headerRow.createCell(3);
            columnName.setCellValue("Имя");
            columnName.setCellStyle(headStyle());

            HSSFCell columnSecondName = headerRow.createCell(4);
            columnSecondName.setCellValue("Отчество");
            columnSecondName.setCellStyle(headStyle());

            HSSFCell columnPosition = headerRow.createCell(5);
            columnPosition.setCellValue("Должность");
            columnPosition.setCellStyle(headStyle());

            HSSFCell columnDepartment = headerRow.createCell(6);
            columnDepartment.setCellValue("Кафедра");
            columnDepartment.setCellStyle(headStyle());

            HSSFCell columnPhone = headerRow.createCell(7);
            columnPhone.setCellValue("Номер телефона");
            columnPhone.setCellStyle(headStyle());

            HSSFCell columnEmail = headerRow.createCell(8);
            columnEmail.setCellValue("Email");
            columnEmail.setCellStyle(headStyle());

            int i = 1;
            for (Developer developer : developers) {
                HSSFRow bodyRow = workSheet.createRow(i);

                HSSFCell idValue = bodyRow.createCell(0);
                idValue.setCellValue(i);
                idValue.setCellStyle(bodyStyle());

                HSSFCell courseValue = bodyRow.createCell(1);
                courseValue.setCellValue(developer.getCourse().getTitle());
                courseValue.setCellStyle(bodyStyle());

                HSSFCell surnameValue = bodyRow.createCell(2);
                surnameValue.setCellValue(developer.getUser().getSurname());
                surnameValue.setCellStyle(bodyStyle());

                HSSFCell nameValue = bodyRow.createCell(3);
                nameValue.setCellValue(developer.getUser().getName());
                nameValue.setCellStyle(bodyStyle());

                HSSFCell secondNameValue = bodyRow.createCell(4);
                secondNameValue.setCellValue(developer.getUser().getSecondname());
                secondNameValue.setCellStyle(bodyStyle());

                HSSFCell positionValue = bodyRow.createCell(5);
                positionValue.setCellValue(developer.getUser().getPosition());
                positionValue.setCellStyle(bodyStyle());

                HSSFCell departmentValue = bodyRow.createCell(6);
                departmentValue.setCellValue(developer.getUser().getDepartment().getTitle());
                departmentValue.setCellStyle(bodyStyle());

                HSSFCell phoneValue = bodyRow.createCell(7);
                phoneValue.setCellValue(developer.getUser().getPhone());
                phoneValue.setCellStyle(bodyStyle());

                HSSFCell emailValue = bodyRow.createCell(8);
                emailValue.setCellValue(developer.getUser().getEmail());
                emailValue.setCellStyle(bodyStyle());

                i++;
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

}
