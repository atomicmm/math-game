package com.ml.atomic.mathgame;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * 将算术题运行结果导出为xlsx格式的渲染器
 */
public class XlsxExportRenderer implements IResultRenderer {
    private static final String EXPORT_LOCATION = "/home/atomic/tmp/math_game.xlsx";

    @Override
    public void execute(List<String> items) {
        var params = new TemplateExportParams("tpl_math_game.xlsx");
        Map<String, Object> content = Maps.newHashMapWithExpectedSize(items.size() / 2);

        if (items.size() % 2 != 0) items.remove(0); //保证一行两个

        List<ExcelItem> contentItem = Lists.partition(items, 2).stream()
                .map(line -> new ExcelItem(line.get(0), line.get(1))).collect(toList()); //Excel模板一行两个
        content.put("content", contentItem);

        Workbook workbook = ExcelExportUtil.exportExcel(params, content);
        try (var fos = new FileOutputStream(EXPORT_LOCATION)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Data @ToString @AllArgsConstructor
    public static class ExcelItem {
        String first;
        String second;
    }

}
